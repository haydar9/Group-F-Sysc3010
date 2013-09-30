package ca.carleton.sysc3010.project.trial.java.server.game;

import java.util.ArrayList;
import java.util.List;

import ca.carleton.sysc3010.project.trial.java.server.ClientConnection;
import ca.carleton.sysc3010.project.trial.java.server.Server;
import ca.carleton.sysc3010.project.trial.java.utility.CommunicationMessages;

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
	 * Assuming players are already connected.
	 * @param input
	 */
	public synchronized String processInput(String input)
	{
		return null;	
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
	
	/**
	 * 
	 */
	public synchronized void startGame(ClientConnection cc)
	{
		if(playerList.size()<3){
			System.out.println("Waiting for " + (3-playerList.size()) + " more players...");
			cc.sendMessage(CommunicationMessages.Server.DISPLAY);
			cc.sendMessage("Waiting for " + (3-playerList.size()) + " more players...");
			try {
				wait();
			} catch (InterruptedException e) {	}
		}
		else if(playerList.size()==3){
			System.out.println("Three players connected: Game Starting...");
			cc.sendMessage(CommunicationMessages.Server.DISPLAY);
			cc.sendMessage("Three players connected: Game Starting...");
			//TODO: add anything related to initializing or setting up game here
			notifyAll();
		}
	}
	
}
