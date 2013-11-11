package has.client;

import has.client.connection.ConnectionManager;
import has.client.gui.GUI;

/**
 * Main class to deploy client, initializing and deployment logic should be in the main method.
 * @author haydar
 *
 */
public class Client{

	private final static String DEFAULT_HOST = "";
	private final static int DEFAULT_PORT = 4444;
	
	
	//TODO: add logger
	private static String host;
	private static int port;
	
	
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
			GUI gui = new GUI();
			
		}
		
		else {
			//if failed display a dialog and exit
		}
		
		//set up handlers (event handlers) NOT GUI event handlers
		
		//set up connection related stuff such as xml parser and such
	}
}
