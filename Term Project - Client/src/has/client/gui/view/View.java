package has.client.gui.view;

import has.client.model.Model;

import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

	private Model cm;
	
	
	public View(Model m)
	
	{
		//.addActionListener(new LedStateListener)
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		Model model = (Model)arg0;
	}
}
