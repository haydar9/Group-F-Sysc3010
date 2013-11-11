package has.client.gui.view;

import has.client.model.ClientModel;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

	private ClientModel cm;
	
	
	public View(ClientModel m)
	
	{
		//.addActionListener(new LedStateListener)
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		ClientModel model = (ClientModel)arg0;
	}
}
