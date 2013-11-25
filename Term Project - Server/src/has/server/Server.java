package has.server;

import has.server.connection.XmlHandler;
import has.server.management.HomeAutomationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.Random;

public class Server {

	public final static int DEFAUL_PORT = 4444;
	
	public static void main(String args[])
	{
		
		int port = Server.DEFAUL_PORT;
		ServerSocket server;
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		HomeAutomationSystem has = new HomeAutomationSystem();
		
		try {
			server = new ServerSocket(port);
			System.out.println("Server started successfully");
			System.out.println("Waiting for connection...");
			
			clientSocket = server.accept();
			
			System.out.println("Connection Established with: " + clientSocket.getRemoteSocketAddress().toString());
			
			 out = new PrintWriter(clientSocket.getOutputStream(), true);
	         in = new BufferedReader(new InputStreamReader(
	        		 clientSocket.getInputStream()));
	            
			
			String request;
			int i = 0;
			Random r = new Random();
			while((request = in.readLine())!= null)
			{
				System.out.println(new Time(System.currentTimeMillis()) + ":\tFrom Client:\t" + request);
				//TODO:uncomment once implemented
				//out.println(has.handleRequest(request));
				
				/*try {
					Thread.sleep(r.nextInt(5000));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				String testResponse = XmlHandler.generateLEDUpdate(true, "1");
				System.out.println(new Time(System.currentTimeMillis()) + ":\tTo Client:\t" + testResponse);
				out.println(testResponse);
				//testing code: replace once above code is implemented
			/*	 if (request.equals("ButtonClicked"))
				{
					 
					System.out.println(new Time(System.currentTimeMillis()) + ":\tTo Client:\t" + "ACK");
					out.println("ACK");
				}
				 else if (request.equals("PeriodicUpdate"))
				 {
					 	String sendBack = "PeriodicUpdate" + i++;
						System.out.println(new Time(System.currentTimeMillis()) + ":\tTo Client:\t" + sendBack);
						out.println(sendBack);
				 }
*/			}

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Server exited.");
	}
}
