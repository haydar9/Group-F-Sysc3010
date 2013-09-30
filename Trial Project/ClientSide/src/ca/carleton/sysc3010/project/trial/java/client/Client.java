package ca.carleton.sysc3010.project.trial.java.client;

import ca.carleton.sysc3010.project.trial.java.utility.CommunicationMessages;
import ca.carleton.sysc3010.project.trial.java.utility.Connection;

/**
 * 
 * @author haydar
 *
 */
public class Client {
	
	
	/* Handles Client connection to server	 */
	private Connection connection;
	
	private final static String HOST = "0.0.0.0";
	private final static int PORT = 4444;
	
	private ClientGame game;
	
	

	//Default Constructor
	public Client()
	{
		connection = new Connection();
		game = new ClientGame();
	}
	
	/** TODO: create a profound to constrain and control content of messages sent
	 * 
	 * @param message
	 * @return
	 */
	public boolean sendMessage(String message)
	{
		return connection.sendMessage(message);
	}
	
	/**
	 * Return null if fail to read anything from server.
	 * @return null if fail to read anything from server.
	 */
	public String receieveMessage()
	{
		return connection.receieveMessage();
	}
	
	public boolean connectToServer()
	{
		//attempt to connect
		if(connection.establishConnection(HOST, PORT))
		{
			
			return true;
			
		}
		return false;
	}
	

	
	/**
	 * Client class is the main class, where it can take arguments in order
	 * to customize the client functionality.
	 * @param args
	 */
	public static void main(String[] args)
	{
		//TODO: separate parsing command line arguments in a separate class or private inner class
		//initialize connection
		//attempt to establish connection
		//handle error and throwback 
		// if successful:
		// 1 - knockknock protocol.
		// 2 - establish state
		// 3 - render/update client game state
		// the game will be command line based. perhaps provide a command line interface for easier integration with GUI 
		// .... OR ....
		// consider RMI and serializing objects for getting model/game state
		
		//all it does is creating connection
		
		//must supply a name
		if(args.length != 1)
		{
			System.out.println("Must specify player name.\n<Usage>: Client <player name>");
			System.exit(1);
		}
		
		
		Client client = new Client();

		client.game.setPlayerName(args[0]);
		
		
		
		System.out.println("Connecting to server...");
		
		//this may take a bit longer than usual
		boolean result = client.connectToServer();
		
		if(result)
		{
			System.out.println("Connecting to server...Successful.");
		}
		else 
		{
			System.out.println("Failed.");
			System.out.println("Client will exit.");
			System.exit(1);
		}
		
		
		
		
		
		
		String input;
		String output;
		
		//connection made, now ask for access
		
		
		//game loop
		while((input = client.receieveMessage()) != null)
		{
			if(input.equals(CommunicationMessages.Server.FORCE_EXIT))
			{
				System.out.println("Server forced disconnection.");
				break;
			}
			else if (input.equals(CommunicationMessages.Server.DISPLAY))
			{
				System.out.println(client.receieveMessage());
				continue;
			}
			
			output = client.game.processCommand(input);
			
			//some commands doesn't require to send feedback then skip rest of code in loop
			if(output.equals(CommunicationMessages.DONT_SEND_FEEDBACK)) continue;//stops the loop here and iterate next
			
			client.sendMessage(output);
		}
		
		//client terminated
		client.connection.closeConnection();
		
	}
}
