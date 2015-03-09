package ntq.lbs.controller;

import ntq.lbs.controller.CrawlerThread.IOnCrawVideo;

public class QueueItem {
	private String videoId;
	private IOnCrawVideo onCrawVideo;

	public String getVideoId() {
		return videoId;
	}

	public IOnCrawVideo getOnCrawVideo() {
		return onCrawVideo;
	}

	public QueueItem(String videoId, IOnCrawVideo onCrawVideo) {
		this.videoId = videoId;
		this.onCrawVideo = onCrawVideo;
	}
}