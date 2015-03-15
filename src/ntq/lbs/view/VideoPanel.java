package ntq.lbs.view;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ntq.lbs.model.MySQLAccess;
import ntq.lbs.model.MySQLAccess.IOnReadResult;
import ntq.lbs.model.Video;

public class VideoPanel extends JPanel {
	private static final long serialVersionUID = 1199183047600371020L;

	private final JTable table = new JTable();
	private final String[] columnNames = { "", "ID", "Video name",
			"Channel ID", "Channel name", "View", "Like - Dislile" };
	private String[][] data = {};

	public VideoPanel() {
		super(new GridLayout(1, 0));
		initView();
		initData();
	}

	private void initView() {
		// Set column name
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(columnNames);

		// Setting
		table.setFillsViewportHeight(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				
			}
		});

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	private void initData() {
		getTableFromDB();
	}

	private void getTableFromDB() {
		MySQLAccess sqlAccess = new MySQLAccess();
		try {
			sqlAccess.read(new IOnReadResult() {
				public void onSuccess(Video video) {
					DefaultTableModel tableModel = (DefaultTableModel) table
							.getModel();
					String[] data = new String[columnNames.length];
					data[0] = "x";
					data[1] = video.getId();
					data[2] = video.getName();
					data[3] = video.getChannelId();
					data[4] = video.getChannelName();
					data[5] = video.getView();
					data[6] = video.getLike() + " - " + video.getDislike();
					tableModel.addRow(data);
					tableModel.fireTableDataChanged();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}