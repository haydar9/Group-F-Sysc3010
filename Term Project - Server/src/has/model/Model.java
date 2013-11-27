package has.model;

import has.server.Server;
import has.server.connection.XmlHandler;

import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;


public class Model extends Observable{

	private boolean led1Status;
	private boolean led2Status;
	private boolean led3Status;
	private boolean fanStatus;
	private double temperatureValue;
	private boolean motionSensorStatus;


	public Model()
	{

	}


	//getters and setters

	public boolean isLed1Status() {
		return led1Status;
	}

	public void setLed1Status(boolean led1Status) {
		this.led1Status = led1Status;
		notifyObservers();
		//FIXME hard code test
		System.out.println("Model update");
		String output = XmlHandler.generateLEDUpdate(led1Status, "1");
		if(output != null)
			Server.broadcast(output);
	}


	public boolean isLed2Status() {
		return led2Status;
	}


	public void setLed2Status(boolean led2Status) {
		this.led2Status = led2Status;
		
		System.out.println("Model update");
		String output = XmlHandler.generateLEDUpdate(led2Status, "2");
		if(output != null)
			Server.broadcast(output);
	}


	public boolean isLed3Status() {
		return led3Status;
	}


	public void setLed3Status(boolean led3Status) {
		this.led3Status = led3Status;
		
		System.out.println("Model update");
		String output = XmlHandler.generateLEDUpdate(led3Status, "3");
		if(output != null)
			Server.broadcast(output);
	}


	public boolean isFanStatus() {
		return fanStatus;
	}


	public void setFanStatus(boolean fanStatus) {
		this.fanStatus = fanStatus;
	}


	public double getTemperatureValue() {
		return temperatureValue;
	}


	public void setTemperatureValue(double temperatureValue) {
		this.temperatureValue = temperatureValue;
	}


	public boolean isMotionSensorStatus() {
		return motionSensorStatus;
	}

	@XmlElement
	public void setMotionSensorStatus(boolean motionSensorStatus) {
		this.motionSensorStatus = motionSensorStatus;
		System.out.println("Model update");
		String output = XmlHandler.generateMotionSensorUpdate(motionSensorStatus);
		if(output != null)
			Server.broadcast(output);
	}





}
