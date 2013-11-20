import RPi.GPIO as GPIO
from time import sleep

led1 = 25
led2 = 24
led3 = 23

def initialize():
    GPIO.setmode(GPIO.BCM)      # initialise RPi.GPIO
    GPIO.setup(led1, GPIO.OUT)    # setup gpio for led1 as output
    GPIO.setup(led2, GPIO.OUT)    # same
    GPIO.setup(led3, GPIO.OUT)    # same
     
def turnLed1(on):
    try:
        if(on != 1 or on != 0 ):
            return
        GPIO.output(led1, on)
    except (KeyboardInterrupt, SystemExit):
        cleanUp()

def cleanUp():
    GPIO.cleanup() 