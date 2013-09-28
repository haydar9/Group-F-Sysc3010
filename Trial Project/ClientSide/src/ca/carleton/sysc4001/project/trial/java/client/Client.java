package ca.carleton.sysc4001.project.trial.java.client;

import ca.carleton.sysc4001.project.trial.java.utility.Connection;

/**
 * 
 * @author haydar
 *
 */
public class Client {
	
	//welcome message
	private static String WELCOME_MESSAGE = "Welcome to Jeopardy!";
	private static String ACKNOWLEDGE_MESSAGE = "ack";
	
	/* Handles Client connection to server	 */
	private Connection connection;
	
	private final static String HOST = "192.168.0.19";
	private final static int PORT = 4444;
	
	private ClientGame game;
	
	private String playerName;
	

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
			//connection made, now ask for access
		
			connection.sendMessage("player:" + getPlayerName());
			//feedback loop code
			//for now we ain't using threads, so make a simple for loop and check for feedback/acknowledge signal
			String feedback = connection.receieveMessage();
			if(feedback.equals(ACKNOWLEDGE_MESSAGE)){
				return true;
			}
			
			
		}
		return false;
	}
	
	
	//Getter and setter
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
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

		client.setPlayerName(args[0]);
		
		System.out.println(WELCOME_MESSAGE + " " + client.getPlayerName());
		
		
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
		
		//TODO: add checking for game on server first
		
		//start game
		
		
		
		String input;
		String output;
		//game loop
		client.sendMessage("Hello");
		while((input = client.receieveMessage()) != null)
		{
			System.out.println(input);
			//output = game.processCommand(incoming);
			//sendMessage(output); 
			
			//check if disconnected
			if(input.toLowerCase().equals("exit")) break;
		}
		
		//client terminated
		client.connection.closeConnection();
		
	}
}
