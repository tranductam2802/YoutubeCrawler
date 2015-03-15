package ntq.lbs.view;

import java.util.Date;

import javax.swing.JFrame;

import ntq.lbs.controller.CrawlerQueue;
import ntq.lbs.controller.CrawlerThread.IOnCrawVideo;
import ntq.lbs.model.Video;

public class MainForm extends JFrame implements IOnCrawVideo {
	private static final long serialVersionUID = -5201118736847043661L;
	private static MainForm mainForm;

	public static MainForm getInstance(String title) {
		if (mainForm == null) {
			mainForm = new MainForm(title);
		}
		return mainForm;
	}

	private MainForm(String title) {
		// Singleton pattern - Only one window on this program
		setTitle(title);

		// TODO: Setting channel panel

		// Setting video panel
		VideoPanel videoPanel = new VideoPanel();

		// Update main form
		videoPanel.setOpaque(true);
		setContentPane(videoPanel);

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