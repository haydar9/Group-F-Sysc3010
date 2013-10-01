package ca.carleton.sysc3010.project.trial.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ca.carleton.sysc3010.project.trial.java.client.Client;
import ca.carleton.sysc3010.project.trial.java.server.game.GameServerSide;
import ca.carleton.sysc3010.project.trial.java.server.game.Player;
import ca.carleton.sysc3010.project.trial.java.utility.CommunicationMessages;

/**
 * Our RPI Game Server that will be run on start up or manually. 
 * @author haydar
 *
 */
public class Server extends Thread {
	
	
	private ServerSocket serverSocket = null;
    private boolean isRunning = false;
    private List<ClientConnection> clientList;
	private final static int PORT = 4444;
    public GameServerSide game;
	
    public Server()
    {
    	clientList = new ArrayList<ClientConnection>();
    	game = new GameServerSide(this);//not good 
    }
    
    //Note not same as thread's start(), has different signature
	public boolean start(int port)
	{
		try {
			System.out.print("Starting Server...");
            serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(1000);
            System.out.println("Started.\nIP Address: " + serverSocket.getInetAddress() +
            		"\nLocal Port: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            System.err.println("Could not listen on: " + port);
            return false;
        }
        
        return true;
	}
	
	public void shutdown()
	{
		
			try {
				for(ClientConnection cc : clientList)
				{
					cc.close();
				}
			
				serverSocket.close();
				System.out.println("Shutting down Server...Successful.");
			} catch (IOException e) {}
			
	}
	
	@Override
	public void run()
	{
        isRunning = true;
        while(isRunning)
        {
        	try {
        		ClientConnection cc = new ClientConnection(serverSocket.accept(),game);
        		cc.start();
          } catch (IOException e) {
            }
        	
        }
      }
	
	public boolean addClient(ClientConnection cc)
	{
		return clientList.add(cc);
	}
	
	public boolean removeClient(ClientConnection cc)
	{
		return clientList.remove(cc);
	}
	
	/**
	 * Send a message to a specific client.
	 * @param clientName Client name to send message.
	 */
	public void sendMessageToClient(String clientName, String message)
	{
		for(ClientConnection cc : clientList)
		{
			if(cc.getName().equals(clientName))
			{
				cc.sendMessage(message);
				break;
			}
		}
	}
	
	/**
	 * To send something to all players/clients, simply implemented for low scale.
	 * @param message The message to broadcast to all clients connected.d
	 */
	public void broadcastMessage(String message)
	{
		for(ClientConnection cc: clientList)
		{
			cc.sendMessage(message);
		}
	}
	
	
	public void broadcastDisplayMessage(String message)
	{
		for(ClientConnection cc: clientList)
		{
			cc.sendMessage(CommunicationMessages.Server.DISPLAY);
			cc.sendMessage(message);
		}
	}
	/*public synchronized void blockAcceptingConnections()
	{
		try {
			wait();
		} catch (InterruptedException e) {
			System.out.println("Error: Unable to obtain lock on server.");
		}
	}*/
	
	/*public synchronized unblockAcceptingConnections() {
	       notify();
	}*/
	
    public static void main(String[] args) {
    	
    	
    	//parse arguments 
    	Server server = new Server();
    	
    	server.start(PORT);
        
    	
    	server.start(); //start server thread to accept connection, this is thread method
     	
    	server.game.start(); //thread method
    	
		Scanner terminal = new Scanner(System.in);
		//terminal.useDelimiter("\n|\r\n|\\s+");
		
		while(server.isAlive()){
			String command = terminal.nextLine();
			if (command.equals("shutdown"))
			{
				System.out.println("Shutting down Server...");
				server.isRunning = false; //clean shutdown to the loop
				try {
					server.join(); //force
				} catch (InterruptedException e) {}
			}
		/*	else if (command.equals("suspend server"))
			{
				server.blockAcceptingConnections();
			}
			else if(command.equals("resume server"))
			{
				//server.unblockAcceptingConnections();
			}*/
			else if(command.equals("broadcast"))
			{
				server.broadcastDisplayMessage(terminal.nextLine());
			}
			else {
				System.out.println("Error: Invalid command.");
			}
		}//end of while loop
        
		terminal.close();
		
        server.shutdown();
    
    }
}
