#
# Python script to wait until certain button is pressed
# returns button pressed and turns on a light to indicate it (for testing) 
# @Pierre
# 
import piface.pfio as pfio

pfio.init()

button = pfio.read_input()
while button == 0:
    button = pfio.read_input()
pin_number = pfio.get_pin_number(button)
pfio.digital_write(0,1)
return pin_number