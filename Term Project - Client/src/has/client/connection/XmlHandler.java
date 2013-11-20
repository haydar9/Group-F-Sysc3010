package has.client.connection;

import has.client.Client;

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
		boolean toggle = true;
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	//handle response
	
	public static String generateLEDControlRequest(boolean status)
	{
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("HomeAutomationSystem");
		doc.appendChild(rootElement);
				
		// staff elements
		Element has = doc.createElement("request");
		rootElement.appendChild(has);
				 
		// setting attribute to has element
		Attr attribute = doc.createAttribute("type");
		attribute.setValue("control");
		has.setAttributeNode(attribute);
				 
		// led_status elements
		Element led_status = doc.createElement("led_status");
		if(!status){
		led_status.appendChild(doc.createTextNode("OFF"));
		}
		else
		led_status.appendChild(doc.createTextNode("ON"));
		has.appendChild(led_status);
		
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
	
	public static void handleResponse(String xml)
	{
		//TODO: Cassandra part, parse response here and update model
		//you can access model by using "Client.model", if u need anything to initialize put it in the static field on top
		//this is just testing code
		if(xml.equals("ACK")){
			if(toggle)
			{	
				System.out.println("Updating Model with: 'ON'");
				Client.model.setFanStatus(toggle);
				toggle = false;
			}
			else{
				System.out.println("Updating Model with: 'OFF'");
				Client.model.setFanStatus(toggle);
				toggle = true;
			}
		}
		else if(xml.contains("PeriodicUpdate")){
			System.out.println("Periodic Update Response: " + xml);
			
		}
	}
	

public static void main(String argv[]) {
	
	System.out.println(generateLEDControlRequest(true));
	
}

}