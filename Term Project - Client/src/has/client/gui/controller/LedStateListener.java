package has.client.gui.controller;

import has.client.model.ClientModel;

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

	private int ledId;
	private ClientModel clientModel;
	
	public LedStateListener(int ledId)
	{
		this.ledId = ledId;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//1- update model
		ClientModel.instance.setLed1Status(true); //true for on
		//2- 
	}

}
