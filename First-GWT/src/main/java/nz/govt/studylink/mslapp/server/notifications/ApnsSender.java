package nz.govt.studylink.mslapp.server.notifications;

import java.util.Map;


public interface ApnsSender {

	void push(Long studentId, String message, Map<String, String> parameters) throws NotificationsException;

}
