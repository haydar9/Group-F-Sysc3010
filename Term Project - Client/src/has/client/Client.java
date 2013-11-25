package has.client;

import has.client.connection.ConnectionManager;
import has.client.gui.view.TestView;
import has.client.model.Model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Main class to deploy client, initializing and deployment logic should be in the main method.
 * @author haydar
 *
 */
public class Client{

	public final static String DEFAULT_HOST = "localhost";
	public final static int DEFAULT_PORT = 4444;
	
	//TODO: add logger
	public static Model model;

	public static void main(String[] args)
	{
		
		//handle command line arguments: so far none
		//perhaps host and port for better dynamic code
		
		String host = Client.DEFAULT_HOST;
		int port = Client.DEFAULT_PORT;
		TestView view;
		
		System.out.println("Attempting to connect to server...");
		
		//establish connection
		if(ConnectionManager.getInstance().establishConnection(host, port))
		{
			System.out.println("Successfully connected to server.");
			//if successful -> launch GUI
			//TODO: log creating GUI
			Client.model = new Model();
			view = new TestView();
			Client.model.addObserver(view);
			
		}
		
		else {
			//if failed display a dialog and exit
			System.err.println("Failed to connect to server.");
			JOptionPane.showMessageDialog(null, "Failed to connect to server.", "Home Automation System",JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		
		//dispatch responseHandler thread
		ReceiverThread rT = new ReceiverThread(ConnectionManager.getInstance());
		rT.start();

		
		try {
			rT.join();
			System.out.println("Receiver Thread joined");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Client: main() exit");
		
	}
	
	
}
