package has.client.gui.view;

import has.client.connection.ConnectionManager;
import has.client.connection.XmlHandler;
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

public class TestView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2434448753266334469L;
	//constants
	private final static String TITLE = "Home Automation System Client";
	private final static JLabel connectionLabel = new JLabel("Connection Status: ");
	
	//fields
	private JPanel contentPanel;
	
	private JLabel connectionValue;
	private JButton button;
	
	//for testing
	private boolean toggle;
	
	public TestView()
	
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
				String xmlRequest = XmlHandler.generateLEDControlRequest(toggle, "1"); //turn on LED
				toggle = !toggle;//test code
				if(xmlRequest != null)
					ConnectionManager.getInstance().sendMessage(xmlRequest);
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
		System.out.println("View update");
		// TODO Auto-generated method stub
		Model model = (Model)arg0;
		if(model.isLed1Status())
			connectionValue.setText("ON");
		else 
			connectionValue.setText("OFF");
		//temperatureTextfield.value = model.getTemperatureValue();
	}
}
