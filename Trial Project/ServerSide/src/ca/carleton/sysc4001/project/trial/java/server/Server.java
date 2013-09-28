package ca.carleton.sysc4001.project.trial.java.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Our RPI Game Server that will be run on start up or manually. 
 * @author haydar
 *
 */
public class Server extends Thread {
	
	
	private ServerSocket serverSocket = null;
    private boolean isRunning = false;
    private List<ClientConnection> clientList;
	
    public Server()
    {
    	clientList = new ArrayList<ClientConnection>();
    }
    
    
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
        	try {//TODO: check if this uses memory up ==> memory leak suspected?!?!
        		ClientConnection cc = new ClientConnection(serverSocket.accept());
        		cc.start();
        		clientList.add(cc);
            } catch (IOException e) {
            }
        	
        }
      }
	
	//To send something to all players/clients
	public void broadcast()
	{
		
	}
	
    public static void main(String[] args) {
    	
    	if(args.length != 1)
    	{
    		System.out.println("<Usage>: Server <port>\nPort to listen on.");
    		System.exit(1);
    	}
    	if(!args[0].matches("[0-9]+"))
    	{
    		System.out.println("Port must be a number.");
    		System.exit(1);
    	}
    	//parse arguments 
    	Server server = new Server();
    	
    	if(!server.start(new Integer(args[0])))
        	System.exit(1);
        
    	
    	server.start(); //start server thread to accept connection
     		
		Scanner terminal = new Scanner(System.in);
			
		while(server.isAlive()){
			String command = terminal.next();
			if (command.equals("shutdown"))
			{
				System.out.println("Shutting down Server...");
				server.isRunning = false; //clean shutdown to the loop
				try {
					server.join(); //force
				} catch (InterruptedException e) {}
			}
			else {
				System.out.println("Error: Invalid command.");
			}
		}//end of while loop
        
		terminal.close();
		
        server.shutdown();
    
    }
}
