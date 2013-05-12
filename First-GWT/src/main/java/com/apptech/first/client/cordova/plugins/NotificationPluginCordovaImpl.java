package com.apptech.first.client.cordova.plugins;

import java.util.logging.Level;

import com.google.gwt.core.client.JavaScriptObject;

public class NotificationPluginCordovaImpl extends BaseNotificationPlugin implements  NotificationPlugin {

	private JavaScriptObject pushNotification;

	public NotificationPluginCordovaImpl(){
		logger.info("notification NotificationPluginCordovaImpl init on device");
		pushNotification = init();
	}
	
	public void registerDevice(boolean alertParamter, boolean badgeParamter,
			boolean soundParameter, String pwAppidParameter,
			String appnameParameter, String projectID){
		
		
		logger.info("registerDevice()  pwAppidParameter = " + pwAppidParameter + ", projectID = " + projectID);
		try{
			// Call the register api
			registerDeviceImpl(pushNotification, alertParamter, badgeParamter, soundParameter, pwAppidParameter, appnameParameter, projectID);
		}
		catch(Exception e){
			logger.log(Level.SEVERE, "registerDeviceImpl() ", e);
			throw new RuntimeException("Error calling registerDeviceImpl() : " + e.getMessage(), e);
		}
	}
	
	// Call the javascript to invoke the register API in the plugin 
	private native void registerDeviceImpl(JavaScriptObject pushNotification, boolean alertParamter, boolean badgeParamter,
			boolean soundParameter, String pwAppidParameter,
			String appnameParameter, String projectID) /*-{
				
		// Keep the this reference for the callback function closuer
		var that = this;
		
		// Invoke registerDevice API in the plugin
		pushNotification.registerDevice({
			alert : alertParamter,
			badge : badgeParamter,
			sound : soundParameter,
			pw_appid : pwAppidParameter,
			appname : appnameParameter,
			projectid : projectID,
			appid : pwAppidParameter
		}, function(status) {
			// Success callback, get the device token from APNS
			var deviceToken = status['deviceToken'];
			//calls back the java method
			that.@com.apptech.first.client.cordova.plugins.NotificationPluginCordovaImpl::handleOnRegisterDeviceSuccess(Lcom/google/gwt/core/client/JavaScriptObject;)(status);
		}, function(status) {
			// Fail callback, get the error message
			var statusString = JSON.stringify(status);
			// Callback to java method
			that.@com.apptech.first.client.cordova.plugins.NotificationPluginCordovaImpl::handleOnRegisterDeviceFail(Lcom/google/gwt/core/client/JavaScriptObject;)(status);
		});
	}-*/;
	
	public void setApplicationIconBadgeNumber(int number){
		logger.info("setApplicationIconBadgeNumber() number:" + number);
		setApplicationIconBadgeNumberImpl(pushNotification, number);
	}
	
	public native void setApplicationIconBadgeNumberImpl(JavaScriptObject pushNotification, int number)/*-{
		pushNotification.setApplicationIconBadgeNumber(0);
	}-*/;
	
	
	private native JavaScriptObject init()/*-{
		var instance = $wnd.plugins.pushNotification;
		
		instance.onDeviceReady();
		
		var that = this;
		// Register the push listener in JavaScript
		// Implement the notification callback, so it will invoke this method instead of the method in prototype
		instance.notificationCallback = function(notification){
			//alert("JavaScript Receive Notification: " + notification);
			that.@com.apptech.first.client.cordova.plugins.NotificationPluginCordovaImpl::handleOnPushNotification(Lcom/google/gwt/core/client/JavaScriptObject;)(notification);
		};
		
		return instance;
	}-*/;
}
