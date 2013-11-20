package has.client.gui.view;

import has.client.connection.ConnectionManager;
import has.client.model.Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JFrame implements Observer {

	//constants
	private final static String TITLE = "Home Automation System Client";
	private final static JLabel connectionLabel = new JLabel("Connection Status: ");
	
	//fields
	private JPanel contentPanel;
	
	private JLabel connectionValue;
	private JButton button;
	
	
	public View()
	
	{
		super(TITLE);
		
		//settings for JFrame
		//FIXME: this shutdowns JVM immediately along with running threads, 
		//need to control behavior for clean shutdown 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//content panel
		contentPanel = new JPanel();
		
		//connectionValue
		connectionValue = new JLabel("On Load value");
		
		button = new JButton("Send to Server");
		
		//compositing
		contentPanel.add(connectionLabel);
		contentPanel.add(connectionValue);
		contentPanel.add(button);
		
		
		//.addActionListener(new LedStateListener)
		//TODO: make the action listener in a separate class
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ConnectionManager.getInstance().sendMessage("ButtonClicked");
			}
		});
		
		
		//final set up
		
		//set content panel
		this.setContentPane(contentPanel);
		
		//centering the frame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//finally show GUI
		this.pack();
		this.setVisible(true);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("View updating");
		// TODO Auto-generated method stub
		Model model = (Model)arg0;
		if(model.isFanStatus())
			connectionValue.setText("ON");
		else 
			connectionValue.setText("OFF");
		//temperatureTextfield.value = model.getTemperatureValue();
	}
}
