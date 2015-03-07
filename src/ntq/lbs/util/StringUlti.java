package ntq.lbs.util;

/** Utility for String progress */
public class StringUlti {
	public static final String YOUTUBE_LINK = "https://www.youtube.com";
	public static final String VIDEO_LINK = "/watch";
	public static final String CHANNEL_LINK = "/channel";
	public static final String PARAM_VIDEO = "v=";
	public static final String PARAM_CHANNEL = "/";

	/** Cut string URL to get Youtube video id */
	public static String getVideoId(String URL) {
		// TODO: Cut string to get video Id
		return "";
	}

	/** Cut string URL to get Youtube channel id */
	public static String getChannelId(String url) {
		int index = url.lastIndexOf(CHANNEL_LINK);
		if (index == -1) {
			return "";
		} else {
			String result = "";
			index += CHANNEL_LINK.length() + PARAM_CHANNEL.length();

			if (url.length() > index) {
				return url.substring(index);
			} else {
				return "";
			}
		}
	}
}