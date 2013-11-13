package has.client.connection;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {
	
	private boolean led1Status;
	private boolean led2Status;
	private boolean led3Status;
	private boolean fanStatus;
	private boolean motionSensorStatus;
	
	public boolean isLed1Status() {
		return led1Status;
	}
	
	@XmlElement
	public void setLed1Status(boolean led1Status) {
		this.led1Status = led1Status;
	}
	
	public boolean isLed2Status() {
		return led2Status;
	}
	
	@XmlElement
	public void setLed2Status(boolean led2Status) {
		this.led2Status = led2Status;
	}
	
	public boolean isLed3Status() {
		return led3Status;
	}
	
	@XmlElement
	public void setLed3Status(boolean led3Status) {
		this.led3Status = led3Status;
	}
	
	public boolean isFanStatus() {
		return fanStatus;
	}
	
	@XmlElement
	public void setFanStatus(boolean fanStatus) {
		this.fanStatus = fanStatus;
	}
	
	public boolean isMotionSensorStatus() {
		return motionSensorStatus;
	}
	
	@XmlElement
	public void setMotionSensorStatus(boolean motionSensorStatus) {
		this.motionSensorStatus = motionSensorStatus;
	}
	
}
