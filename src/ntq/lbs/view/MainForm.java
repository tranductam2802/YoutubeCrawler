package ntq.lbs.view;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JFrame;

import ntq.lbs.controller.CrawlerQueue;
import ntq.lbs.controller.CrawlerThread.IOnCrawVideo;
import ntq.lbs.model.Video;

public class MainForm extends JFrame implements IOnCrawVideo {
	private static final long serialVersionUID = -5201118736847043661L;
	private static MainForm mainForm;

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

		// Setting video panel
		VideoPanel videoPanel = new VideoPanel();
		videoPanel.setSize(10, 10);

		// Update main form
		add(channelPanel, BorderLayout.WEST);
		add(videoPanel, BorderLayout.EAST);
		setSize(200, 100);
		// setResizable(false);
	}

	public void onSuccess(Video video) {
		// TODO Auto-generated method stub
		System.out.println(video.toString());
		CrawlerQueue.notifyDataChanged();
		Date date = new Date();
		System.out.println(String.valueOf("Date: " + date.toString()));
	}

	public void onError(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}
}