package has.client.gui.view;

import has.client.connection.ConnectionManager;
import has.client.model.Model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author Ziad Skaik
 * @date 2013-11-20
 */
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
		//this.setResizable(false);

	    DefaultTableModel dm = new DefaultTableModel();
	    dm.setDataVector(new Object[][] { { "Temperature Sensor", "OFF","25C","OFF" },
	        { "Motion Sensor", "OFF","4lm","OFF" } }, new Object[] { "Component", "Status","Value","Control" });

	    JTable table = new JTable(dm);
	    table.getColumn("Control").setCellRenderer(new ButtonRenderer());
	    table.getColumn("Control").setCellEditor(
	      new ButtonEditor(new JCheckBox()));
	    JScrollPane scroll = new JScrollPane(table);
	    getContentPane().add(scroll);
	    setSize(400, 100);
	    setVisible(true);

		//content panel
		//contentPanel = new JPanel();
		
		//connectionValue
		//connectionValue = new JLabel("On Load value");
		
		//button = new JButton("Send to Server");
		
		//compositing
		//contentPanel.add(connectionLabel);
		//contentPanel.add(connectionValue);
		//contentPanel.add(button);
		
		
		//.addActionListener(new LedStateListener)
		//TODO: make the action listener in a separate class
	/*	button.addActionListener(new ActionListener() {
			
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
		*/
	}
	class ButtonRenderer extends JButton implements TableCellRenderer {

		  public ButtonRenderer() {
		    setOpaque(true);
		  }

		  public Component getTableCellRendererComponent(JTable table, Object value,
		      boolean isSelected, boolean hasFocus, int row, int column) {
		    if (isSelected) {
		      setForeground(table.getSelectionForeground());
		      setBackground(table.getSelectionBackground());
		    } else {
		      setForeground(table.getForeground());
		      setBackground(UIManager.getColor("Button.background"));
		    }
		    setText((value == null) ? "" : value.toString());
		    return this;
		  }
		}

	class ButtonEditor extends DefaultCellEditor {
		  protected JButton button;

		  private String label;

		  private boolean isPushed;

		  public ButtonEditor(JCheckBox checkBox) {
		    super(checkBox);
		    button = new JButton();
		    button.setOpaque(true);
		    button.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        fireEditingStopped();
		      }
		    });
		  }

		  public Component getTableCellEditorComponent(JTable table, Object value,
			      boolean isSelected, int row, int column) {
			    if (isSelected) {
			      button.setForeground(table.getSelectionForeground());
			      button.setBackground(table.getSelectionBackground());
			    } else {
			      button.setForeground(table.getForeground());
			      button.setBackground(table.getBackground());
			    }
			    label = (value == null) ? "" : value.toString();
			    
			    if(label.equals("ON")){
			    	label ="OFF";
			    	button.setText(label);
			    	table.getModel().setValueAt(label, row, column-2);
			    	//table.getModel().setValueAt("OFF", table.getY(), table.getX()-2);
			    }
			    
			    else{
			    	label = "ON";
			    	button.setText(label);
			    	table.getModel().setValueAt("ON", row, column-2);
			    	//table.getModel().setValueAt("OFF", table.getY(), table.getX()-2);
			    }
			    isPushed = true;
			    return button;
			  }

		  public Object getCellEditorValue() {
			    if (isPushed) {
			      // 
			      // 
			      //JOptionPane.showMessageDialog(button, label + ": Ouch!");
			      // System.out.println(label + ": Ouch!");
			    }
			    isPushed = false;
			    return new String(label);
			  }

			/*  public boolean stopCellEditing() {
			    isPushed = false;
			    return super.stopCellEditing();
			  }*-*/

			  protected void fireEditingStopped() {
			    super.fireEditingStopped();
			  }
			}


	
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
