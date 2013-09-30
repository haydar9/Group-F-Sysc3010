package ca.carleton.sysc4001.project.trial.java.server.game;

import java.util.ArrayList;
import java.util.List;

import ca.carleton.sysc4001.project.trial.java.server.Server;

/**
 * 
 * Piere implement your game logic here in run method
 *
 */
public class GameServerSide extends Thread{
	
	private List<Player> playerList;
	private Server server;
	
	/**
	 * Coupling, this coupling is safe since the game will not be created 
	 * until the server has successfully been created.  
	 * @param server
	 */
	public GameServerSide(Server server) {
		this.server = server;
		playerList = new ArrayList<Player>();
	}

	public void addPlayer(Player player)
	{
		playerList.add(player);	
	}
	
	/**
	 * The game processing is synchronous. A player request at a time.
	 * @param input
	 */
	public synchronized void processInput(String input)
	{
		
	}
	
	/**
	 * The main code to run for game, this is same as main method.
	 */
	@Override
	public void run()
	{
		if(playerList.size()<3){
			System.out.println("Need " + (3-playerList.size()) + " more players.");
		}
		else if(playerList.size()==3){
			System.out.println("Three players connected: Game Starting");
		}
	}
	

}
