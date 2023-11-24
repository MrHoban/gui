import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI implements ActionListener {
	
	private int count = 0;
	private int totalClicks = 0;
	private long totalTime = 0;
	private JLabel label;
	private JLabel cpsLabel;
	private JLabel timeLabel;
	private JFrame frame;
	private JButton button;
	private JPanel panel;
	private Date lastClickTime;
	
	public GUI() {
		
		frame = new JFrame();
		
		button = new JButton("Click me");
		button.addActionListener(this);
		
		label = new JLabel("Number of clicks: 0");
		cpsLabel = new JLabel("CPS: 0.0");
		timeLabel = new JLabel("Time Between Clicks: N/A");
		
		
		panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(label);
        panel.add(cpsLabel);
        panel.add(timeLabel);
        panel.add(button);
;
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void main(String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		count++;
        totalClicks++;

        if (lastClickTime != null) {
            long timeDifference = new Date().getTime() - lastClickTime.getTime();
            double cps = 1000.0 / timeDifference; // Clicks per second
            totalTime += timeDifference;
            //label
            cpsLabel.setText(String.format("CPS: %.2f", cps));
            timeLabel.setText("Time Between clicks: " + formatTimeDifference(timeDifference));
        }

        lastClickTime = new Date();

        label.setText("Number of clicks: " + count);
		// //Add the average click rate at each checkpoint
        if (count == 50 || count == 75 || count == 100 || count == 200) {
            double averageCPS = totalClicks / (totalTime / 1000.0); // Average Clicks per second
            String message = String.format("Number of clicks: %d\nCPS: %.2f\nTime Between clicks: %s\nAverage CPS: %.2f",
                    count, Double.parseDouble(cpsLabel.getText().substring(5)), timeLabel.getText(), averageCPS);
            JOptionPane.showMessageDialog(frame, message);
        }
        
		if (count == 65) {
			JOptionPane.showMessageDialog(frame, "Seriously, You're still going?"); 
		}
		
		if (count == 80) {
			JOptionPane.showMessageDialog(frame, "You can stop now");
		}
		
		if (count == 150) {
			JOptionPane.showMessageDialog(frame, "Are you okay?");
		}
		
		if (count == 250) {
			JOptionPane.showMessageDialog(frame, "Want to see a magic trick?");
		}
		if (count == 300) {
            double averageCPS = totalClicks / (totalTime / 1000.0); // Average Clicks per second
            String message = String.format(
                    "Number of clicks: %d\nCPS: %.2f\nTime Between clicks: %s\nAverage CPS: %.2f", count,
                    Double.parseDouble(cpsLabel.getText().substring(5)), timeLabel.getText(), averageCPS);
            JOptionPane.showMessageDialog(frame, message);

            // Reset count and related variables after reaching 300 clicks
            count = 0;
            totalClicks = 0;
            totalTime = 0;
            cpsLabel.setText("CPS: 0.0");
            timeLabel.setText("Time Between Clicks: N/A");
        }
    }
	private String formatTimeDifference(long timeInMillis) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss:SSS");
		return sdf.format(new Date(timeInMillis));
	}
}
