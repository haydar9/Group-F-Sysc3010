package has.server.connection;

import java.net.Socket;

public class HandleConnectionThread extends Thread{

	private Socket socket;
	
	public HandleConnectionThread(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
	}
	

}
