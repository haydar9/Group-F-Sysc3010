package ca.carleton.sysc4001.project.trial.java.client;

/**
 * This class contains the communication messages
 * @author cassandra
 *
 */
public class CassandraConstants {
	
	public class Client
	{
	public static final String CLIENT_WAITING = "Okay";
	public static final String I_DONT_KNOW = "I don't know what to say";
	
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
	}
	
	public class Spectator
	{
		
		
	}
}
