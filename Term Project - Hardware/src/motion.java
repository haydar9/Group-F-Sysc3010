 
  /*
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2013 Pi4J
 * %%
 */


import java.io.IOException;

import com.pi4j.gpio.extension.piface.PiFaceGpioProvider;
import com.pi4j.gpio.extension.piface.PiFacePin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.wiringpi.Spi;

  /*
   * Class to control motion sensor
   *@author Pierre Yammine
   *To compile:      javac -classpath .:classes:/opt/pi4j/lib/'*' motion.java
   *To run:	         sudo java -classpath .:classes:/opt/pi4j/lib/'*' motion.java
   */

public class motion {
    
    public static void main(String args[]) throws InterruptedException, IOException {
        
        System.out.println("Looking for motion...\n");
        

        final GpioController gpio = GpioFactory.getInstance();
        

        final PiFaceGpioProvider gpioProvider = new PiFaceGpioProvider(PiFaceGpioProvider.DEFAULT_ADDRESS,Spi.CHANNEL_0);
        

        GpioPinDigitalInput myInput = gpio.provisionDigitalInputPin(gpioProvider, PiFacePin.INPUT_04);
        final GpioPinDigitalOutput myOutput = gpio.provisionDigitalOutputPin(gpioProvider, PiFacePin.OUTPUT_00);      
        
        // create and register gpio pin listener
        gpio.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println("MOTION DETECTED!!!"); //this is where we send an interrupt saying there is motion right haydar?
				 //gpio.setState(true, myOutput);
				 //Thread.sleep(2);
				 
            }
        }, myInput);
        
        
        
      
        for (;;) {
        //gpio.setState(false, myOutput);              
        }
        
        
        //gpio.shutdown();                 
    }
}

