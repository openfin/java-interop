import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.ItemSelectable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONObject;

import com.openfin.desktop.ActionEvent;

public class JavaTest implements ActionListener{
	static InteropTest i = new InteropTest();
	JLabel ticker = new JLabel("empty");
	public JavaTest() {
    	JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel btnLabelListen = new JLabel("Select to listen to Channel");
		JLabel btnLabelSet = new JLabel("Select to set Channel");

		String[] petStrings = { "red", "green", "pink", "orange", "purple", "yellow" };

		JComboBox JoinChannelCB = new JComboBox(petStrings);
		JoinChannelCB.putClientProperty("join", true);
		JoinChannelCB.setSelectedIndex(1);
		JoinChannelCB.addActionListener(this);
		
		JComboBox SetChannelCB = new JComboBox(petStrings);
		SetChannelCB.putClientProperty("join", false);
		SetChannelCB.setSelectedIndex(1);
		SetChannelCB.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(70,70,30,70));
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(ticker);
	
		panel.add(btnLabelListen);
		panel.add(JoinChannelCB);
		panel.add(btnLabelSet);
		panel.add(SetChannelCB);


		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		
        
		JavaTest jt = new JavaTest();
        try {
        	
        	i.setup();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String color = (String)cb.getSelectedItem();
        try {
        	boolean a = (boolean)cb.getClientProperty("join");
        	if((boolean) cb.getClientProperty("join")) {
        		i.joinAllGroups(color, this);	
        	}else {
        		i.clientSetContext(color);
        	}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void updateTicker(JSONObject id) {
		ticker.setText(id.toString());
	}
	
}
