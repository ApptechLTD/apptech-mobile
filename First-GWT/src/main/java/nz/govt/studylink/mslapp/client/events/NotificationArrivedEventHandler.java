package nz.govt.studylink.mslapp.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface NotificationArrivedEventHandler extends EventHandler {

	void onNotificationArrived(NotificationArrivedEvent notificationEvent);
	
}