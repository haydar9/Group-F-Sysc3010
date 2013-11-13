package has.client;

import has.client.connection.ConnectionManager;
import has.client.gui.view.View;
import has.client.model.Model;

/**
 * Main class to deploy client, initializing and deployment logic should be in the main method.
 * @author haydar
 *
 */
public class Client{

	private final static String DEFAULT_HOST = "";
	private final static int DEFAULT_PORT = 4444;
	
	
	//TODO: add logger
	
	private static String host = DEFAULT_HOST;
	private static int port = DEFAULT_PORT;
	
	private static int updatePerSecond = 1;
	
	private static View view;
	
	public static void main(String[] args)
	{
		//handle command line arguments: so far none
		//perhaps host and port for better dynamic code
		
		//establish connection
		if(ConnectionManager.establishConnection(host, port))
		{
			//if successful
			//launch GUI
			//TODO: log creating GUI
			Client.view = new View(Model.getInstance());
		}
		
		else {
			//if failed display a dialog and exit
		}
		
		//set up connection related stuff such as xml parser and such
		
		//while loop for updating 
		//this is part of the controller
		while(ConnectionManager.isConnected())
		{
			//send request for update
			
			try {
				Thread.sleep(1000/updatePerSecond);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
}
