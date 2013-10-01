#
# Python script to flash the LEDs
# @Pierre
# 

import piface.pfio as pfio
pfio.init()
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
    sleep(1)
    pfio.digital_write(0,0)
    pfio.digital_write(1,0)
    pfio.digital_write(2,0)
    pfio.digital_write(3,0)
    pfio.digital_write(4,0)
    pfio.digital_write(5,0)
    pfio.digital_write(6,0)
    pfio.digital_write(7,0)
    pfio.digital_write(8,0)
    sleep(1)