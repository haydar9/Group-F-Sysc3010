#
# Python methods to control LEDs and huttons on PiFace
# @Pierre
#

import piface.pfio as pfio

#function to flash all the LEDS every x seconds
def FlashLights(x):
        ResetLights()
        while(True):
            pfio.digital_write(0,1)
            pfio.digital_write(1,1)
            pfio.digital_write(2,1)
            pfio.digital_write(3,1)
            pfio.digital_write(4,1)
            pfio.digital_write(5,1)
            pfio.digital_write(6,1)
            pfio.digital_write(7,1)
            pfio.digital_write(8,1)
            sleep(x)
            pfio.digital_write(0,0)
            pfio.digital_write(1,0)
            pfio.digital_write(2,0)
            pfio.digital_write(3,0)
            pfio.digital_write(4,0)
            pfio.digital_write(5,0)
            pfio.digital_write(6,0)
            pfio.digital_write(7,0)
            pfio.digital_write(8,0)
            sleep(x)
#function to reset lights and buttons (essentially turns off the LEDS)           
def ResetLights():
    pfio.init()
    
#turn on specified LED    
def TurnOnLed(x):
    pfio.digital_write(x,1)

#turn off specified LED    
def TurnOffLed(x):
    pfio.digital_write(x,0)

#function that waits until button x is pressed and then returns true    
def ButtonPressed(x):
    pfio.init()
    pressed = pfio.digital_read(x)
    while(pressed!=1):
        pressed = pfio.digital_read(x)
    if(pressed==1):
        return True 
    
#function that waits until any button is pressed
#returns the button that is pressed
def AnyButtonPressed():
    button = pfio.read_input()
    while button == 0:
        button = pfio.read_input()
    pin_number = pfio.get_pin_number(button)
    return pin_number
        
