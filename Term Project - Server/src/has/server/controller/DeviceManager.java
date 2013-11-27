package has.server.controller;

import has.server.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.pi4j.gpio.extension.piface.PiFaceGpioProvider;
import com.pi4j.gpio.extension.piface.PiFacePin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.Spi;


public class DeviceManager implements DeviceInterface{

	private final GpioController gpio;
	private final List<GpioPinDigitalOutput> myOutputs = new ArrayList<GpioPinDigitalOutput>();
	
	//device manager constructor
	public DeviceManager() throws IOException {
		gpio = GpioFactory.getInstance();
        

        PiFaceGpioProvider gpioProvider;
		
		gpioProvider = new PiFaceGpioProvider(PiFaceGpioProvider.DEFAULT_ADDRESS,Spi.CHANNEL_0);
		
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_00));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_01));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_02));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_03));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_04));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_05));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_06));
		myOutputs.add(gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_07));
        

        GpioPinDigitalInput myInput = gpio.provisionDigitalInputPin(gpioProvider, PiFacePin.INPUT_04);
        //final GpioPinDigitalOutput myOutput = gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_00);      
        
        // create and register gpio pin listener
        gpio.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                //System.out.println("MOTION DETECTED!!!"); //this is where we send an interrupt saying there is motion right haydar?
				 //gpio.setState(true, myOutput);
				 //Thread.sleep(2);
            	Server.model.setMotionSensorStatus(true);
				 
            }
        }, myInput);
        
	}
	
	
	/**
	 * Returns a boolean flag indiciating whether its successful or not.
	 */
	@Override
	public boolean turnLed(int ledId, boolean on) {
		
		
		//put code here and return false if failed
		
		
		//update model
		switch(ledId)
		{
		case 1:
			gpio.setState(on, myOutputs.get(3));
			Server.model.setLed1Status(on);
			break;
		case 2:
			Server.model.setLed2Status(on);
			break;
		case 3:
			Server.model.setLed3Status(on);
			break;
			
		}
		
		return true;
	}

	@Override
	public double readTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
