package ntq.lbs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAccess {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String DB_ENCODING_UTF8 = "?useUnicode=true&characterEncoding=utf-8";

	// Database credentials
	static final String DB_USER = "root";
	static final String DB_PASS = "minhphuong";
	static final String DB_NAME = "youtube";
	static final String TABLE_VIDEO_NAME = "videos";
	static final String PARAM_VIDEO_ID = "id";
	static final String PARAM_VIDEO_NAME = "name";
	static final String PARAM_VIDEO_CHANNEL_ID = "channel_id";
	static final String PARAM_VIDEO_CHANNEL_NAME = "channel_name";
	static final String PARAM_VIDEO_VIEW = "view_num";
	static final String PARAM_VIDEO_LIKE = "like_num";
	static final String PARAM_VIDEO_DISLIKE = "dislike_num";

	// Connection to MySQL
	private Connection connection = null;
	// Statement keep query statement
	private Statement statement = null;
	// Query to database
	private PreparedStatement preparedStatement = null;
	// Result set from database
	private ResultSet resultSet = null;

	public void delete(String videoId) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER,
					DB_PASS);

			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();

			// Check database exist
			resultSet = connection.getMetaData().getCatalogs();
			boolean isExist = false;
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.equals(DB_NAME)) {
					isExist = true;
					break;
				}
			}

			if (!isExist) {
				return;
			}

			// Delete row
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("DELETE FROM ");
			queryBuilder.append(TABLE_VIDEO_NAME);
			queryBuilder.append(" WHERE ");
			queryBuilder.append(PARAM_VIDEO_ID);
			queryBuilder.append("='");
			queryBuilder.append(videoId);
			queryBuilder.append("';");

			statement.executeUpdate(queryBuilder.toString());
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	public void insert(Video video) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();

			// Check database exist
			resultSet = connection.getMetaData().getCatalogs();
			boolean isExist = false;
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.equals(DB_NAME)) {
					isExist = true;
					break;
				}
			}

			if (!isExist) {
				// Create database
				String queryStatement = "CREATE DATABASE " + DB_NAME;
				statement.executeUpdate(queryStatement);
			}

			// Reconnect to database
			String url = DB_URL + DB_NAME + DB_ENCODING_UTF8;
			connection = DriverManager.getConnection(url, DB_USER, DB_PASS);
			statement = connection.createStatement();

			// Create table id not exist
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("CREATE TABLE IF NOT EXISTS ");
			queryBuilder.append(TABLE_VIDEO_NAME);
			queryBuilder.append(" (");
			queryBuilder.append(PARAM_VIDEO_ID);
			queryBuilder.append(" VARCHAR(50) PRIMARY KEY NOT NULL, ");
			queryBuilder.append(PARAM_VIDEO_NAME);
			queryBuilder.append(" NVARCHAR(255), ");

			queryBuilder.append(PARAM_VIDEO_CHANNEL_ID);
			queryBuilder.append(" VARCHAR(50), ");
			queryBuilder.append(PARAM_VIDEO_CHANNEL_NAME);
			queryBuilder.append(" NVARCHAR(255), ");
			queryBuilder.append(PARAM_VIDEO_VIEW);
			queryBuilder.append(" VARCHAR(15), ");

			queryBuilder.append(PARAM_VIDEO_LIKE);
			queryBuilder.append(" VARCHAR(15), ");
			queryBuilder.append(PARAM_VIDEO_DISLIKE);
			queryBuilder.append(" VARCHAR(15))");

			String queryStatement = queryBuilder.toString();
			statement.executeUpdate(queryStatement);

			// Check video id existed
			String videoId = video.getId();
			resultSet = statement.executeQuery(checkIdExistedQuery(videoId));
			if (resultSet.next()) {
				// Update to database
				statement.executeUpdate(updateQuery(video));
			} else {
				// Insert to database
				statement.executeUpdate(insertQuery(video));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private String checkIdExistedQuery(String videoId) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("SELECT * FROM ");
		queryBuilder.append(TABLE_VIDEO_NAME);
		queryBuilder.append(" WHERE ");
		queryBuilder.append(PARAM_VIDEO_ID);
		queryBuilder.append(" = '");
		queryBuilder.append(videoId);
		queryBuilder.append("'");

		return queryBuilder.toString();
	}

	private String updateQuery(Video video) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("UPDATE ");
		queryBuilder.append(TABLE_VIDEO_NAME);
		queryBuilder.append(" SET ");
		queryBuilder.append(PARAM_VIDEO_NAME);
		queryBuilder.append("='");
		queryBuilder.append(video.getName());
		queryBuilder.append("', ");
		queryBuilder.append(PARAM_VIDEO_CHANNEL_ID);
		queryBuilder.append("='");
		queryBuilder.append(video.getChannelId());
		queryBuilder.append("', ");
		queryBuilder.append(PARAM_VIDEO_CHANNEL_NAME);
		queryBuilder.append("='");
		queryBuilder.append(video.getChannelName());
		queryBuilder.append("', ");
		queryBuilder.append(PARAM_VIDEO_VIEW);
		queryBuilder.append("='");
		queryBuilder.append(video.getView());
		queryBuilder.append("', ");
		queryBuilder.append(PARAM_VIDEO_LIKE);
		queryBuilder.append("='");
		queryBuilder.append(video.getLike());
		queryBuilder.append("', ");
		queryBuilder.append(PARAM_VIDEO_DISLIKE);
		queryBuilder.append("='");
		queryBuilder.append(video.getDislike());
		queryBuilder.append("' WHERE ");
		queryBuilder.append(PARAM_VIDEO_ID);
		queryBuilder.append("='");
		queryBuilder.append(video.getId());
		queryBuilder.append("'");

		return queryBuilder.toString();
	}

	private String insertQuery(Video video) {
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("INSERT INTO ");
		queryBuilder.append(TABLE_VIDEO_NAME);
		queryBuilder.append("(");
		queryBuilder.append(PARAM_VIDEO_ID);
		queryBuilder.append(", ");

		queryBuilder.append(PARAM_VIDEO_NAME);
		queryBuilder.append(", ");
		queryBuilder.append(PARAM_VIDEO_CHANNEL_ID);
		queryBuilder.append(", ");
		queryBuilder.append(PARAM_VIDEO_CHANNEL_NAME);
		queryBuilder.append(", ");

		queryBuilder.append(PARAM_VIDEO_VIEW);
		queryBuilder.append(", ");
		queryBuilder.append(PARAM_VIDEO_LIKE);
		queryBuilder.append(", ");
		queryBuilder.append(PARAM_VIDEO_DISLIKE);

		queryBuilder.append(") VALUES('");
		queryBuilder.append(video.getId());
		queryBuilder.append("', '");
		queryBuilder.append(video.getName());
		queryBuilder.append("', '");
		queryBuilder.append(video.getChannelId());

		queryBuilder.append("', '");
		queryBuilder.append(video.getChannelName());
		queryBuilder.append("', '");
		queryBuilder.append(video.getView());

		queryBuilder.append("', '");
		queryBuilder.append(video.getLike());
		queryBuilder.append("', '");
		queryBuilder.append(video.getDislike());
		queryBuilder.append("');");

		return queryBuilder.toString();
	}

	public void read(IOnReadResult onReadResult) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();

			// Check database exist
			resultSet = connection.getMetaData().getCatalogs();
			boolean isExist = false;
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if (databaseName.equals(DB_NAME)) {
					isExist = true;
					break;
				}
			}

			if (!isExist) {
				// Create database
				String queryStatement = "CREATE DATABASE " + DB_NAME;
				statement.executeUpdate(queryStatement);
			}

			// Reconnect to database
			String url = DB_URL + DB_NAME + DB_ENCODING_UTF8;
			connection = DriverManager.getConnection(url, DB_USER, DB_PASS);
			statement = connection.createStatement();

			// Create table id not exist
			StringBuilder queryBuilder = new StringBuilder();
			queryBuilder.append("CREATE TABLE IF NOT EXISTS ");
			queryBuilder.append(TABLE_VIDEO_NAME);
			queryBuilder.append(" (");
			queryBuilder.append(PARAM_VIDEO_ID);
			queryBuilder.append(" VARCHAR(50) PRIMARY KEY NOT NULL, ");
			queryBuilder.append(PARAM_VIDEO_NAME);
			queryBuilder.append(" NVARCHAR(255), ");

			queryBuilder.append(PARAM_VIDEO_CHANNEL_ID);
			queryBuilder.append(" VARCHAR(50), ");
			queryBuilder.append(PARAM_VIDEO_CHANNEL_NAME);
			queryBuilder.append(" NVARCHAR(255), ");
			queryBuilder.append(PARAM_VIDEO_VIEW);
			queryBuilder.append(" VARCHAR(15), ");

			queryBuilder.append(PARAM_VIDEO_LIKE);
			queryBuilder.append(" VARCHAR(15), ");
			queryBuilder.append(PARAM_VIDEO_DISLIKE);
			queryBuilder.append(" VARCHAR(15))");

			String queryStatement = queryBuilder.toString();
			statement.executeUpdate(queryStatement);

			// Read database table
			String selectQueryStatement = "SELECT * FROM " + TABLE_VIDEO_NAME;

			// Result set get the result of the SQL query
			resultSet = statement.executeQuery(selectQueryStatement);
			writeResultSet(resultSet, onReadResult);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void writeResultSet(ResultSet resultSet, IOnReadResult onReadResult)
			throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			String id = resultSet.getString(PARAM_VIDEO_ID);
			String name = resultSet.getString(PARAM_VIDEO_NAME);
			String channelId = resultSet.getString(PARAM_VIDEO_CHANNEL_ID);
			String channelName = resultSet.getString(PARAM_VIDEO_CHANNEL_NAME);
			String view = resultSet.getString(PARAM_VIDEO_VIEW);
			String like = resultSet.getString(PARAM_VIDEO_LIKE);
			String dislike = resultSet.getString(PARAM_VIDEO_DISLIKE);

			// Create video
			Video video = new Video(id);
			video.setName(name);
			video.setChannelId(channelId);
			video.setChannelName(channelName);
			video.setView(view);
			video.setLike(like);
			video.setDislike(dislike);

			// Notify data
			if (onReadResult != null) {
				onReadResult.onSuccess(video);
			}
		}
	}

	public interface IOnReadResult {
		public void onSuccess(Video video);
	}

	public interface IOnUpdateResult {
		public void onSuccess();

		public void onError(String msg);
	}

	// You have to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			System.out.println(String.valueOf(e.getMessage()));
		}
	}
}