package has.client.gui.view;

import has.client.Client;
import has.client.connection.ConnectionManager;
import has.client.connection.XmlHandler;
import has.client.model.Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TestView extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2434448753266334469L;
	//constants
	private final static String TITLE = "Home Automation System Client";
	
	private final static JLabel MOTION_SENSOR_LABEL = new JLabel("Motion Sensor: ");
	private final static JLabel LED_1_LABEL = new JLabel("LED 1: ");
	private final static JLabel LED_2_LABEL = new JLabel("LED 2: ");
	private final static JLabel LED_3_LABEL = new JLabel("LED 3: ");
	private final static JLabel TEMPERATURE_SENSOR_LABEL = new JLabel("Temperature: ");
	private final static JLabel FAN_LABEL = new JLabel("Fan: ");

	//fields
	private JPanel contentPanel;
	

	private JButton led1ControlButton;
	private JButton led2ControlButton;
	private JButton led3ControlButton;
	private JButton fanControlButton;
	
	private JLabel temperatureSensorReading; 
	private JLabel motionSensorStatus; 
	private JLabel fanStatus; 
	private JLabel LED1Status; 
	private JLabel LED2Status; 
	private JLabel LED3Status; 
	
	private JTextArea motionSensorHistory;
	
	
	public TestView()
	
	{
		super(TITLE);
		
		//settings for JFrame
		//FIXME: this shutdowns JVM immediately along with running threads, 
		//need to control behavior for clean shutdown 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setPreferredSize(new Dimension(200,400));
		
		//content panel
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		//initialize buttons and JLabels
		motionSensorStatus = new JLabel("*");
		temperatureSensorReading = new JLabel("*");
		fanStatus = new JLabel("*");
		LED1Status = new JLabel("*");
		LED2Status = new JLabel("*");
		LED3Status = new JLabel("*"); 
		
		led1ControlButton = new JButton("Toggle");
		led2ControlButton = new JButton("Toggle");
		led3ControlButton = new JButton("Toggle");
		fanControlButton = new JButton("Toggle");
		
		motionSensorHistory = new JTextArea();
		motionSensorHistory.setEditable(false);
		
		//compositing
		JPanel led1Panel = new JPanel();
		led1Panel.add(LED_1_LABEL);
		led1Panel.add(LED1Status);
		led1Panel.add(led1ControlButton);
		
		JPanel led2Panel = new JPanel();
		led2Panel.add(LED_2_LABEL);
		led2Panel.add(LED2Status);
		led2Panel.add(led2ControlButton);
		
		JPanel led3Panel = new JPanel();
		led3Panel.add(LED_3_LABEL);
		led3Panel.add(LED3Status);
		led3Panel.add(led3ControlButton);
		
		JPanel fanPanel = new JPanel();
		fanPanel.add(FAN_LABEL);
		fanPanel.add(fanStatus);
		fanPanel.add(fanControlButton);
		
		JPanel motionSensorPanel = new JPanel();
		motionSensorPanel.add(MOTION_SENSOR_LABEL);
		motionSensorPanel.add(motionSensorStatus);
		
		JPanel temperatureSensorPanel = new JPanel();
		temperatureSensorPanel.add(TEMPERATURE_SENSOR_LABEL);
		temperatureSensorPanel.add(temperatureSensorReading);
		
		
		
		JScrollPane motionSensorHistoryScrollPanel = new JScrollPane(motionSensorHistory,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		contentPanel.add(led1Panel);
		contentPanel.add(led2Panel);
		contentPanel.add(led3Panel);
		contentPanel.add(motionSensorPanel);
		contentPanel.add(temperatureSensorPanel);
		contentPanel.add(fanPanel);
		contentPanel.add(motionSensorHistoryScrollPanel);
		
		//controllers/action listeners
		led1ControlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String xmlRequest = XmlHandler.generateLEDControlRequest(!Client.model.isLed1Status(), "1"); //turn on LED
				
				if(xmlRequest != null)
					ConnectionManager.getInstance().sendMessage(xmlRequest);
			}
		});
		
		led2ControlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String xmlRequest = XmlHandler.generateLEDControlRequest(!Client.model.isLed2Status(), "1"); //turn on LED
				
				if(xmlRequest != null)
					ConnectionManager.getInstance().sendMessage(xmlRequest);
			}
		});
		
		led3ControlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String xmlRequest = XmlHandler.generateLEDControlRequest(!Client.model.isLed3Status(), "1"); //turn on LED
				
				if(xmlRequest != null)
					ConnectionManager.getInstance().sendMessage(xmlRequest);
			}
		});
		
		fanControlButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String xmlRequest = XmlHandler.generatefanControlRequest(!Client.model.isFanStatus()); //turn on LED
				
				if(xmlRequest != null)
					ConnectionManager.getInstance().sendMessage(xmlRequest);
			}
		});
		
		//final set up
		
		//set content panel
		this.setContentPane(contentPanel);
		
		//finally show GUI
		this.pack();
		this.setVisible(true);
		
		
		
		//centering the frame
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
				
				
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("View update");
		// TODO Auto-generated method stub
		Model model = (Model)arg0;
		if(model.isLed1Status())
			LED1Status.setText("ON");
		else 
			LED1Status.setText("OFF");
		
		if(model.isLed2Status())
			LED2Status.setText("ON");
		else 
			LED2Status.setText("OFF");
		if(model.isLed3Status())
			LED3Status.setText("ON");
		else 
			LED3Status.setText("OFF");
		
		if(model.isMotionSensorStatus()){
			motionSensorStatus.setText("Detected");
		}
		else {
			motionSensorStatus.setText("Still");
		}
		
		if(model.isFanStatus()){
			fanStatus.setText("ON");
		}
		else {
			fanStatus.setText("OFF");
		}
		
		temperatureSensorReading.setText(String.valueOf(model.getTemperatureValue()));
		motionSensorHistory.append(model.getMotionSensorHistory());
	}
	
	public static void main(String[] args)
	{
		new TestView();
	}
}
