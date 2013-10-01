package ca.carleton.sysc3010.project.trial.java.utility;

/**
 * This class contains the communication messages
 * @author cassandra
 *
 */
public class CommunicationMessages {
	
	public static final String DONT_SEND_FEEDBACK = "ignore sending back";

	private static final String ACKNOWLEDGE_MESSAGE = "ack";
	
	public class Client
	{
	public static final String CLIENT_WAITING = "Okay";
	public static final String I_DONT_KNOW = "I don't know what you're saying";
	public static final String BUTTON_PRESSED = "The button was pressed!";
	
	// type of clients
	public class Type 
	{
		public static final String UNKNOWN = "Unknown";
		public static final String PLAYER = "Player";
		public static final String SPECTATOR = "Spectator";
	}
	
	}
	
	public class Server
	{
		public static final String WAIT = "Wait";
		public static final String WHO_ARE_YOU = "Who are you?";
		public static final String WHAT_ARE_YOU = "What are you?";
		public static final String WELCOME_PLAYER = "Welcome the player";
		public static final String FORCE_EXIT = "Force Exit";
		public static final String DISPLAY = "Display on terminal";
		public static final String REJECTED = "Connection Rejected";
		public static final String GAME_START = "Game Started!";
	}
	
	public class Spectator
	{
		
		
	}
	
	
}
