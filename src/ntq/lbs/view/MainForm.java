package ntq.lbs.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainForm extends JFrame {
	private static final long serialVersionUID = -5201118736847043661L;
	private static MainForm mainForm;

	public static void main(String[] args) {
		// Construct a new MainForm and show this form
		MainForm currentMainForm = getInstance();
		currentMainForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentMainForm.setVisible(true);
		try {
			processPage("http://youtube.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void processPage(String URL) throws IOException {
		System.out.println(URL);
		Document doc = Jsoup.connect(URL).get();

		// get all links and recursively call the processPage method
		Elements questions = doc.select("a[href]");
		for (Element link : questions) {
			System.out.println(link.attr("href"));
		}
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