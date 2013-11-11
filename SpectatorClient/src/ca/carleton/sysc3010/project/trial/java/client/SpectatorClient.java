package ca.carleton.sysc3010.project.trial.java.client;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import ca.carleton.sysc3010.project.trial.java.utility.CommunicationMessages;
import ca.carleton.sysc3010.project.trial.java.utility.Connection;

/**
 * 
 * @author haydar
 *
 */
public class SpectatorClient {
	
	//welcome message
	private static String WELCOME_MESSAGE = "Welcome to Jeopardy!";
		
		
	/* Handles Client connection to server	 */
	private Connection connection;
	
	private final static String HOST = "10.0.0.62";
	private final static int PORT = 4444;
	
	private ClientGame game;
	
	private String spectatorName;

	//Default Constructor
	public SpectatorClient(String spectatorName)
	{
		connection = new Connection();
		this.spectatorName = spectatorName;
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
	 * @param input the input to process
	 * @return output the command to send back to the server
	 * after processing the server
	 */
	public String processCommand(String input)
	{
		if(input.equals(CommunicationMessages.Server.WAIT))
		{
			return CommunicationMessages.Client.CLIENT_WAITING;
		}
		else if(input.equals(CommunicationMessages.Server.WHO_ARE_YOU))
		{
			return spectatorName;
			
		}
		else if(input.equals(CommunicationMessages.Server.WHAT_ARE_YOU))
		{
			return new String(CommunicationMessages.Client.Type.SPECTATOR);
		}
		else if(input.equals(CommunicationMessages.Server.WELCOME_PLAYER))
		{
			System.out.println(WELCOME_MESSAGE);
			return CommunicationMessages.DONT_SEND_FEEDBACK;
		}
		else if(input.equals(CommunicationMessages.Server.GAME_START))
		{
			System.out.println("Game started, server is waiting for a player to press button.");
			return CommunicationMessages.DONT_SEND_FEEDBACK;
		}
		else
		{
			return CommunicationMessages.Client.I_DONT_KNOW;
		}
		
		
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
			System.out.println("Must specify name.\n<Usage>: Client <spectator name>");
			System.exit(1);
		}
		
		
		SpectatorClient client = new SpectatorClient(args[0]);

		
		
		
		
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
		
		
/*		try{
		Process p;
		p = Runtime.getRuntime().exec("python pythontest.py");
		InputStreamReader isr = new InputStreamReader(p.getInputStream());
		BufferedReader br = new BufferedReader(isr);
		PrintWriter out = new PrintWriter(p.getOutputStream(), true);
		String temp = null;
		Thread.sleep(5000);
		while((temp=br.readLine())!=null)
		{	System.out.println(temp);
			if(temp.equals("0")){

				System.out.println("buttonpressed");
			}
		}
		System.out.println("Python script terminated.");	

		}catch(Exception e){
			e.printStackTrace();
		}
		*/
		//end test
		
		
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
			
			output = client.processCommand(input);
			
			//some commands doesn't require to send feedback then skip rest of code in loop
			if(output.equals(CommunicationMessages.DONT_SEND_FEEDBACK)) continue;//stops the loop here and iterate next
			
			client.sendMessage(output);
		}
		
		//client terminated
		System.out.println("Client exited.");
		client.connection.closeConnection();
		
	}
}
