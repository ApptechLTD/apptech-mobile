package com.apptech.first.client.events;

import java.util.Map;

import com.google.gwt.event.shared.GwtEvent;

public class NotificationArrivedEvent extends GwtEvent<NotificationArrivedEventHandler> {

	public static Type<NotificationArrivedEventHandler> TYPE = new Type<NotificationArrivedEventHandler>();
	
	private Map<String, String> notification; 
	
	public NotificationArrivedEvent(Map<String, String> notification){
		this.notification = notification;
	}
	
	@Override
	public Type<NotificationArrivedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(NotificationArrivedEventHandler handler) {
		
		handler.onNotificationArrived(this);
	}

	public Map<String, String> getNotification() {
		return notification;
	}
}