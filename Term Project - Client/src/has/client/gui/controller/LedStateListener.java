package has.client.gui.controller;

import has.client.model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The LED button handler:
 * 1- update model on the client
 * 2- send to server to update by using connection manager
 * @author haydar
 * @author ziad
 */
public class LedStateListener implements ActionListener {

	
	public LedStateListener()
	{
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//1- update model
		Model.getInstance().setLed1Status(true); //true for on
		//2- 
	}

}
