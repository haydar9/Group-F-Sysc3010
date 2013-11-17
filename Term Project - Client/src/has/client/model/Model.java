package has.client.model;

import java.util.Observable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * GUI application or client specific model.
 * 
 * @author haydar
 *
 */
@XmlRootElement
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
	}


	public boolean isLed2Status() {
		return led2Status;
	}


	public void setLed2Status(boolean led2Status) {
		this.led2Status = led2Status;
	}


	public boolean isLed3Status() {
		return led3Status;
	}


	public void setLed3Status(boolean led3Status) {
		this.led3Status = led3Status;
	}


	public boolean isFanStatus() {
		return fanStatus;
	}


	public void setFanStatus(boolean fanStatus) {
		this.fanStatus = fanStatus;
		setChanged();
		notifyObservers();
	}


	public double getTemperatureValue() {
		return temperatureValue;
	}


	public void setTemperatureValue(double temperatureValue) {
		this.temperatureValue = temperatureValue;
		setChanged();
		notifyObservers();
	}


	public boolean isMotionSensorStatus() {
		return motionSensorStatus;
	}

	@XmlElement
	public void setMotionSensorStatus(boolean motionSensorStatus) {
		this.motionSensorStatus = motionSensorStatus;
		setChanged();
		notifyObservers();
	}



	
}
