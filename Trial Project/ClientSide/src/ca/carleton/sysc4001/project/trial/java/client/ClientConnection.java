package ca.carleton.sysc4001.project.trial.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

/**
 * 
 * @author haydar
 *
 */
public class ClientConnection {

	private final Logger log = Logger.getLogger(ClientConnection.class.getSimpleName());
	
	//FIELDS
	private Socket socket = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private boolean isConnected;
	
	
	
	//CONSTRUCTORS
	/**
	 * Default constructor.
	 * Must call establishConnection() after default constructor.
	 */
	public ClientConnection()
	{
		isConnected = false;
	}
	
	public ClientConnection(String host, int port)
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
        
		} catch (UnknownHostException e) {
            System.err.println("Error<Client.establishConnection>: Unknown host: " + host);
            return false;
        } catch (IOException e) {
            System.err.println("Error<Client.establishConnection>: Couldn't get I/O for "
                               + "the connection to: " + host);
            return false;
        }
		
		//if successful the code will excute beyond this point
		System.out.println("Info<Client.establishConnection>: Connection established successfully: Host: "
        + host +", port: " + port);
		return true;
	}
	
	public boolean closeConnection()
	{
		try {
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
	 * Sends an array of bytes can send array serialized objects.
	 * @param bytes
	 * @return True if no error occured, false otherwise.
	 */
	public boolean sendBytes(byte[] bytes) {
		try 
		{ 
			out.println(bytes);
		} catch(Exception e) {
			System.out.println("Error<sendBytes>: Error sending bytes.");
			return false;
		}
		return true;
	}

	
	public byte[] receiveBytes() {
		try 
		{ 
			in.readLine();
		} catch(Exception e) {
			System.out.println("Error<Client.sendBytes>: Error sending bytes.");
			return null;
		}
		return null;
	}

}
