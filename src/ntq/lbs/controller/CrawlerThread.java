package ntq.lbs.controller;

import java.io.IOException;

import ntq.lbs.model.Video;
import ntq.lbs.util.Config;

// Singleton pattern
public class CrawlerThread extends Thread {
	private static final CrawlerThread[] queueThread = new CrawlerThread[Config.THREAD_NUM];

	private String videoId;
	private IOnCrawVideo onCrawVideo;

	private CrawlerThread(String videoId) {
		this.videoId = videoId;
	}

	public void setOnCrawVideo(IOnCrawVideo onCrawVideo) {
		this.onCrawVideo = onCrawVideo;
	}

	/** @Nullable */
	public static synchronized CrawlerThread getInsctance(String videoId) {
		for (int i = 0; i < Config.THREAD_NUM; i++) {
			if (queueThread[i] == null) {
				queueThread[i] = new CrawlerThread(videoId);
				return queueThread[i];
			}
		}
		return null;
	}

	public static void release(CrawlerThread crawlerThread) {
		for (int i = 0; i < Config.THREAD_NUM; i++) {
			if (queueThread[i] == crawlerThread) {
				queueThread[i] = null;
			}
		}
	}

	@Override
	public void run() {
		super.run();
		try {
			Video video = Crawler.crawVideo(videoId);
			if (onCrawVideo != null) {
				onCrawVideo.onSuccess(video);
			}
		} catch (IOException e) {
			if (onCrawVideo != null) {
				onCrawVideo.onError(String.valueOf(e.getMessage()));
			}
		}
		release(this);
	}

	public interface IOnCrawVideo {
		public void onSuccess(Video video);

		public void onError(String msg);
	}
}