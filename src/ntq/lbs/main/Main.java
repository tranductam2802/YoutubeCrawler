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
	private static int THREAHOLD = 38;
	private static List<Video> a = new ArrayList<Video>();

	private static synchronized void de() {
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
			CrawlerQueue.add("vpfn0UTNIx0", onCrawVideo);
			CrawlerQueue.add("S-1peV-w0O0", onCrawVideo);
			CrawlerQueue.add("yESQ4Fzq9_o", onCrawVideo);
			CrawlerQueue.add("nZlTx9nxY-U", onCrawVideo);
			CrawlerQueue.add("ZEcjXjpOo-Q", onCrawVideo);
			CrawlerQueue.add("INrFJuLU0fM", onCrawVideo);
			CrawlerQueue.add("wPrKAOpBeus", onCrawVideo);
			CrawlerQueue.add("D-6JDufCJ1Y", onCrawVideo);
			CrawlerQueue.add("X8mhF6HgzVA", onCrawVideo);
			CrawlerQueue.add("x-UEksXSLGs", onCrawVideo);
			CrawlerQueue.add("5iMx3tlpFeY", onCrawVideo);
			CrawlerQueue.add("kvNEHnVSmvs", onCrawVideo);
			CrawlerQueue.add("KE4I_W3jLuI", onCrawVideo);
			CrawlerQueue.add("AdfSLq7XNoI", onCrawVideo);
			CrawlerQueue.add("GefwYSJb_Bg", onCrawVideo);
			CrawlerQueue.add("r1OtnOs-utU", onCrawVideo);
			CrawlerQueue.add("BDw77bVNPo8", onCrawVideo);
			CrawlerQueue.add("lAkoANzq750", onCrawVideo);
			CrawlerQueue.add("VGTQi5QqR0w", onCrawVideo);
			CrawlerQueue.add("m6ndiBURmlQ", onCrawVideo);
			CrawlerQueue.add("3jj-MSFwRfQ", onCrawVideo);
			CrawlerQueue.add("RefgaMRfrG8", onCrawVideo);
			CrawlerQueue.add("45LNKFmrViY", onCrawVideo);
			CrawlerQueue.add("ZBT0Chsef0k", onCrawVideo);
			CrawlerQueue.add("DKaZtnJLSZ0", onCrawVideo);
			CrawlerQueue.add("rqxOucqbdyg", onCrawVideo);
			CrawlerQueue.add("QETfX44-PB8", onCrawVideo);
			CrawlerQueue.add("qbRIikllr-4", onCrawVideo);
			CrawlerQueue.add("x3Xlqs1z_FI", onCrawVideo);
			CrawlerQueue.add("tcat9CPiAZ4", onCrawVideo);
			CrawlerQueue.add("i724lraI93s", onCrawVideo);
			CrawlerQueue.add("V0e2cgpOrJQ", onCrawVideo);
			CrawlerQueue.add("yESQ4Fzq9_o", onCrawVideo);
			CrawlerQueue.add("xmfZ2U9bmZg", onCrawVideo);
			CrawlerQueue.add("io-JPztHbhg", onCrawVideo);
			CrawlerQueue.add("ZEcjXjpOo-Q", onCrawVideo);
			CrawlerQueue.add("wjRgrezfccA", onCrawVideo);
			CrawlerQueue.add("C2AiNyYiPDI", onCrawVideo);
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
