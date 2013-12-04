package has.server.controller;


import has.server.Server;

import java.io.IOException;
import java.util.Scanner;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.Gertboard;
import com.pi4j.wiringpi.Spi;

public class DeviceManager extends Thread implements DeviceInterface{

	
	private static final Pin MOTION_SENSOR_PIN = RaspiPin.GPIO_06; //this is GPIO 25 on gertboard
	private static final Pin LED_1_PIN = RaspiPin.GPIO_03;
	private static final Pin LED_2_PIN = RaspiPin.GPIO_04;
	private static final Pin LED_3_PIN = RaspiPin.GPIO_05;
	private static final Pin MOTOR_PIN_A = RaspiPin.GPIO_01;
	private static final Pin MOTOR_PIN_B = RaspiPin.GPIO_00;
	private static final int ANALOG_CHANNEL = 1; //temperature sensor analog channel

	private final GpioController gpio;

	private final GpioPinDigitalOutput led1Pin;
	private final GpioPinDigitalOutput led2Pin;
	private final GpioPinDigitalOutput led3Pin;

	private final GpioPinDigitalOutput motorPinA;
	private final GpioPinDigitalOutput motorPinB;

	//sync objects
	private Object temperatureSensorLock;

	//device manager constructor
	public DeviceManager() throws IOException {


		temperatureSensorLock = new Object();
		
		
		//setup gertboard
		Gertboard.gertboardSPISetup();


		
		//get gpio control object
		gpio = GpioFactory.getInstance();


		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		// Set up motion sensor
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//get motion sensor pin reference
		final GpioPinDigitalInput motionSensorPin = gpio.provisionDigitalInputPin(MOTION_SENSOR_PIN, PinPullResistance.PULL_DOWN);

		// create and register motion sensor pin listener
		motionSensorPin.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				// display pin state on console
				if(event.getState().isLow())
					Server.model.setMotionSensorStatus(true); //update model
				else 
					Server.model.setMotionSensorStatus(false);

				//perhaps call a custom listener hre
				//RegisteredListener registeredListener = registeredListener;
				//registeredListener.invoke();
			}

		});


		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//update model with system initial state
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		/*Server.model.setFanStatus(false);
		Server.model.setLed1Status(false);
		Server.model.setLed2Status(false);
		Server.model.setLed3Status(false);
		Server.model.setMotionSensorStatus(false);*/

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//set up LEDs, it's better to create an interface to add/remove devices
		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		led1Pin = gpio.provisionDigitalOutputPin(LED_1_PIN);
		led1Pin.low(); //initially turn off led 1
		led2Pin = gpio.provisionDigitalOutputPin(LED_2_PIN);
		led2Pin.low(); //initially turn off led 1
		led3Pin = gpio.provisionDigitalOutputPin(LED_3_PIN);
		led3Pin.low(); //initially turn off led 1

		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//set up Motor
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		motorPinA = gpio.provisionDigitalOutputPin(MOTOR_PIN_A);
		motorPinB = gpio.provisionDigitalOutputPin(MOTOR_PIN_B);

		//initially motor not turning
		motorPinA.low();
		motorPinB.low();

		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//set up Temperature Sensor
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		this.start();
	}



	//task to read temperature every second
	@Override
	public void run() {
		while(true)
		{
			
			//update model with current temperature
			Server.model.setTemperatureValue(readTemperature());
			
			//sleep for one second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public double readTemperature()
	{
		synchronized(temperatureSensorLock)
		{
			int digitalInput = Gertboard.gertboardAnalogRead(ANALOG_CHANNEL);
			double temperature = (((digitalInput / 1023.0 ) * 3.3 ) - 0.5) / 0.01;
			
			return temperature;
		}
	}
		
	/**
	 * Returns a boolean flag indiciating whether its successful or not.
	 */
	public boolean turnLed(int ledId, boolean on) {
		//again this can be generalized for dynamic LED adding to the system
		//idea: provide an XML for setting up hardware ports
		//turn on led and update model
		switch(ledId)
		{
		case 1:
			led1Pin.setState(on);
			Server.model.setLed1Status(on);
			break;
		case 2:
			led2Pin.setState(on);
			Server.model.setLed2Status(on);
			break;
		case 3:
			led3Pin.setState(on);
			Server.model.setLed3Status(on);
			break;

		}

		return true;
	}


	public void turnMotor(boolean on){
		if(on) 
		{//turn on
			//can be extended to control direction of rotation
			//by switching the motor pins values below, motor rotate in other direction
			motorPinA.setState(true);
			motorPinB.setState(false);
			Server.model.setFanStatus(on);
		}
		else 
		{//turn off
			motorPinA.setState(false);
			motorPinB.setState(false);
			Server.model.setFanStatus(on);

		}
	}


	//for live test of the device manager
	public static void main(String[] args) throws IOException
	{
		DeviceManager deviceManager = new DeviceManager();
		Scanner scanner = new Scanner(System.in);
		String input;
		while(true) {
			input = scanner.next();
			//provide a console to debug and manually run hardware methods
			switch(input)
			{
			case "led1 on":
				deviceManager.turnLed(1, true);
				break;
			case "led1 off":
				deviceManager.turnLed(1, false);
				break;
			}

		}
	}

}
