package has.server.management;

import has.server.controller.DeviceInterface;
import has.server.controller.DeviceManager;

import java.io.FileNotFoundException;
import java.io.StringWriter;

import javax.script.ScriptException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

public class HomeAutomationSystem {
	
	//FIXME: change to private after
	public DeviceInterface deviceInterface;
	
	public HomeAutomationSystem() throws Exception
	{
		try {
			deviceInterface = new DeviceManager();
		} catch (FileNotFoundException | ScriptException e) {
			e.printStackTrace();
			throw new Exception("Fail to initialize device manager");
			
		}
	}
	
	/**
	 * 
	 * @param request request in xml
	 * @return String representation of the response in xml format.
	 */
	public synchronized String handleRequest(String request)
	{
		
		String output;
		
		//output = xmlToString() - pass in node from generated xml
		
		return null; // make reutrn output if not equal to null
		// if null, then return an error xml
		// assume everything is correct
	}
	
	private String xmlToString(Node doc)
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
		
		
	}
}
