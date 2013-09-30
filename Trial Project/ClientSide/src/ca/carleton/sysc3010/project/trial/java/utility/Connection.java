package ca.carleton.sysc3010.project.trial.java.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

import ca.carleton.sysc3010.project.trial.java.client.Client;

/**
 * 
 * @author haydar
 *
 */
public class Connection {
	
	//FIELDS
	private final Logger log = Logger.getLogger(Connection.class.getSimpleName());
	
	//these configurations should be loaded or extracted dynamically
	private String host;
	private int port;
	
	//status
	private boolean isConnected;	
	
	//core
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	//private ObjectOutputStream oos;
	
	
	//TODO: add protocol object to handle communication protocol
	//Use knockknock protocol from java tutorial
	
	//CONSTRUCTORS
	/**
	 * Default constructor.
	 * Must call establishConnection() after default constructor.
	 */
	public Connection()
	{
		isConnected = false;
	}
	
	public Connection(String host, int port)
	{
		isConnected = establishConnection(host, port);
	}
	
	
	//METHODS
	
	/**
	 * Attempt one try to establish connection with host and port number.
	 * @param host The host to connect to, usually either name or IP address.
	 * @param port The port number of the host.
	 * @return True if successful, false otherwise.
	 */
	public boolean establishConnection(String host, int port) {
		try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        socket.getInputStream()));
            
            //oos = new ObjectOutputStream(socket.getOutputStream());
             
            this.host = host;
            this.port = port;
        
		} catch (UnknownHostException e) {
            System.err.println("Error<Client.establishConnection>: Unknown host: " + host);
            return false;
        } catch (IOException e) {
            System.err.println("Error<Client.establishConnection>: Couldn't get I/O for "
                               + "the connection to: " + host);
            return false;
        }
		
		//if successful the code will execute beyond this point
		return true;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean closeConnection()
	{
		try {
			//oos.close();
			in.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Error<Client.closeConnection>: Closing connection failed.");
			return false;		
		}
		
		//otherwise successful
		return true;
	}
	
	/**
	 * Sends a message to the server.
	 * @param message Message to send to the server.
	 * @return True if no error occurred, false otherwise.
	 */
	public boolean sendMessage(String message) {
		try 
		{ 
			out.println(message);
		} catch(Exception e) {
			System.out.println("Error<sendBytes>: Error sending bytes.");
			return false;
		}
		
		return true;
	}

	/*//Under test Dont use
	private boolean sendSerializedObject(Object o)
	{
		
		try {
			oos.writeObject(o);
		} catch (IOException e) {
					}
		return false;
	}*/
	
	/**
	 * Reads one line of the input from server, separated by '\n', '\r' or any other line separator.
	 * @return The input from server, null otherwise if reading not successful.
	 * @see {@link BufferedReader#readLine}
	 */
	public String receieveMessage() {
		String returned = null;
		
		try 
		{ 
			returned = in.readLine();
		} catch(Exception e) {
			System.out.println("Error<Client.sendBytes>: Error sending bytes.");
			return null;
		}
		return returned;
	}
	
	//test from cassandra
	
	//setter and getter
	

	/**
	 * TODO: change implementation to actually check for connection rather than returning isConnected
	 * @return
	 */
	public boolean isConnected() {
		return false; //this is done on purpose to trigger error because not implemented yet
	}
	
	public String getHost() {
		return host;
	}


	public int getPort() {
		return port;
	}

	//no need for setters for the above three variables since we only want to fetch their values
	
}
