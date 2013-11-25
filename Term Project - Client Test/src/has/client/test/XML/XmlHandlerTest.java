package has.client.test.XML;

import has.client.connection.XmlHandler;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlHandlerTest {
	
		
	@Test
	public void testGenerateLEDControlRequestEquality(){
		try{
		File fXmlFile = new File("LedRequest.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document expecteddoc = dBuilder.parse(fXmlFile);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(expecteddoc), new StreamResult(writer));
		String output = writer.getBuffer().toString();//.replaceAll("\n|\r", "");

		String s = XmlHandler.generateLEDControlRequest(false, "1");
		String d = XmlHandler.generateLEDControlRequest(false, "2");
		
		System.out.println("Testing for Equality");
		System.out.println("Expected: " + s);
		System.out.println("Actual: " + output);
		System.out.println("Expected Test Result: True");
		System.out.println();
		assertTrue(s.equals(output));
		
		
		
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
		}
	
	@Test
	public void testGenerateLEDControlRequestNotEqual(){
		try{
		File fXmlFile = new File("LedRequest.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document expecteddoc = dBuilder.parse(fXmlFile);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(expecteddoc), new StreamResult(writer));
		String output = writer.getBuffer().toString();//.replaceAll("\n|\r", "");

		String d = XmlHandler.generateLEDControlRequest(true, "2");
		
		System.out.println("Testing for NonEquality");
		System.out.println("Expected: " + d);
		System.out.println("Actual: " + output);
		System.out.println("Expected Test Result: False");
		System.out.println();
		assertFalse(d.equals(output));
		
		
		
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
		}
	
	}
	

