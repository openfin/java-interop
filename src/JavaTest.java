import org.apache.log4j.BasicConfigurator;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class JavaTest implements ActionListener{
	static InteropTest i = new InteropTest();
	JLabel ticker = new JLabel("Empty");
	JComboBox tickersCB;
	JComboBox JoinChannelCB;
	String platform;
	public JavaTest() {
    	JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel btnLabelListen = new JLabel("Select to listen to Channel");
		JLabel btnLabelSet = new JLabel("Select to set Channel:");
		JLabel LabelTicker = new JLabel("Select ticker symbol:");
        platform = JOptionPane.showInputDialog("Enter Platform id:");
        
        String[] tickers = { "AAPL", "MSFT", "GOOG", "TSLA" };
		tickersCB = new JComboBox(tickers);
		tickersCB.putClientProperty("ticker", true);
		tickersCB.setSelectedIndex(0);
		tickersCB.addActionListener(this);
		
		String[] channelColors = { "red", "green", "pink", "orange", "purple", "yellow" };

		JoinChannelCB = new JComboBox(channelColors);
		JoinChannelCB.putClientProperty("join", true);
		JoinChannelCB.setSelectedIndex(1);
		JoinChannelCB.addActionListener(this);
		
		JComboBox SetChannelCB = new JComboBox(channelColors);
		SetChannelCB.putClientProperty("join", false);
		SetChannelCB.setSelectedIndex(1);
		SetChannelCB.addActionListener(this);
		
		panel.setBorder(BorderFactory.createEmptyBorder(10,70,30,70));
		panel.setLayout(new GridLayout(0,1));
		
		panel.add(ticker);
		panel.add(LabelTicker);
		panel.add(tickersCB);
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
        	i.setup(jt.platform);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        try {
        	if(cb.getClientProperty("join") != null && ((boolean) cb.getClientProperty("join"))) {
        		i.joinAllGroups(JoinChannelCB.getSelectedItem().toString(),this);
        	} else if(cb.getClientProperty("ticker") != null && (boolean) cb.getClientProperty("ticker")) {
        		i.clientSetContext(JoinChannelCB.getSelectedItem().toString(), tickersCB.getSelectedItem().toString(), platform);
        	}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public void updateTicker(JSONObject id) {
		ticker.setText(id.toString());
	}
	
}
