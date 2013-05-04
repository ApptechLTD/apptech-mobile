package nz.govt.studylink.mslapp.client.cordova.plugins;

import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;

// For test the notification on browser
public class NotificationPluginBrowserImpl extends BaseNotificationPlugin{

	private Logger logger = Logger.getLogger(NotificationPluginBrowserImpl.class.getName());
	
	public NotificationPluginBrowserImpl(){
		logger.info("notification NotificationPluginBrowserImpl init on browser");
		init();
	}
	
	public void registerDevice(boolean alertParamter, boolean badgeParamter,
			boolean soundParameter, String pwAppidParameter,
			String appnameParameter, String projectID){

		registerDeviceImpl(null, alertParamter, badgeParamter, soundParameter, pwAppidParameter, appnameParameter);
		logger.info("notification register device on browser");
	}
	
	public void setApplicationIconBadgeNumber(int number){
		logger.info("notification setApplicationIconBadgeNumber on browser");
	}
	
	private native void registerDeviceImpl(JavaScriptObject pushNotification, boolean alertParamter, boolean badgeParamter,
			boolean soundParameter, String pwAppidParameter,
			String appnameParameter) /*-{
				
		var status = {"deviceToken":"7b15632e82e2954d831d6a3538b53b513a7e9403f11f4bbaa739c23ec13ccbf8"}
		this.@nz.govt.studylink.mslapp.client.cordova.plugins.NotificationPluginBrowserImpl::handleOnRegisterDeviceSuccess(Lcom/google/gwt/core/client/JavaScriptObject;)(status);
	}-*/;
	
	private native void init()/*-{
		
		$wnd.mslapp = {};
		var that = this;
		// Register the push listener in JavaScript
		// Implement the notification callback, so it will invoke this method instead of the method in prototype
		$wnd.mslapp.notificationCallback = function(notification){
			that.@nz.govt.studylink.mslapp.client.cordova.plugins.BaseNotificationPlugin::handleOnPushNotification(Lcom/google/gwt/core/client/JavaScriptObject;)(notification);
		};
	}-*/;
}
