package ntq.lbs.util;

public class Log {
	/** Information log */
	public static void i(String tag, String msg) {
		// TODO: Log for information
	}

	/** Information log */
	public static void i(String msg) {
		i(Config.APP_NAME, msg);
	}

	/** Debug log */
	public static void d(String tag, String msg) {
		// TODO: Log for debug
	}

	/** Debug log */
	public static void d(String msg) {
		d(Config.APP_NAME, msg);
	}

	/** Warning log */
	public static void w(String tag, String msg) {
		// TODO: Log for warning
	}

	/** Warning log */
	public static void w(String msg) {
		w(Config.APP_NAME, msg);
	}

	/** Error log */
	public static void e(String tag, String msg) {
		// TODO: Log for error
	}

	/** Error log */
	public static void e(String msg) {
		e(Config.APP_NAME, msg);
	}
}