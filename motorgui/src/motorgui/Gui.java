/**
 * 
 */
package motorgui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * @author mustafa
 *
 */
public class Gui {

	private JFrame frame;
	private JButton savebutton;
	private JButton loadbutton;
	private JLabel serialnumberlabel;
	private JTextField serialnumberinfo;
	private JLabel rightspeedlabel;
	private JLabel rightspeedinfo;
	private JLabel leftspeedlabel;
	private JLabel leftspeedinfo;
	private JButton connectbutton;
	private JButton exitbutton;
	private JLabel selectdevicelabel;
	private JLabel rightpolecountlabel;
	private JTextField rightpolecountinfo;
	private JLabel leftpolecountlabel;
	private JTextField leftpolecountinfo;
	private JLabel rightspeedcoeflabel;
	private JTextField rightspeedcoefinfo;
	private JLabel leftspeedcoeflabel;
	private JTextField leftspeedcoefinfo;
	private JComboBox<String> selectdevicelist;
	public ArrayList<String> devicelist;
	private JLabel rightcurrentcoeflabel;
	private JTextField rightcurrentcoefinfo;
	private JLabel leftcurrentcoeflabel;
	private JTextField leftcurrentcoefinfo;
	private JLabel communicationratelabel;
	private JTextField communicationrateinfo;
	private JLabel kprightlabel;
	private JTextField kprightinfo;
	private JLabel kirightlabel;
	private JTextField kirightinfo;
	private JLabel kpleftlabel;
	private JTextField kpleftinfo;
	private JLabel kileftlabel;
	private JTextField kileftinfo;
	private JLabel windupleftlabel;
	private JTextField windupleftinfo;
	private JLabel winduprightlabel;
	private JTextField winduprightinfo;
	public int selecteddeviceindex;
	public boolean deviceselected;
	private JLabel globalerrorlabel;
	private JLabel globalerrorinfo;
	private JLabel batterylabel;
	private JLabel batteryinfo;
	private JLabel leftcurrentlabel;
	private JLabel leftcurrentinfo;
	private JLabel rightcurrentlabel;
	private JLabel rightcurrentinfo;
	private JLabel temperaturelabel;
	private JLabel temperatureinfo;
	private JLabel rightspeedsetlabel;
	private JSlider rightspeedsetinfo;
	private JLabel leftspeedsetlabel;
	private JSlider leftspeedsetinfo;
	private JLabel rightspeedsetrpmlabel;
	private JLabel leftspeedsetrpmlabel;
	protected int rightspeedset;
	protected int leftspeedset;
	protected boolean setspeedschanged;
	private JButton stopmotorsbutton;
	protected boolean actionload;
	protected boolean actionsave;
	public int rightpolecountset;
	public int leftpolecountset;
	public int rightspeedcoefset;
	public int leftspeedcoefset;
	public int rightcurrentcoefset;
	public int leftcurrentcoefset;
	public int communicationrateset;
	public int rightkpset;
	public int leftkpset;
	public int rightkiset;
	public int leftkiset;
	public int rightwindupset;
	public int leftwindupset;
	public int serialnumberset;
	public int modesset;
	
	
	/**
	 * 
	 */
	public Gui() {
		// TODO Auto-generated constructor stub
	}
	public void start() {
       frame = new JFrame("Sinusoidal Driver GUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(700,640);
       Container northpanel = new JPanel();
       Container southpanel = new JPanel();
       Container westpanel = new JPanel();
       Container eastpanel = new JPanel();
       Container centerpanel = new JPanel();
       Container topblockpanel1 = new JPanel();
       Container topblockpanel2 = new JPanel();
       Container topblockpanel3 = new JPanel();
       Container topblockpanel4 = new JPanel();
       Container topblockpanel5 = new JPanel();
       Container topblockpanel6 = new JPanel();
       Container topblockpanel7 = new JPanel();
       Container topblockpanel8 = new JPanel();
       
       connectbutton = new JButton("Connect");
       loadbutton = new JButton("Load");
       savebutton = new JButton("Save");
       exitbutton = new JButton("Exit");
       selectdevicelabel = new JLabel("Select Device"); 
       devicelist = new ArrayList<String>();
       selectdevicelist = new JComboBox<String>(); 
       serialnumberlabel = new JLabel("Serial Number");  
       serialnumberinfo = new JTextField("00000000");  
       globalerrorlabel = new JLabel("Global Error Status");  
       globalerrorinfo = new JLabel("00000000"); 
       batterylabel = new JLabel("Battery Status (mV)");  
       batteryinfo = new JLabel("00000000");
       temperaturelabel = new JLabel("Temperature Status (C)");  
       temperatureinfo = new JLabel("00000000");  
       communicationratelabel = new JLabel("Communication Rate (Hz)");  
       communicationrateinfo = new JTextField("10");  
       
       leftspeedlabel = new JLabel("Left Speed (rpm)");  
       leftspeedinfo = new JLabel("00000000");   
       leftcurrentlabel = new JLabel("Left Current (mA)");  
       leftcurrentinfo = new JLabel("00000000");   
       leftpolecountlabel = new JLabel("Left Pole Count");  
       leftpolecountinfo = new JTextField("15");  
       leftspeedcoeflabel = new JLabel("Left Speed Filter Coef");  
       leftspeedcoefinfo = new JTextField("1"); 
       leftcurrentcoeflabel = new JLabel("Left Current Filter Coef");  
       leftcurrentcoefinfo = new JTextField("12"); 
       kpleftlabel = new JLabel("Kp Left");  
       kpleftinfo = new JTextField("655"); 
       kileftlabel = new JLabel("Ki Left");  
       kileftinfo = new JTextField("12"); 
       windupleftlabel = new JLabel("Windup Left");  
       windupleftinfo = new JTextField("100000000"); 
       
       rightspeedlabel = new JLabel("Right Speed (rpm)");  
       rightspeedinfo = new JLabel("00000000");  
       rightcurrentlabel = new JLabel("Right Current (mA)");  
       rightcurrentinfo = new JLabel("00000000");   
       rightpolecountlabel = new JLabel("Right Pole Count");  
       rightpolecountinfo = new JTextField("15");  
       rightspeedcoeflabel = new JLabel("Right Speed Filter Coef");  
       rightspeedcoefinfo = new JTextField("1");  
       rightcurrentcoeflabel = new JLabel("Right Current Filter Coef");  
       rightcurrentcoefinfo = new JTextField("12");   
       kprightlabel = new JLabel("Kp Right");  
       kprightinfo = new JTextField("655"); 
       kirightlabel = new JLabel("Ki Right");  
       kirightinfo = new JTextField("12"); 
       winduprightlabel = new JLabel("Windup Right");  
       winduprightinfo = new JTextField("100000000"); 

       rightspeedsetlabel = new JLabel("Right Speed Rpm");
       rightspeedsetrpmlabel = new JLabel("0");
       rightspeedsetinfo = new JSlider(JSlider.VERTICAL,-500,500,0);
       stopmotorsbutton = new JButton("Stop Motors");
       leftspeedsetlabel = new JLabel("Left Speed Rpm");
       leftspeedsetrpmlabel = new JLabel("0");
       leftspeedsetinfo = new JSlider(JSlider.VERTICAL,-500,500,0);
       
       northpanel.setLayout(new BoxLayout(northpanel, BoxLayout.Y_AXIS));
       eastpanel.setLayout(new BoxLayout(eastpanel, BoxLayout.Y_AXIS));
       westpanel.setLayout(new BoxLayout(westpanel, BoxLayout.Y_AXIS));
       centerpanel.setLayout(new BoxLayout(centerpanel, BoxLayout.X_AXIS));

       topblockpanel6.setLayout(new BoxLayout(topblockpanel6, BoxLayout.Y_AXIS));
       topblockpanel7.setLayout(new BoxLayout(topblockpanel7, BoxLayout.Y_AXIS));
       topblockpanel8.setLayout(new BoxLayout(topblockpanel8, BoxLayout.Y_AXIS));
       
       

       northpanel.add(selectdevicelabel);
       northpanel.add(selectdevicelist);
       topblockpanel1.add(serialnumberlabel);
       topblockpanel1.add(serialnumberinfo);
       topblockpanel2.add(globalerrorlabel);
       topblockpanel2.add(globalerrorinfo);
       topblockpanel3.add(batterylabel);
       topblockpanel3.add(batteryinfo);
       topblockpanel4.add(temperaturelabel);
       topblockpanel4.add(temperatureinfo);
       topblockpanel5.add(communicationratelabel);
       topblockpanel5.add(communicationrateinfo);
       topblockpanel6.add(rightspeedsetlabel);
       topblockpanel6.add(rightspeedsetrpmlabel);
       topblockpanel6.add(rightspeedsetinfo);
       topblockpanel7.add(leftspeedsetlabel);
       topblockpanel7.add(leftspeedsetrpmlabel);
       topblockpanel7.add(leftspeedsetinfo);
       topblockpanel8.add(stopmotorsbutton);
       northpanel.add(topblockpanel1);
       northpanel.add(topblockpanel2);
       northpanel.add(topblockpanel3);
       northpanel.add(topblockpanel4);
       northpanel.add(topblockpanel5);
       
       
       eastpanel.add(leftspeedlabel);
       eastpanel.add(leftspeedinfo);
       eastpanel.add(leftcurrentlabel);
       eastpanel.add(leftcurrentinfo);
       eastpanel.add(leftpolecountlabel);
       eastpanel.add(leftpolecountinfo);
       eastpanel.add(leftspeedcoeflabel);
       eastpanel.add(leftspeedcoefinfo);
       eastpanel.add(leftcurrentcoeflabel);
       eastpanel.add(leftcurrentcoefinfo);
       eastpanel.add(kpleftlabel);
       eastpanel.add(kpleftinfo);
       eastpanel.add(kileftlabel);
       eastpanel.add(kileftinfo);
       eastpanel.add(windupleftlabel);
       eastpanel.add(windupleftinfo);

       westpanel.add(rightspeedlabel);
       westpanel.add(rightspeedinfo);
       westpanel.add(rightcurrentlabel);
       westpanel.add(rightcurrentinfo);
       westpanel.add(rightpolecountlabel);
       westpanel.add(rightpolecountinfo);
       westpanel.add(rightspeedcoeflabel);
       westpanel.add(rightspeedcoefinfo);
       westpanel.add(rightcurrentcoeflabel);
       westpanel.add(rightcurrentcoefinfo);
       westpanel.add(kprightlabel);
       westpanel.add(kprightinfo);
       westpanel.add(kirightlabel);
       westpanel.add(kirightinfo);
       westpanel.add(winduprightlabel);
       westpanel.add(winduprightinfo);
       
       southpanel.add(connectbutton);
       southpanel.add(loadbutton);
       southpanel.add(savebutton);
       southpanel.add(exitbutton);
       
       centerpanel.add(topblockpanel6);
       centerpanel.add(topblockpanel8);
       centerpanel.add(topblockpanel7);
       leftspeedsetlabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
       leftspeedsetrpmlabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
       leftspeedsetinfo.setAlignmentX(Component.RIGHT_ALIGNMENT);
       
       frame.getContentPane().add(BorderLayout.NORTH, northpanel);
       frame.getContentPane().add(BorderLayout.EAST, eastpanel);
       frame.getContentPane().add(BorderLayout.WEST, westpanel);
       frame.getContentPane().add(BorderLayout.SOUTH, southpanel);
       frame.getContentPane().add(BorderLayout.CENTER, centerpanel);
       frame.setVisible(true);

       rightspeedsetinfo.addChangeListener(new ChangeListener() {
           public void stateChanged(ChangeEvent e) {
        	   rightspeedsetrpmlabel.setText("Set RPM : " + ((JSlider)e.getSource()).getValue());
        	   rightspeedset = ((JSlider) e.getSource()).getValue();
        	   setspeedschanged=true;
           }
        });
       leftspeedsetinfo.addChangeListener(new ChangeListener() {
           public void stateChanged(ChangeEvent e) {
        	   leftspeedsetrpmlabel.setText("Set RPM : " + ((JSlider)e.getSource()).getValue());
        	   leftspeedset = ((JSlider) e.getSource()).getValue();
        	   setspeedschanged=true;        	   
           }
        });

       connectbutton.addActionListener(new ActionListener(){  
    	   public void actionPerformed(ActionEvent e){  
   	        			deviceselected = true;
    	           }  
    	       });
       exitbutton.addActionListener(new ActionListener(){  
    	   public void actionPerformed(ActionEvent e){  
    		   System.exit(0);
    	           }  
    	       });
       loadbutton.addActionListener(new ActionListener(){  
    	   public void actionPerformed(ActionEvent e){  
   	        			actionload = true;
    	           }  
    	       }); 
       savebutton.addActionListener(new ActionListener(){  
    	   public void actionPerformed(ActionEvent e){  
    		   	serialnumberset = Integer.valueOf(serialnumberinfo.getText());
	   			rightpolecountset = Integer.valueOf(rightpolecountinfo.getText());
	   			leftpolecountset = Integer.valueOf(leftpolecountinfo.getText());
	   			rightspeedcoefset = Integer.valueOf(rightspeedcoefinfo.getText());
	   			leftspeedcoefset = Integer.valueOf(leftspeedcoefinfo.getText());
	   			rightcurrentcoefset = Integer.valueOf(rightcurrentcoefinfo.getText());
	   			leftcurrentcoefset = Integer.valueOf(leftcurrentcoefinfo.getText());
	   			communicationrateset = Integer.valueOf(communicationrateinfo.getText());
	   			modesset = 1;
	   			rightkpset = Integer.valueOf(kprightinfo.getText());
	   			leftkpset = Integer.valueOf(kpleftinfo.getText());
	   			rightkiset = Integer.valueOf(kirightinfo.getText());
	   			leftkiset = Integer.valueOf(kileftinfo.getText());
	   			rightwindupset = Integer.valueOf(winduprightinfo.getText());
	   			leftwindupset = Integer.valueOf(windupleftinfo.getText());
   	        			actionsave = true;
    	           }  
    	       }); 
       stopmotorsbutton.addActionListener(new ActionListener(){  
    	   public void actionPerformed(ActionEvent e){  
		    		   rightspeedset = 0; 
		    		   leftspeedset = 0;
		    		   setspeedschanged = true;
		    		   leftspeedsetinfo.setValue(0);
		    		   rightspeedsetinfo.setValue(0);
    	           }  
    	       }); 
       ActionListener actionListener = new ActionListener() {
    	      public void actionPerformed(ActionEvent actionEvent) {
    	    	  System.out.println(actionEvent.getActionCommand());
    	        System.out.println("Selected: " + selectdevicelist.getSelectedItem());
    	        selecteddeviceindex = selectdevicelist.getSelectedIndex();
    	      }
    	    };
    	    selectdevicelist.addActionListener(actionListener);
	}
	public void refreshlist() {
		// TODO Auto-generated method stub
		selectdevicelist.removeAllItems();
		for (int i = 0; i < devicelist.size(); ++i)
		{
			selectdevicelist.addItem(devicelist.get(i));
		}
		
	}
	public void refreshcheckdata(int counterright, int counterleft, int battery, int globalerror,
			int rightcurrent, int leftcurrent, int temperature) {
		Float rate = Float.valueOf(communicationrateinfo.getText());
		Float leftpolecount = Float.valueOf(leftpolecountinfo.getText());
		Float rightpolecount = Float.valueOf(rightpolecountinfo.getText());
		Float leftspeedrpm = 60 * (rate * counterleft / (leftpolecount * 6));
		Float rightspeedrpm = 60 * (rate * counterright / (rightpolecount * 6));
		leftspeedinfo.setText(Float.toString(leftspeedrpm));
		rightspeedinfo.setText(Float.toString(rightspeedrpm));
		batteryinfo.setText(Integer.toString(battery));
		globalerrorinfo.setText(Integer.toString(globalerror));
		rightcurrentinfo.setText(Integer.toString(rightcurrent));
		leftcurrentinfo.setText(Integer.toString(leftcurrent));
		temperatureinfo.setText(Integer.toString(temperature/10));
		// TODO Auto-generated method stub
		
	}
	public void refreshprogramdata(int serialnumber, int rightpolecount, int leftpolecount, int rightspeedfilter,
			int leftspeedfilter, int rightcurrentfilter, int leftcurrentfilter, int sendrate, int modes, int rightkp,
			int leftkp, int rightki, int leftki, int rightwindup, int leftwindup) {
		serialnumberinfo.setText(Integer.toString(serialnumber));
		rightpolecountinfo.setText(Integer.toString(rightpolecount));
		leftpolecountinfo.setText(Integer.toString(leftpolecount));
		rightspeedcoefinfo.setText(Integer.toString(rightspeedfilter));
		leftspeedcoefinfo.setText(Integer.toString(leftspeedfilter));
		rightcurrentcoefinfo.setText(Integer.toString(rightcurrentfilter));
		leftcurrentcoefinfo.setText(Integer.toString(leftcurrentfilter));
		communicationrateinfo.setText(Integer.toString(sendrate));
		//serialnumberinfo.setText(Integer.toString(modes));
		kprightinfo.setText(Integer.toString(rightkp));
		kpleftinfo.setText(Integer.toString(leftkp));
		kirightinfo.setText(Integer.toString(rightki));
		kileftinfo.setText(Integer.toString(leftki));
		winduprightinfo.setText(Integer.toString(rightwindup));
		windupleftinfo.setText(Integer.toString(leftwindup));
		
		// TODO Auto-generated method stub
		
	}

}
