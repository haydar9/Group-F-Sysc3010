package ca.carleton.sysc4001.project.trial.java.client;

/**
 * 
 * @author haydar
 *
 */
public class Client {

	//welcome message
	private static String WELCOME_MESSAGE = "Welcome to Jeopardy!";
	
	/* Handles Client connection to server	 */
	private ClientConnection connection;
	
	private final static String HOST = "192.168.0.20";
	private final static int PORT = 4444;
	
	private ClientGame game;
	
	private String playerName;
	

	//Default Constructor
	public Client()
	{
		connection = new ClientConnection();
		game = new ClientGame();
	}
	
	public boolean sendMessage(String message)
	{
		return connection.sendBytes(message.getBytes());
	}
	
	public String readMessage()
	{
		byte[] temp = connection.receiveBytes();
		
		if(temp != null)
		{
			return new String(temp);
		}
		
		return null;
	}
	
	public boolean connectToServer()
	{
		return connection.establishConnection(HOST, PORT);
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
		
		System.out.println(WELCOME_MESSAGE + ", " + client.getPlayerName());
		
		
		System.out.print("Connecting to server...");
		
		if(client.connectToServer())
		{
			System.out.println("Successful.");
		}
		else 
		{
			System.out.println("Failed.");
			System.out.println("Client will exit.");
			System.exit(1);
		}
		
		client.sendMessage("<player>"+client.getPlayerName()+"</player>");
		
		//TODO: add checking for game on server first
		
		//start game
		
		boolean running = true;
		
		//game loop
		while(running)
		{
			String incoming = new String();
			System.out.println(incoming); 
			//game.processCommand(incoming);
		}
		
		//client terminated
		client.connection.closeConnection();
		
	}
	
	
}
