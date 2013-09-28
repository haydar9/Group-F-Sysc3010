/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Our RPI Game Server that will be run on start up or manually. 
 * @author haydar
 *
 */
public class RPIGameServer {
    public static void main(String[] args) throws IOException {
        ArrayList<Socket> clients = new ArrayList<Socket>();
	//clients = null;
	//clients = null;	
    	if(args.length == 0)
    	{
    		System.out.println("RPIGameServer usage: bla bla bla");
    	}
    	
    	//parse arguments 
    	
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("IP Address: " + serverSocket.getInetAddress());
            System.out.println("Local Port: " + serverSocket.getLocalPort());
            System.out.println("Local Socket Address: " + serverSocket.getLocalSocketAddress());
	    System.out.println("Need 3 players to start game");	
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }
        
        
        Socket clientSocket = null;
        /*try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }*/

       /* PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
        String inputLine;
       
        
        out.println("Hello from socket server: who's this?");
        System.out.println(in.readLine());*/
     	
	PrintWriter out=null;
        BufferedReader in=null;
	String inputLine=null;
	int waiting = 0;
	while(waiting<3){
	try {
	//clientSocket = serverSocket.accept();
	clients.add(serverSocket.accept());
	
 	//out = new PrintWriter(clients.get(waiting).getOutputStream(), true);
       in = new BufferedReader(new InputStreamReader(clients.get(waiting).getInputStream()));
	    //System.out.println(waiting);
            
	    waiting++;
	System.out.println(in.readLine());
	
	if(waiting==3){System.out.println("All players connected: Game Started");}
	else{System.out.println("Waiting for more players: Need "+(3-waiting)+" more");}

        
       
        
        //out.println("Hello from socket server: who's this??");
        
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

	}

        /*while ((inputLine = in.readLine()) != null) {
             out.println(inputLine);
             if (inputLine.equals("shutdown server"))
                break;
        }*/

	//enter game here... 3 people connected.
	boolean game = true;
	for(Socket q: clients){	
		out = new PrintWriter(q.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(q.getInputStream()));
	
		//System.out.println(q);
		out.println("Welcome to millionaire");	
		//System.out.println(in.readLine());
	}
	//System.out.println("Welcome to millionaire");
	
	while(game){/*game is in here*/}

        out.close();
        in.close();
        clientSocket.close();
        
        serverSocket.close();
    }
}
