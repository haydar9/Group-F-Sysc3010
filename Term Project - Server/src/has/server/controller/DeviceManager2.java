

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

public class DeviceManager extends Thread{

	private static final Pin MOTION_SENSOR_PIN = RaspiPin.GPIO_06;
	private static final Pin LED_1_PIN = RaspiPin.GPIO_03;
	private static final Pin MOTOR_PIN_A = RaspiPin.GPIO_01;
	private static final Pin MOTOR_PIN_B = RaspiPin.GPIO_00;

	private final GpioController gpio;

	private final GpioPinDigitalOutput led1Pin;

	private final GpioPinDigitalOutput motorPinA;
	private final GpioPinDigitalOutput motorPinB;

	private double previousTemperature;

	//device manager constructor
	public DeviceManager() throws IOException {


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
					System.out.println("Motion Detected");
			}

		});


		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//set up LEDs
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		led1Pin = gpio.provisionDigitalOutputPin(LED_1_PIN);
		led1Pin.low(); //initially turn off led 1

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



	@Override
	public void run() {
		while(true)
		{
			int digitalInput = Gertboard.gertboardAnalogRead(1);
			System.out.println("DigitalInput: " + digitalInput);
			double temperature = (((digitalInput / 1023.0 ) * 3.3 ) - 0.5) / 0.01;
			if( temperature != previousTemperature)
			{
				//update model


				System.out.println("Temperature Changed: " + temperature);
			}
			previousTemperature = temperature;
			System.out.flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}



	/**
	 * Returns a boolean flag indiciating whether its successful or not.
	 */
	public boolean turnLed(int ledId, boolean on) {

		//turn on led and update model
		switch(ledId)
		{
		case 1:
			led1Pin.setState(on);
			// Server.model.setLed1Status(on);
			break;
		case 2:
			//led2Pin.setState(on);
			//.model.setLed2Status(on);
			break;
		case 3:
			//led3Pin.setState(on);
			//  Server.model.setLed3Status(on);
			break;

		}

		return true;
	}


	public void turnMotor(boolean on){
		if(on) 
		{//turn on
			motorPinA.setState(true);
			motorPinB.setState(false);
		}
		else 
		{//turn off
			motorPinA.setState(false);
			motorPinB.setState(false);

		}
	}


	public static void main(String[] args) throws IOException
	{
		DeviceManager deviceManager = new DeviceManager();
		Scanner scanner = new Scanner(System.in);
		String input;
		while(true) {
			input = scanner.next();

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
