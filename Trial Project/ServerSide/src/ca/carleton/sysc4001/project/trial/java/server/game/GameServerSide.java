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
	}

	public void addPlayer(Player player)
	{
		playerList.add(player);	
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
