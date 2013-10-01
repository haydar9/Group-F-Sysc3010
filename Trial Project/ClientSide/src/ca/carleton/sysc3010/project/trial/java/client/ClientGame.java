package ca.carleton.sysc3010.project.trial.java.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import ca.carleton.sysc3010.project.trial.java.utility.CommunicationMessages;

/**
 * The client side of the game.
 * @author cassandra
 * @author haydar
 *
 */
public class ClientGame extends Thread
{
	//welcome message
	private static String WELCOME_MESSAGE = "Welcome to Jeopardy!";
		
	private String playerName;
	private String file;
	Client client;
	
	public ClientGame(Client client)
	{
		this.client = client;
	}
	private void playGame()
	{
		//welcomeMessage();
		
		System.out.println("End of game");
	}
	
	public void run()
	{
		try{
			Process p;
			p = Runtime.getRuntime().exec("python pythontest.py");
			
			InputStreamReader isr = new InputStreamReader(p.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			PrintWriter out = new PrintWriter(p.getOutputStream(), true);
			String temp = null;
			
			while((temp=br.readLine())!=null)
			{	
				if(temp.equals("0")){
					client.sendMessage(CommunicationMessages.Client.BUTTON_PRESSED);
					System.out.println("buttonpressed");
				}
			}
			System.out.println("Python script terminated.");	

			}catch(Exception e){
				e.printStackTrace();
			}		
	}
	 
	/**
	 * @param input the input to process
	 * @return output the command to send back to the server
	 * after processing the server
	 */
	public String processCommand(String input)
	{
		if(input.equals(CommunicationMessages.Server.WAIT))
		{
			return CommunicationMessages.Client.CLIENT_WAITING;
		}
		else if(input.equals(CommunicationMessages.Server.WHO_ARE_YOU))
		{
			return getPlayerName();
			
		}
		else if(input.equals(CommunicationMessages.Server.WHAT_ARE_YOU))
		{
			return CommunicationMessages.Client.Type.PLAYER;
		}
		else if(input.equals(CommunicationMessages.Server.WELCOME_PLAYER))
		{
			System.out.println(WELCOME_MESSAGE);
			return CommunicationMessages.DONT_SEND_FEEDBACK;
		}
		else if(input.equals(CommunicationMessages.Server.GAME_START))
		{
			this.start();
		    return CommunicationMessages.DONT_SEND_FEEDBACK;
		}
		else
		{
			return CommunicationMessages.Client.I_DONT_KNOW;
		}
		
		
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	private void askQuestions()
	{
		System.out.println();
		System.out.println("QUESTION HERE");
	}
	
	
}

