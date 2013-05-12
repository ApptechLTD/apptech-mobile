package com.apptech.first.client.events;

import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;

public class NotificationReadEvent extends GwtEvent<NotificationReadEventHandler> {

	public static Type<NotificationReadEventHandler> TYPE = new Type<NotificationReadEventHandler>();
	
	private Map<String, String> notification; 
	
	public NotificationReadEvent(Map<String, String> notification){
		this.notification = notification;
	}
	
	
	@Override
	public Type<NotificationReadEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NotificationReadEventHandler handler) {
		
		handler.onNotificationRead(this);
	}

	public Map<String, String> getNotification() {
		return notification;
	}
}