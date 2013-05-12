package com.apptech.first.server.notifications;

@SuppressWarnings("serial")
public class NotificationsException extends Exception {

	public NotificationsException(String message, Throwable t) {
		super(message, t);
	}

	public NotificationsException(String message) {
		super(message);
	}

}
