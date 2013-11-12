
import RPi.GPIO as GPIO
import time


GPIO.setmode(GPIO.BCM)


GPIO_PIR = 7 #GPIO not pin number

print "Starting..."


GPIO.setup(GPIO_PIR,GPIO.IN)      #set pin as an input

currentstatus  = 0
previousstatus = 0

try:

  print "Waiting for PIR to settle ..."

  # Loop until PIR output is 0
  while GPIO.input(GPIO_PIR)==1:
    Current_State  = 0    

  print "Ready"     
    
  
  while True :
   
    #read input
    currentstatus = GPIO.input(GPIO_PIR)
   
    if currentstatus==1 and previousstatus==0:
      
      print "  Motion detected!"
     
      previousstatus=1
    elif currentstatus==0 and previousstatus==1:
      #no motion
      print "Ready (waiting for motion)"
      previousstatus=0
      
    # Wait 10 milliseconds
    time.sleep(0.01)      
      
except KeyboardInterrupt:
  print "Quit" 
  
  GPIO.cleanup() # Reset settings for gpio