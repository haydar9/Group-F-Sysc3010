//import java.io.*;
import java.net.*;
import java.io.*;

public class Client{	

    public static void main(String[] args) throws IOException {
    	//hard coded host
    	//String host = "localhost-dawn";//haydar machine
    	String host = "localhost";//with cu-wireless
    	int port = 4444;
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + host);
            System.exit(1);
        }

	BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
	System.out.println("Enter your player name: ");	
	String userInput=null;
	
	while(userInput==null){userInput = stdIn.readLine();}
	String username = userInput;
	out.println(username+ " is connected");
	System.out.println("Waiting for other players...");
	System.out.println(in.readLine());
	//write the link to server, TODO: uncomment when not testing with apache server
	//out.write("GET /ServerSide/ HTTP/1.0\r\n\r\n");
	//userInput==null;
	while (userInput!= null) {
	    if(userInput.equals("shutdown client")){
	    	break;
				
	    //out.println(userInput);
	    //System.out.println("server: " + in.readLine());
	}
	    else{userInput=stdIn.readLine();};		
	}

	out.close();
	in.close();
	stdIn.close();
	socket.close();
    }
}
