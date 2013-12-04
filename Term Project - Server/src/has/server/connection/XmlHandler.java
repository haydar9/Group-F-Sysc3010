package has.server.connection;

import has.comm.XmlDefinition;
import has.server.Server;
import has.server.controller.DeviceManager;
import has.server.management.HomeAutomationSystem;

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
		Element updateNode = doc.createElement(XmlDefinition.UPDATE);
		rootElement.appendChild(updateNode);


		Element ledNode = doc.createElement(XmlDefinition.LED);
		// setting attribute to <led> element
		Attr ledIdAttribute = doc.createAttribute("id");
		ledIdAttribute.setValue(id);
		ledNode.setAttributeNode(ledIdAttribute);

		updateNode.appendChild(ledNode);

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

	public static String generateMotionSensorUpdate(boolean motionDetected)
	{
		if(docBuilder == null) return null;
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("HomeAutomationSystem");
		doc.appendChild(rootElement);

		// staff elements
		Element updateNode = doc.createElement(XmlDefinition.UPDATE);
		rootElement.appendChild(updateNode);


		Element motionSensorNode = doc.createElement(XmlDefinition.MOTION_SENSOR);

		updateNode.appendChild(motionSensorNode);

		// led_status elements
		Element motion_status = doc.createElement("status");
		if(!motionDetected){
			motion_status.appendChild(doc.createTextNode(XmlDefinition.MOTION_STILL));
		}
		else
			motion_status.appendChild(doc.createTextNode(XmlDefinition.MOTION_DETECTED));

		motionSensorNode.appendChild(motion_status);

		//transform into xml
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

	/*
	 * Migrated to HomeAutomationSystem class
	 */
	/*	public static void handleRquest(String xml)
	{
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

							if(id.equals("1")) HomeAutomationSystem.turnLed(1, tempstatus);
							else if (id.equals("2")) HomeAutomationSystem.turnLed(2, tempstatus);
							else if (id.equals("3")) HomeAutomationSystem.turnLed(3, tempstatus);
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
	}*/




	public static String generateTemperatureUpdate(double temperatureValue) {
		if(docBuilder == null) return null;
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("HomeAutomationSystem");
		doc.appendChild(rootElement);

		// staff elements
		Element updateNode = doc.createElement(XmlDefinition.UPDATE);
		rootElement.appendChild(updateNode);


		Element temepratureSensorNode = doc.createElement(XmlDefinition.TEMPERATURE_SENSOR);

		updateNode.appendChild(temepratureSensorNode);

		// led_status elements
		Element tempValue = doc.createElement(XmlDefinition.VALUE);

		tempValue.appendChild(doc.createTextNode(String.valueOf(temperatureValue)));

		temepratureSensorNode.appendChild(tempValue);

		//transform into xml
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

	public static String generateFanUpdate(boolean fanStatus) {
		if(docBuilder == null) return null;
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("HomeAutomationSystem");
		doc.appendChild(rootElement);

		// staff elements
		Element updateNode = doc.createElement(XmlDefinition.UPDATE);
		rootElement.appendChild(updateNode);


		Element fanNode = doc.createElement(XmlDefinition.FAN);

		updateNode.appendChild(fanNode);

		// led_status elements
		Element fan_status = doc.createElement(XmlDefinition.STATUS);

		if(fanStatus)
			fan_status.appendChild(doc.createTextNode("ON"));
		else
			fan_status.appendChild(doc.createTextNode("OFF"));



		fanNode.appendChild(fan_status);

		//transform into xml
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

	public static String generateModelUpdate() {
		if(docBuilder == null) return null;
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("HomeAutomationSystem");
		doc.appendChild(rootElement);

		// staff elements
		Element modelNode = doc.createElement(XmlDefinition.MODEL);
		rootElement.appendChild(modelNode);


		Element fanNode = doc.createElement(XmlDefinition.FAN);

		modelNode.appendChild(fanNode);

		// led_status elements
		Element fan_status = doc.createElement(XmlDefinition.STATUS);

		if(Server.model.isFanStatus())
			fan_status.appendChild(doc.createTextNode("ON"));
		else
			fan_status.appendChild(doc.createTextNode("OFF"));



		fanNode.appendChild(fan_status);



		Element temepratureSensorNode = doc.createElement(XmlDefinition.TEMPERATURE_SENSOR);

		modelNode.appendChild(temepratureSensorNode);

		// temp value
		Element tempValue = doc.createElement(XmlDefinition.VALUE);

		tempValue.appendChild(doc.createTextNode(String.valueOf(Server.model.getTemperatureValue())));

		temepratureSensorNode.appendChild(tempValue);



		Element motionSensorNode = doc.createElement(XmlDefinition.MOTION_SENSOR);

		modelNode.appendChild(motionSensorNode);

		// led_status elements
		Element motion_status = doc.createElement("status");
		if(!Server.model.isMotionSensorStatus()){
			motion_status.appendChild(doc.createTextNode(XmlDefinition.MOTION_STILL));
		}
		else
			motion_status.appendChild(doc.createTextNode(XmlDefinition.MOTION_DETECTED));

		motionSensorNode.appendChild(motion_status);


		for(int i =1; i <=3 ; i++)
		{
			Element ledNode = doc.createElement(XmlDefinition.LED);
			// setting attribute to <led> element
			Attr ledIdAttribute = doc.createAttribute("id");
			ledIdAttribute.setValue(String.valueOf(i));
			ledNode.setAttributeNode(ledIdAttribute);

			modelNode.appendChild(ledNode);

			// led_status elements
			Element led_status = doc.createElement("status");
			boolean status = false;

			switch(i){
			case 1:
				status = Server.model.isLed1Status();
				break;
			case 2:
				status = Server.model.isLed2Status();
				break;
			case 3:
				status = Server.model.isLed3Status();
				break;

			}
			if(!status){
				led_status.appendChild(doc.createTextNode("OFF"));
			}
			else
				led_status.appendChild(doc.createTextNode("ON"));
			
			ledNode.appendChild(led_status);
		}


		//transform into xml
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

	//for testing
	public static void main(String argv[]) {

		System.out.println(generateLEDUpdate(true, "1"));
		System.out.println(generateMotionSensorUpdate(true));
		System.out.println(generateFanUpdate(true));
		System.out.println(generateTemperatureUpdate(22.0));
		System.out.println(generateModelUpdate());


	}
}