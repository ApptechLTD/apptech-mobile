package com.apptech.first.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface NotificationArrivedEventHandler extends EventHandler {

	void onNotificationArrived(NotificationArrivedEvent notificationEvent);
	
}