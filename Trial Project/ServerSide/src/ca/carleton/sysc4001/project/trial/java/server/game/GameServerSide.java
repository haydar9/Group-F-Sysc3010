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
	
	
	public GameServerSide(Server server) {
		this.server = server;
	}

	public void addPlayer()
	{
		
	}
	
	/**
	 * The main code to run for game, this is same as main method.
	 */
	@Override
	public void run()
	{
		//use 
		//TODO: pierre add your code here
		//you want to check player list only.
	}
	

}
