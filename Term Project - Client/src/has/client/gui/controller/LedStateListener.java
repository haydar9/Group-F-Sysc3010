package has.client.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The LED button handler:
 * 1 - send request to server to update it's model by using connection manager
 * 2 - update client model with updated model sent back from server
 * @author haydar
 * @author ziad
 */
public class LedStateListener implements ActionListener {

	private int ledId;
	
	public LedStateListener(int ledId)
	{
		this.ledId = ledId;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
