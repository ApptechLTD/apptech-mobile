package com.apptech.first.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface NotificationReadEventHandler extends EventHandler {

	void onNotificationRead(NotificationReadEvent notificationReadEvent);
}