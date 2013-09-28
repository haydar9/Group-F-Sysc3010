package ca.carleton.sysc4001.project.trial.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection extends Thread {
	
	private final static int UNKNOWN = -1;
	private final static int PLAYER = 0;
	private final static int SPECTATOR = 1;
	//create common interface for these messages/enum
	private static String ACKNOWLEDGE_MESSAGE = "ack";
	
	private boolean isIdentified;
	private int clientType;
	
	
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	
	public ClientConnection(Socket socket)
	{
		this.socket = socket;
		System.out.println("Client Connected: " + socket.getLocalSocketAddress());
		isIdentified = false;
		clientType = UNKNOWN;
	}

	@Override
	public void run()
	{
		try{
			 out = new PrintWriter(socket.getOutputStream(), true);
			 in = new BufferedReader(
					new InputStreamReader(
					socket.getInputStream()));
		 } catch(IOException e){}
		
		String input = null;
		
		try {
			while ((input = in.readLine()) != null) {
				System.out.println(input);
				//String outputLine = kkp.processInput(inputLine);
				if(input.equals("hello")) 
					out.println("hello from server");
				out.println(new String(ACKNOWLEDGE_MESSAGE));
				if (input.equals("close"))
				    break;
			    }
		} catch (IOException e) {
		}
	}
	
	/**
	 * Sends an array of bytes can send array serialized objects.
	 * @param bytes
	 * @return True if no error occurred, false otherwise.
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
	
	/**
	 * Reads one line of the input, separated by '\n', '\r' or any other line separator.
	 * @return
	 * @see {@link BufferedReader#readLine}
	 */
	public byte[] receiveBytes() {
		String returned = null;
		
		try 
		{ 
			returned = in.readLine();
		} catch(Exception e) {
			System.out.println("Error<Client.sendBytes>: Error sending bytes.");
			return null;
		}
		return returned.getBytes();
	}
	
	public void close()
	{
		try {
			socket.close();
			
			if(in != null) 
				in.close();
			if(out != null)
				out.close();
		} catch (IOException e) {
		}
		
	}
}
