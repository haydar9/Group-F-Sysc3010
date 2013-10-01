 #
# Python script to turn on all LEDs
# @Pierre
#
import piface.pfio as pfio
pfio.init()

pfio.digital_write(0,1)
pfio.digital_write(1,1)
pfio.digital_write(2,1)
pfio.digital_write(3,1)
pfio.digital_write(4,1)
pfio.digital_write(5,1)
pfio.digital_write(6,1)
pfio.digital_write(7,1)
pfio.digital_write(8,1)