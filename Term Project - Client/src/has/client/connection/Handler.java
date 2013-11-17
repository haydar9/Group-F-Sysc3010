package has.client.connection;

import has.client.connection.request.Request;

import java.util.Map;



/**
 * The static xml tools methods
 * @author haydar
 *
 */
public class Handler {
 
	//fields
	
	
	//initialize here
	static {
		
	}
	
	//Ignore this for now
	public static String generateXml(Request request)
	{
		Map<String, Object> attributesMap = request.getAttributesMap();
		//TODO: straightforward for every key that would be node and the value would be value of node or something like that 
		return null;
	}
	
	public static void handleResponse(String xml)
	{
		//TODO: Cassandra part, parse response here and update model
		//you can access model by using "Client.model", if u need anything to initialize put it in the static field on top
		
	}
	
}
