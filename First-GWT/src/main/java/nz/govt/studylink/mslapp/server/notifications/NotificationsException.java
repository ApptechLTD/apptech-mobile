package nz.govt.studylink.mslapp.server.notifications;

@SuppressWarnings("serial")
public class NotificationsException extends Exception {

	public NotificationsException(String message, Throwable t) {
		super(message, t);
	}

	public NotificationsException(String message) {
		super(message);
	}

}
