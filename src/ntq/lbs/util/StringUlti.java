package ntq.lbs.util;

/** Utility for String progress */
public class StringUlti {
	public static final String YOUTUBE_LINK = "https://www.youtube.com";
	public static final String VIDEO_LINK = "/watch";
	public static final String PARAM_VIDEO = "v=";
	public static final String CHANNEL_LINK = "/channel/";

	/** Cut string URL to get Youtube video id */
	public static String getVideoId(String url) {
		int index = url.lastIndexOf(PARAM_VIDEO);
		if (index == -1) {
			return "";
		} else {
			index += PARAM_VIDEO.length();
			if (url.length() > index) {
				String temp = url.substring(index);
				index = temp.indexOf("&");
				if (index == -1) {
					return temp;
				} else {
					return temp.substring(0, index);
				}
			} else {
				return "";
			}
		}
	}

	/** Cut string URL to get Youtube channel id */
	public static String getChannelId(String url) {
		int index = url.lastIndexOf(CHANNEL_LINK);
		if (index == -1) {
			return "";
		} else {
			index += CHANNEL_LINK.length();
			if (url.length() > index) {
				return url.substring(index);
			} else {
				return "";
			}
		}
	}
}