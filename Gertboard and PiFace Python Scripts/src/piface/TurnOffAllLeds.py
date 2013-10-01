 #
# Python script to turn off all LEDs
# @Pierre
#
import piface.pfio as pfio
pfio.init()
pfio.digital_write(0,0)
pfio.digital_write(1,0)
pfio.digital_write(2,0)
pfio.digital_write(3,0)
pfio.digital_write(4,0)
pfio.digital_write(5,0)
pfio.digital_write(6,0)
pfio.digital_write(7,0)
pfio.digital_write(8,0)