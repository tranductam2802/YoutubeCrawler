package ntq.lbs.view;

import javax.swing.JTable;

public class ChannelTable extends JTable {
	private static final long serialVersionUID = 3972092754283286069L;

	private static final String[] COLUM_NAME = { "Video name", "Views",
			"Likes", "Channel name" };

	public static ChannelTable newInstance() {
		ChannelTable channelTable = new ChannelTable();
		return channelTable;
	}
}