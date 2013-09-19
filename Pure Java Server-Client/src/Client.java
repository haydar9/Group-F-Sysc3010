//import java.io.*;
import java.net.*;
import java.io.*;


public class Client {
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
	String userInput;

	out.println("This is client 1.");
	System.out.println(in.readLine());
	//write the link to server, TODO: uncomment when not testing with apache server
	//out.write("GET /ServerSide/ HTTP/1.0\r\n\r\n");
	
	while ((userInput = stdIn.readLine()) != null) {
	    if(userInput == "shutdown client")
	    	break;
		out.println(userInput);
	    System.out.println("server: " + in.readLine());
	}

	out.close();
	in.close();
	stdIn.close();
	socket.close();
    }
}