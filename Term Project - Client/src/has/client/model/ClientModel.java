package has.client.model;

import java.util.Observable;

/**
 * GUI application or client specific model.
 * 
 * @author haydar
 *
 */
public class ClientModel extends Observable{
	
	
	public static ClientModel instance;
	
	//static initializer
	static {
		instance = new ClientModel();
	}
	
	private boolean led1Status;

	private ClientModel()
	{
		
	}
	
	
	//getters and setters
	
	public boolean isLed1Status() {
		return led1Status;
	}

	public void setLed1Status(boolean led1Status) {
		this.led1Status = led1Status;
		notifyObservers();
	}
	
	
}
