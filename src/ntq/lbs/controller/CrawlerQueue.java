package ntq.lbs.controller;

import java.util.ArrayList;
import java.util.List;

import ntq.lbs.controller.CrawlerThread.IOnCrawVideo;

public class CrawlerQueue {
	private static List<QueueItem> videoQueue = new ArrayList<QueueItem>();

	public static synchronized void add(String videoId, IOnCrawVideo onCrawVideo) {
		CrawlerThread crawlerThread = CrawlerThread.getInsctance(videoId);
		if (crawlerThread == null) {
			QueueItem stackItem = new QueueItem(videoId, onCrawVideo);
			videoQueue.add(stackItem);
		} else {
			crawlerThread.setOnCrawVideo(onCrawVideo);
			crawlerThread.start();
		}
	}

	public static void notifyDataChanged() {
		if (videoQueue.size() == 0) {
			return;
		}

		QueueItem stackItem = videoQueue.remove(0);
		add(stackItem.getVideoId(), stackItem.getOnCrawVideo());
	}
}