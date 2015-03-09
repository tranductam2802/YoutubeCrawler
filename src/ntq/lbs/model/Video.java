package ntq.lbs.model;

public class Video {
	private String id;
	private String name;
	private String channelId;
	private String channelName;
	private String view;
	private String like;
	private String dislike;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getDislike() {
		return dislike;
	}

	public void setDislike(String dislike) {
		this.dislike = dislike;
	}

	public String getId() {
		return id;
	}

	public Video(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Video id: " + id);
		builder.append("\n");
		builder.append("Name: " + name);
		builder.append("\n");
		builder.append("Channel id: " + channelId);
		builder.append("\n");
		builder.append("Channel name: " + channelName);
		builder.append("\n");
		builder.append("View: " + view);
		builder.append("\n");
		builder.append("Like: " + like);
		builder.append("\n");
		builder.append("Dislike: " + dislike);
		builder.append("\n");
		return builder.toString();
	}
}