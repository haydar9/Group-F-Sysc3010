package has.server;

import has.model.Model;
import has.server.connection.ConnectionHandlerThread;
import has.server.management.HomeAutomationSystem;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Server {

	public static Model model;
	
	public static HomeAutomationSystem has;
		
	public final static int DEFAUL_PORT = 4444;
	
	//List of clients connection
	private static List<ConnectionHandlerThread> clients = new ArrayList<ConnectionHandlerThread>();
	
	public static void removeConnection(
			ConnectionHandlerThread connectionHandlerThread) {
		clients.remove(connectionHandlerThread);
		
	}
	
	public static void addConnection(
			ConnectionHandlerThread connectionHandlerThread) {
		clients.add(connectionHandlerThread);
		
	}
	
	public static void broadcast(String xml)
	{
		if(clients != null){
			System.out.println(new Time(System.currentTimeMillis()) + ":\tTo Clients:\t" + xml);
			for ( ConnectionHandlerThread cHT : clients)
				cHT.send(xml);
		}
	}
	
	public static void main(String args[])
	{
		int port = Server.DEFAUL_PORT;
		
		//command line checking
		if(args.length != 0)
		{
			try {
				port = Integer.parseInt(args[0]);
			} catch (Exception e)
			{
				System.out.println("Invalid port number");
				System.exit(-1);
			}
		}
		
		try {
			has = new HomeAutomationSystem();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Failed to initialize the automation system");
			e1.printStackTrace();
			System.exit(-1);
		}
		
		//create new server
		ServerSocket server;
				
		model = new Model(); //initializes the model and assign it to a static variable to ease access
		
		//run the home automation system thread
		has.start();
		
		try {
			server = new ServerSocket(port);
			System.out.println("Server started successfully");
			System.out.println("Waiting for connection...");
			
			while (true){
				//wait for new connection and start a separate thread to handle that connection
				ConnectionHandlerThread reference = new ConnectionHandlerThread(server.accept());
				Server.addConnection(reference);
				reference.start();
			}
				

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-2); //unknown reason TODO: implement error handling if time permits
		}
		
		System.out.println("Server exited.");
	}

	
	
	
}
