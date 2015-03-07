package ntq.lbs.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.text.html.HTML;

import ntq.lbs.model.Video;
import ntq.lbs.util.StringUlti;

import org.jsoup.Jsoup;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/** Youtube main crawler */
public class Crawler {

	public static Video crawVideo(String videoId) throws IOException {
		// Build youtube link
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(StringUlti.YOUTUBE_LINK);
		urlBuilder.append(StringUlti.VIDEO_LINK);
		urlBuilder.append("?");
		urlBuilder.append(StringUlti.PARAM_VIDEO);
		urlBuilder.append(videoId);

		// Connect to the Web
		String url = urlBuilder.toString();
		System.out.println(url);
		Document document = Jsoup.connect(url).get();

		// Create video to collect data
		Video video = new Video(videoId);

		// Get html div tag
		Elements divTags = document.select("div[class], div[id]");
		for (Element divElement : divTags) {
			// Get video name
			if (divElement.attr("id").equals(ConfigReader.getVideoElement())) {
				Elements spanTags = divElement.select("span");
				if (spanTags.size() > 0) {
					Element spanElement = spanTags.get(0);
					String name = spanElement.childNode(0).toString().trim();
					name = Jsoup.parse(name).text();
					video.setName(name);
					System.out.println("Name: " + name);
				}
			}

			// Get channel info
			if (divElement.attr("class").equals(
					ConfigReader.getChannelElement())) {
				Elements aTags = divElement.select("a");
				if (aTags.size() > 0) {
					Element aElement = aTags.get(0);

					// Get channel id
					String channelId = StringUlti.getChannelId(aElement
							.attr("href"));
					video.setChannelId(channelId);
					System.out.println("Channel id: " + channelId);

					// Get channel name
					String channelName = aElement.childNode(0).toString()
							.trim();
					channelName = Jsoup.parse(channelName).text();
					video.setChannelName(channelName);
					System.out.println("Channel name: " + channelName);
				}
			}

			// Get view number
			if (divElement.attr("class").equals(ConfigReader.getViewElement())) {
				String view = divElement.childNode(0).toString().trim();
				video.setView(view);
				System.out.println("View: " + view);
			}
		}

		Elements buttonTags = document.select("button[id]");
		for (Element buttonElement : buttonTags) {
			// Get like
			if (buttonElement.attr("id").equals(ConfigReader.getLikeElement())) {
				Elements spanTags = buttonElement.select("span");
				if (spanTags.size() > 0) {
					Element spaneElement = spanTags.get(0);
					String like = spaneElement.childNode(0).toString().trim();
					video.setLike(like);
					System.out.println("Like: " + like);
				}
			}

			// Get dislike
			if (buttonElement.attr("id").equals(
					ConfigReader.getDislikeElement())) {
				Elements spanTags = buttonElement.select("span");
				if (spanTags.size() > 0) {
					Element spaneElement = spanTags.get(0);
					String dislike = spaneElement.childNode(0).toString()
							.trim();
					video.setDislike(dislike);
					System.out.println("Dislike: " + dislike);
				}
			}
		}

		return video;
	}
}