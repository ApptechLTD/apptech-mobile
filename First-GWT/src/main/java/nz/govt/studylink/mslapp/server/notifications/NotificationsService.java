package nz.govt.studylink.mslapp.server.notifications;

import java.util.List;

import nz.govt.studylink.mslapp.server.notifications.model.Device;

public interface NotificationsService {
	void setupNotifications(String deviceToken, String authToken, String deviceType) throws NotificationsException;

	List<Device> getDevices(Long studentId) throws NotificationsException;
}
