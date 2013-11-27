package has.server.management;

import has.server.connection.XmlDefinition;
import has.server.controller.DeviceInterface;
import has.server.controller.DeviceManager;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HomeAutomationSystem extends Thread{

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

	public static HomeAutomationSystem instance;

	private DeviceInterface deviceInterface;

	private LinkedBlockingDeque<String> tasks;

	private boolean running;

	public HomeAutomationSystem() throws IOException
	{
		this.deviceInterface = new DeviceManager();
		tasks = new LinkedBlockingDeque<String>();
		running = true;

		instance = this; //enable global access to this object
	}


	@Override
	public void run() {
		while(running)
		{
			try {
				handleRquest(tasks.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Add request onto the queue to be handled by the handler thread. 
	 * The reason for synchronization is because this method will be
	 * invoked from multiple threads (connection threads).
	 * @param request
	 */
	public synchronized void addRequest(String request)
	{
		try {
			tasks.put(request);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public boolean isRunning() {
		return running;
	}


	public void setRunning(boolean running) {
		this.running = running;
	}

	public void handleRquest(String xml)
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

							if(id.equals("1")) deviceInterface.turnLed(1, tempstatus);
							else if (id.equals("2")) deviceInterface.turnLed(2, tempstatus);
							else if (id.equals("3")) deviceInterface.turnLed(3, tempstatus);
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
	/*
	public synchronized String handleRequest(String request)
	{
		String output;

		//output = xmlToString(doc) - pass in node from generated xml

		return null; // make reutrn output if not equal to null
		// if null, then return an error xml
		// assume everything is correct
	}*/



	/*private String xmlToString(Node doc)
	{
		try {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString().replaceAll("\n|\r", "");

		return output;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}*/
}
