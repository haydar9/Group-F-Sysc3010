package has.client;

import has.client.connection.ConnectionManager;
import has.client.connection.XmlHandler;


public class ReceiverThread extends Thread {

	private ConnectionManager connectionManager;
	
	public ReceiverThread(ConnectionManager connectionManager)
	{
		this.connectionManager = connectionManager;
	}
	
	@Override
	public void run() {
		String input;
		while((input = connectionManager.receieveMessage()) != null)
		{
			XmlHandler.handleXml(input);
			
			
		}
	}
}
