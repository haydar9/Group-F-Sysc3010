package has.client.test.model;

import has.client.Client;
import has.client.gui.view.TestView;
import has.server.Server;

public class TestXmlResponse {

	public static void main(String[] args)
	{
		//this is not a good way of testing dynamically,
		// i have to manually set up the test, instead of replacing 
		//the model referenced by handleXml() in the XmlHandler
		Client.model = new has.client.model.Model();
		Server.model = new has.model.Model(); //TODO: rename package, but really its not a server model
		//There should be a model on the device manager and a server model that is updated.
		
		//create the view offline
		TestView view = new TestView();
		Client.model.addObserver(view);
		
		String modelUpdateResponse = has.server.connection.XmlHandler.generateModelUpdate();
		System.out.println(modelUpdateResponse);
		
		
		has.client.connection.XmlHandler.handleXml(modelUpdateResponse);
		
		
		
		
	}
}
