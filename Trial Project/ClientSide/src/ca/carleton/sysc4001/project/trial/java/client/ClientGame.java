package ca.carleton.sysc4001.project.trial.java.client;
//import java.util.Scanner;

/**
 * 
 * @author cassandra
 *
 */
public class ClientGame 
{
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
		if(input.equals(CassandraConstants.Server.WAIT))
		{
			return CassandraConstants.Client.CLIENT_WAITING;
		}
		else if(input.equals(CassandraConstants.Server.WHO_ARE_YOU))
		{
			return getPlayerName();
			
		}
		else if(input.equals(CassandraConstants.Server.WHAT_ARE_YOU))
		{
			return CassandraConstants.Client.Type.PLAYER;
		}
		else
		{
			return CassandraConstants.Client.I_DONT_KNOW;
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

