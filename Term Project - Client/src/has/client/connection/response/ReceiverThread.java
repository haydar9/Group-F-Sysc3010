package has.client.connection.response;

import has.client.Client;
import has.client.connection.ConnectionManager;
import has.client.connection.Handler;


public class ReceiverThread extends Thread {

	private ConnectionManager connectionManager;
	
	public ReceiverThread(ConnectionManager connectionManager)
	{
		this.connectionManager = connectionManager;
	}
	
	@Override
	public void run() {
		String input;
		boolean toggle = true;
		while((input = connectionManager.receieveMessage()) != null)
		{
			Handler.handleResponse(input);
			
			//this is just testing code
			if(input.equals("ACK")){
				if(toggle)
				{	
					System.out.println("Updating Model with: 'ON'");
					Client.model.setFanStatus(toggle);
					toggle = false;
				}
				else{
					System.out.println("Updating Model with: 'OFF'");
					Client.model.setFanStatus(toggle);
					toggle = true;
				}
			}
			else if(input.contains("PeriodicUpdate")){
				System.out.println("Periodic Update Response: " + input);
				
			}
		}
	}
}
