package has.client.connection;

import has.client.Client;
import has.client.connection.request.Request;

import java.util.Map;



/**
 * The static xml tools methods
 * @author haydar
 *
 */
public class Handler {
 
	//fields
	private static boolean toggle;
	
	static {
		boolean toggle = true;
	}

	
	//handle response
	
	
	public static void handleResponse(String xml)
	{
		//TODO: Cassandra part, parse response here and update model
		//you can access model by using "Client.model", if u need anything to initialize put it in the static field on top
		//this is just testing code
		if(xml.equals("ACK")){
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
		else if(xml.contains("PeriodicUpdate")){
			System.out.println("Periodic Update Response: " + xml);
			
		}
	}
	
}
