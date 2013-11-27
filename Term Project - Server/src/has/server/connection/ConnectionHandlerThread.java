package has.server.connection;

import has.server.Server;
import has.server.management.HomeAutomationSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Time;

public class ConnectionHandlerThread extends Thread{

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public ConnectionHandlerThread(Socket socket) throws IOException
	{
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		System.out.println("Connection Established with: " + socket.getRemoteSocketAddress().toString());
	}



	@Override
	public void run() {
		String request;

		try {
			while((request = receive())!= null)
			{
				System.out.println(new Time(System.currentTimeMillis()) + ": " + socket.getInetAddress() + "\tFrom Client:\t" + request);
				HomeAutomationSystem.instance.addRequest(request);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		//clean up in case error reading
		Server.removeConnection(this);
	}



	public String receive() throws IOException
	{
		return in.readLine();
	}
	
	public void send(String xmlToSend)
	{
		out.println(xmlToSend);
	}

}
