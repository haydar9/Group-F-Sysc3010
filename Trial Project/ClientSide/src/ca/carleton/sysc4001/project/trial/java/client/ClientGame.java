package ca.carleton.sysc4001.project.trial.java.client;

import ca.carleton.sysc4001.project.trial.java.utility.CommunicationMessages;

/**
 * The client side of the game.
 * @author cassandra
 * @author haydar
 *
 */
public class ClientGame 
{
	//welcome message
	private static String WELCOME_MESSAGE = "Welcome to Jeopardy!";
		
	private String playerName;
	
	private void playGame()
	{
		//welcomeMessage();
		
		System.out.println("End of game");
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

