package ntq.lbs.controller;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ntq.lbs.util.Config;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigReader {
	private static boolean isConfigLoaded = false;

	// Document configuration form
	private static final String videoTag = "video-name";
	private static String videoElement = "watch-headline-title";

	public static String getVideoElement() {
		return videoElement;
	}

	private static final String videoIdTag = "video-id";
	private static String videoIdElement = "";

	public static String getVideoIdElement() {
		return videoIdElement;
	}

	private static final String channelTag = "channel-name";
	private static String channelElement = "yt-user-info";

	public static String getChannelElement() {
		return channelElement;
	}

	private static final String channelIdTag = "channel-id";
	private static String channelIdElement = "yt-uix-sessionlink";

	public static String getChannelIdElement() {
		return channelIdElement;
	}

	private static final String viewTag = "view";
	private static String viewElement = "watch-view-count";

	public static String getViewElement() {
		return viewElement;
	}

	private static final String likeTag = "like";
	private static String likeElement = "watch-like";

	public static String getLikeElement() {
		return likeElement;
	}

	private static final String dislikeTag = "dislike";
	private static String dislikeElement = "watch-dislike";

	public static String getDislikeElement() {
		return dislikeElement;
	}

	/**
	 * Load the configuration file. If this file's loaded, run over the loading
	 * task.
	 * 
	 * @param byPass
	 *            If true, by pass the check and reload configuration
	 */
	public static void loadConfig(boolean byPass) {
		if (isConfigLoaded && !byPass) {
			return;
		}

		try {
			File xmlFile = new File(Config.CONFIG_FILE);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
			Document document = docBuilder.parse(xmlFile);

			// Optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			document.getDocumentElement().normalize();

			Element element = document.getDocumentElement();

			// Get video name
			videoElement = element.getElementsByTagName(videoTag).item(0)
					.getTextContent();

			// Get video ID
			videoIdElement = element.getElementsByTagName(videoIdTag).item(0)
					.getTextContent();

			// Get channel name
			channelElement = element.getElementsByTagName(channelTag).item(0)
					.getTextContent();
			// Get channel ID
			channelIdElement = element.getElementsByTagName(channelIdTag)
					.item(0).getTextContent();

			// Get view
			viewElement = element.getElementsByTagName(viewTag).item(0)
					.getTextContent();

			// Get like
			likeElement = element.getElementsByTagName(likeTag).item(0)
					.getTextContent();

			// Get dislike
			dislikeElement = element.getElementsByTagName(dislikeTag).item(0)
					.getTextContent();

			isConfigLoaded = true;
		} catch (Exception e) {
			// TODO: Show any thing
			System.out.println(String.valueOf(e.getMessage()));
		}
	}
}