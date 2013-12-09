Trial: RPI-Game
========

A game project using Raspberry Pi, along with Gertboard and PiFace chips.

How to use: (Alpha 0.1)

1- Run server, the default port is hard coded for now as 4444.<br/>
2- Run Three Clients, passing your name as command line argument.<br/>
3- The server will wait for three clients to connect then will start game.<br/>

Note: The server will only send message but not start game (not yet implpemented).

Term Project:

A Home Automation System... where a raspberry pi is connected to sensors and leds, 
acting as a household lights and motion/temperature sensor. 



Term Project: README file
==========

1- Setup hardware connection according to the mapping diagram <br/>
2- Run the server.jar with super user on the RPi e.g. "sudo server.jar" <br/>
   Options: The user can specify port to host the sever on e.g. "sudo server.jar 1234" <br/>
3- Run the client.jar on another device. Must have a display otherwise the gui will not work. <br/>
   Client Does not require super user. e.g. "client.jar" </br>
   Options: Can specify IP and port to connect to as command line argument, e.g. "client.jar 10.0.0.3 4444" <br/>
