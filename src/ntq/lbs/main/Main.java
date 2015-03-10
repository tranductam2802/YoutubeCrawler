package ntq.lbs.main;

import java.util.Date;

import ntq.lbs.controller.ConfigReader;
import ntq.lbs.controller.Crawler;
import ntq.lbs.model.MySQLAccess;
import ntq.lbs.model.MySQLAccess.IOnReadResult;
import ntq.lbs.model.Video;
import ntq.lbs.view.MainForm;

public class Main {
	public static void main(String[] args) {
		// TODO: Delete soon
		Date dateStart = new Date();
		System.out.println(String.valueOf("Start: " + dateStart.toString()));

		// Loading configuration file
		ConfigReader.loadConfig(true);
		MySQLAccess dao = new MySQLAccess();
		try {
			dao.delete("vpfn0UTNIx0");

			dao.read(new IOnReadResult() {
				public void onSuccess(Video video) {
					// TODO Auto-generated method stub
					System.out.println(video.toString());
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Construct a new MainForm and show this form
		// MainForm currentMainForm = MainForm.getInstance();
		// currentMainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// currentMainForm.setVisible(true);

		// TODO: Delete soon
		Date dateEnd = new Date();
		System.out.println(String.valueOf("End: " + dateEnd.toString()));
	}
}