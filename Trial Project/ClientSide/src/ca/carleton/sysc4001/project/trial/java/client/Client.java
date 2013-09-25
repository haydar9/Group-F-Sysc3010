package ca.carleton.sysc4001.project.trial.java.client;

public class Client {

	/* Handles Client connection to server	 */
	private ClientConnection connection;
	
	private final static String HOST = "192.168.0.20";
	private final static int PORT = 4444;
	
	
	//Default Constructor
	public Client()
	{
		connection = new ClientConnection(HOST, PORT);
	}
	
	public boolean sendSomething(Object e)
	{
		//could be object class or whatever.
		return false;
	}
	
	//....
	
	//parseCommandArguments
	
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
		Client client = new Client();
		
		
	}
	
	
}
