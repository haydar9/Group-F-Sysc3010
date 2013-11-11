package has.client.connection;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.omg.CORBA.Request;

/**
 * Connection Manger: logic and requirements should be here.
 * All methods here are static to generalize its usage.
 * @author haydar
 *
 */
public class ConnectionManager {

	private final static Logger log = Logger.getLogger(ConnectionManager.class.getSimpleName()); 

	private static Connection connection = new Connection();
	
	/** Timeout time in seconds */
	private static int timeout = 1;
	
	
	public static boolean establishConnection(String host, int port) 
	{
		
		log.log(Level.INFO, "Attempting to connect to server...");
		if(connection.establishConnection(host, port))
		{
			log.log(Level.INFO, "Successfully connected to server");
			return true;
		}
		else {
			log.log(Level.SEVERE, "Unable to connect to server");
			return false;
		}
	}

	public boolean isConnected()
	{
		return connection.isConnected();
	}
	
	/**
	 * Connection Manager on the client side only sends request then wait for response.
	 * @param request
	 * @return true if acknowledge
	 */
	public static Response send(Request request)
	{
		//change request to xml
		
		
		//connection.sendMessage();
		
		//when waiting for response: set up resposnse timeout
		
		//parse response 
		
		//direct to appropiate manager/handler
	}


	
	
	//getters and setters

	public static String getHost() {
		return connection.getHost();
	}
	
	public static int getPort() {
		return connection.getPort();
	}

	public static int getTimeout() {
		return timeout;
	}

	public static void setTimeout(int timeout) {
		ConnectionManager.timeout = timeout;
	}
	
	
	
}
