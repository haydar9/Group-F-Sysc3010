package has.server.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class DeviceManager implements DeviceInterface{

	private static final String GERTBOARD_LED_INTERFACE = "gertboardLedInterface.py";
	private ScriptEngine engine;
	
	public DeviceManager() throws FileNotFoundException, ScriptException
	{
		//python interpreter
		engine = new ScriptEngineManager().getEngineByName("python");
		engine.eval(new FileReader(GERTBOARD_LED_INTERFACE));
		
	}
	
	/**
	 * Returns a boolean flag indiciating whether its successful or not.
	 */
	@Override
	public boolean turnLed(int ledId, boolean status) {
		try {
			switch(ledId) {
			case 1: 
				engine.eval("turnLed1(" + (status ? 1 : 0) + ")");
				break;
			case 2:
				//put other leds here, unless using same trick as above to get rid of switch block
				break;
			}
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	@Override
	public double readTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
