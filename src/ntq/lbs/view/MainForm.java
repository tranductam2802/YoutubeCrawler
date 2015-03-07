package ntq.lbs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;

import javax.swing.JFrame;

import ntq.lbs.controller.ConfigReader;
import ntq.lbs.controller.Crawler;

public class MainForm extends JFrame {
	private static final long serialVersionUID = -5201118736847043661L;
	private static MainForm mainForm;

	public static void main(String[] args) {
		// TODO: Delete soon
		Date dateStart = new Date();

		// Loading configuration file
		ConfigReader.loadConfig(true);

		// Construct a new MainForm and show this form
		// MainForm currentMainForm = getInstance();
		// currentMainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// currentMainForm.setVisible(true);

		try {
			Crawler.crawVideo("xUF_15OaChU");
		} catch (Exception e) {

		}

		// TODO: Delete soon
		Date dateEnd = new Date();
		System.out.println(String.valueOf(dateEnd.toString()));
		System.out.println(String.valueOf(dateStart.toString()));
		System.out.println(String.valueOf(dateEnd.getTime()
				- dateStart.getTime()));
	}

	public static MainForm getInstance() {
		if (mainForm == null) {
			mainForm = new MainForm();
		}

		return mainForm;
	}

	private MainForm() {
		// Singleton pattern - Only one window on this program

		// Setting channel panel
		ChannelPanel channelPanel = new ChannelPanel();
		channelPanel.setSize(10, 10);
		channelPanel.setBackground(Color.BLACK);

		// Setting video panel
		VideoPanel videoPanel = new VideoPanel();
		videoPanel.setSize(10, 10);
		videoPanel.setBackground(Color.BLUE);

		// Update main form
		add(channelPanel, BorderLayout.WEST);
		add(videoPanel, BorderLayout.EAST);
		setResizable(false);
	}
}