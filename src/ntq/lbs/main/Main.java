package ntq.lbs.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import ntq.lbs.controller.ConfigReader;
import ntq.lbs.controller.CrawlerQueue;
import ntq.lbs.controller.CrawlerThread.IOnCrawVideo;
import ntq.lbs.model.MySQLAccess;
import ntq.lbs.model.MySQLAccess.IOnReadResult;
import ntq.lbs.model.Video;
import ntq.lbs.view.MainForm;

public class Main {
	private static int THREAHOLD = 39;
	private static List<Video> a = new ArrayList<Video>();
	
	private static synchronized void de(){
		THREAHOLD--;
	}

	public static void main(String[] args) {
		// TODO: Delete soon
		Date dateStart = new Date();
		System.out.println(String.valueOf("Start: " + dateStart.toString()));
		System.out.println("========== * * * ==========");

		// Loading configuration file
		ConfigReader.loadConfig(true);
		MySQLAccess dao = new MySQLAccess();
		try {
			// dao.delete("vpfn0UTNIx0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Construct a new MainForm and show this form
		MainForm currentMainForm = MainForm.getInstance();
		currentMainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentMainForm.setVisible(true);
	}

	private static IOnCrawVideo onCrawVideo = new IOnCrawVideo() {

		public void onSuccess(Video video) {
			MySQLAccess dao = new MySQLAccess();
			try {
				System.out.println(THREAHOLD);
				de();
				a.add(video);
				if (THREAHOLD == 0) {
					for (Video v : a) {
						dao.insert(v);
					}
					dao.read(new IOnReadResult() {
						public void onSuccess(Video video) {
							// TODO Auto-generated method stub
							System.out.println(video.toString());
						}
					});
					// TODO: Delete soon
					Date dateEnd = new Date();
					System.out.println("========== * * * ==========");
					System.out.println(String.valueOf("End: "
							+ dateEnd.toString()));
				}
				CrawlerQueue.notifyDataChanged();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void onError(String msg) {
			// TODO Auto-generated method stub
		}
	};
}