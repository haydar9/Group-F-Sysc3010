package ca.carleton.sysc3010.project.trial.java.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ca.carleton.sysc3010.project.trial.java.server.game.GameServerSide;
import ca.carleton.sysc3010.project.trial.java.server.game.Player;
import ca.carleton.sysc3010.project.trial.java.utility.CommunicationMessages;


public class ClientConnection extends Thread {
	
	
	private String clientType;
	
	private String clientName;
	
	private GameServerSide game;
	
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	
	
	public ClientConnection(Socket socket, GameServerSide game)
	{
		this.socket = socket;
		System.out.println("Client Connected: " + socket.getLocalSocketAddress());
		clientType = CommunicationMessages.Client.Type.UNKNOWN;
		this.game = game;
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
			//initiate talking by asking client what they are (player or spectator)
			out.println(new String(CommunicationMessages.Server.WHAT_ARE_YOU));
			
			if (in.readLine().equals(CommunicationMessages.Client.Type.PLAYER))
			{
				clientType = CommunicationMessages.Client.Type.PLAYER;
				out.println(new String(CommunicationMessages.Server.WHO_ARE_YOU));
				clientName = in.readLine();
				out.println(new String(CommunicationMessages.Server.WELCOME_PLAYER));
			}
			else if (in.readLine().equals(CommunicationMessages.Client.Type.SPECTATOR))
			{
				clientType = CommunicationMessages.Client.Type.SPECTATOR;
				out.println(new String(CommunicationMessages.Server.WHO_ARE_YOU));
				clientName = in.readLine();
				out.println(new String(CommunicationMessages.Server.WELCOME_PLAYER));
			}
			else {
				out.println(new String(CommunicationMessages.Server.REJECTED));
				System.out.println("Error: Client is not recognized.");
				close();
				return; //terminate thread by returning from the run method
			}
			
			//client recognized
			System.out.println("Client: " + clientType + ", Name: " + clientName);
			//get game object to redirect to
			game.addPlayer(new Player(clientName));
			game.startGame(this);
			
			out.println(CommunicationMessages.Server.DISPLAY);
			out.println("Game started, ENJOY!");
			out.println(CommunicationMessages.Server.GAME_START);
			//the whole point of using thread is here
			while ((input = receieveMessage()) != null) {
				game.processInput(input,this);
			}
			
			//close connection and all I/O channels
			close();
		} catch (IOException e) {
		}
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
			System.out.println("Error<sendMessage>: Error sending bytes.");
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
			System.out.println("Error<receieveMessage>: Error sending bytes.");
			return null;
		}
		return returned;
	}
	
	public void close()
	{
		try {
			in.close();
			out.close();
			socket.close();		
		} catch (IOException e) {
		}
		
	}
	
	public String getClientType() {
		return clientType;
	}

	public String getClientName()
	{
		return clientName;
	}
}
