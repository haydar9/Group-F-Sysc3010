package has.server.connection;

import has.server.Server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 * The static xml tools methods
 * @author haydar
 *
 */
public class XmlHandler {

	//fields
	private static boolean toggle;
	private static DocumentBuilder docBuilder;

	static {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//handle response

	public static String generateLEDUpdate(boolean status, String id)
	{
		if(docBuilder == null) return null;
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("HomeAutomationSystem");
		doc.appendChild(rootElement);

		// staff elements
		Element requestNode = doc.createElement(XmlDefinition.UPDATE);
		rootElement.appendChild(requestNode);


		Element ledNode = doc.createElement("led");
		// setting attribute to <led> element
		Attr ledIdAttribute = doc.createAttribute("id");
		ledIdAttribute.setValue(id);
		ledNode.setAttributeNode(ledIdAttribute);

		requestNode.appendChild(ledNode);

		// led_status elements
		Element led_status = doc.createElement("status");
		if(!status){
			led_status.appendChild(doc.createTextNode("OFF"));
		}
		else
			led_status.appendChild(doc.createTextNode("ON"));
		ledNode.appendChild(led_status);

		try{
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();//.replaceAll("\n|\r", "");
			return output;
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}


	public static void handleXml(String xml)
	{
		//TODO: Cassandra part, parse response here and update model
		try {
			Document doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes())));

			//TODO: double check, note: recommended - ingore for now until double checked
			doc.getDocumentElement().normalize();

			//TODO: validate through schema right away dont implement validation

			//check if model data is sent from server
			//get response tag
			NodeList nodeList = doc.getElementsByTagName(XmlDefinition.REQUEST);

			for(int i = 0; i < nodeList.getLength(); i++)
			{
				Node node = nodeList.item(i);
				if(XmlDefinition.LED.equals(node.getChildNodes().item(0).getNodeName()))
				{
					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element e = (Element) node;;
						String id = ((Element) e.getElementsByTagName(XmlDefinition.LED).item(0)).getAttribute("id");
						String status = e.getElementsByTagName(XmlDefinition.STATUS).item(0).getTextContent();
						if(id != null && status != null)
						{
							boolean tempstatus;
							if(status.equals("ON")) tempstatus = true;
							else tempstatus = false;

							if(id.equals("1")) Server.model.setLed1Status(tempstatus);
							else if (id.equals("2")) Server.model.setLed2Status(tempstatus);
							else if (id.equals("3")) Server.model.setLed3Status(tempstatus);
						}
					}
				}
			}

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String argv[]) {

		System.out.println(generateLEDUpdate(true, "1"));

	}

}