package com.apptech.first.client.cordova.plugins;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.First;
import com.apptech.first.client.cordova.plugins.NotificationPlugin.NotificationPluginCallback;
import com.apptech.first.client.events.NotificationArrivedEvent;
import com.apptech.first.client.service.MSLServiceAsync;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;

public class PushNotificationEventHandler implements NotificationPluginCallback {

	Logger logger = Logger.getLogger("MSLApp.PushNotificationEventHandler");
	
	private Map<String, String> currentNotification = null;
	private ClientFactory clientFactory;
	protected MSLServiceAsync service = MSLServiceAsync.helper.getService();

	public PushNotificationEventHandler(ClientFactory clientFactory) {
		// TODO Auto-generated constructor stub
		this.clientFactory = clientFactory;
	}

	@Override
	public void onPushNotification(Map<String, String> notification) {
		logger.info("onPushNotification() : notification: " + notification);
		
		// clear the badge
		clientFactory.getNotificationPlugin().setApplicationIconBadgeNumber(0);
		
		// update the number in store
		int unreadMailCount = 0;
		int unreadApplicationCount = 0;
		String unreadMailStr = clientFactory.getStorage().getItem("unreadmail");
		
		if (unreadMailStr != null)
		{
			unreadMailCount = Integer.valueOf(unreadMailStr);
		}

		String unreadApplicationStr = clientFactory.getStorage().getItem("unreadapplication");
		if (unreadApplicationStr != null)
		{
			unreadApplicationCount = Integer.valueOf(unreadApplicationStr);
		}
		
		if ("mail".equals(notification.get("messageType")))
		{
			unreadMailCount++;
		}
		else if ("application".equals(notification.get("messageType")))
		{
			unreadApplicationCount++;
		}
		else
		{
			logger.info("onPushNotification() : error messagetype: " + notification.get("messageType"));
		}

		clientFactory.getStorage().setItem("unreadmail", String.valueOf(unreadMailCount));
		clientFactory.getStorage().setItem("unreadapplication", String.valueOf(unreadApplicationCount));
		
		// post push arrive event
		EventBus eventBus = this.clientFactory.getEventBus();
		eventBus.fireEvent(new NotificationArrivedEvent(notification));

	}
	
	@Override
	public void onRegisterDeviceSuccess(Map<String, String> status) {
		String platform = clientFactory.getPhoneGap().getDevice().getPlatform();
		String device = null;
		
		if (platform.equalsIgnoreCase("ios") || platform.equalsIgnoreCase("iphone")) {
			device = clientFactory.getPhoneGap().getDevice().getName();
		}
		else if (platform.equalsIgnoreCase("android")) {
			device = platform;
		}
		
		logger.info("onRegisterDeviceSuccess() : Device registered sucessfully ! status: " + status + " device type: " + device + " platform " + platform);
		
		service.setupNotifications(status.get("deviceToken"), First.getActiveUser().getAuthToken(),
				device, new AsyncCallback<Void>() {
					@Override
					public void onSuccess(Void result) {
						logger.info("service.setupNotifications() : Device token sent to server sucessfully ! ");
					}

					@Override
					public void onFailure(Throwable caught) {
						logger.log(Level.SEVERE, "onRegisterDeviceSuccess() : Error calling service.setupNotifications() ", caught);
					}
				});
	}

	@Override
	public void onRegisterDeviceFail(Map<String, String> status) {

	}

	// Get current push notification data instance
	public Map<String, String> getCurrentNotification() {
		return currentNotification;
	}

	// Set current push notification data instance
	public void setCurrentNotification(Map<String, String> notification) {
		currentNotification = notification;
	}
}