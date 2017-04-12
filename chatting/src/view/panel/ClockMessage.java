package view.panel;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

import assets.Setting;
//시계글씨
@SuppressWarnings("serial")
public class ClockMessage extends JPanel implements Runnable {
	int i = Calendar.getInstance().get(Calendar.AM_PM);
	String[] ampm = { "AM", "PM" };
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
	String time = sdf.format(new Date());
	JLabel timeLabel, ampmLabel;

	public ClockMessage() {
		this.setLayout(null);

		timeLabel = new JLabel(time);
		timeLabel.setBounds(0, 0, 100, 20);
		timeLabel.setForeground(Setting.basicColor);
		timeLabel.setFont(Setting.basicFont);

		ampmLabel = new JLabel(ampm[i]);
		ampmLabel.setBounds(15, 20, 100, 30);
		ampmLabel.setForeground(Setting.basicColor);
		ampmLabel.setFont(Setting.basicFont);

		add(timeLabel, BorderLayout.NORTH);
		add(ampmLabel, BorderLayout.CENTER);
	}

	@Override
	public void run() {
		do {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeLabel.setText(sdf.format(new Date()));
		} while (true);
	}
}
