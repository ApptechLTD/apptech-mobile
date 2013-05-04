package nz.govt.studylink.mslapp.client.cordova.plugins;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import nz.govt.studylink.mslapp.client.ClientFactory;

import com.google.gwt.core.client.JavaScriptObject;

public abstract class BaseNotificationPlugin implements NotificationPlugin {

	protected NotificationPluginCallback handler;

	protected Logger logger = Logger.getLogger("MSLApp.BaseNotificationPlugin");

	protected Map<String, String> currentNotification = null;

	public BaseNotificationPlugin()
	{
		
	}
	
	public void Init(ClientFactory clientFactory)
	{
		this.handler = new PushNotificationEventHandler(clientFactory);
	}

	void handleOnPushNotification(JavaScriptObject notification) {
		try {
			// clear the badge
			setApplicationIconBadgeNumber(0);
			
			// Parse the notification javascript object into java a map
			Map<String, String> map = 
					CordovaPluginUtil.parseNotificationJavascriptObjct(notification);
			
			if (this.handler != null) {
				this.handler.onPushNotification(map);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "onPushNotification() ", e);
			throw new RuntimeException("Error calling onPushNotification() : "
					+ e.getMessage(), e);
		}
	}

	void handleOnRegisterDeviceSuccess(JavaScriptObject status) {
		try {
			// 1: We create a map isntance
			Map<String, String> map = new HashMap<String, String>();

			// 2 : populate with the java from the json
			CordovaPluginUtil.parseJavascriptMap(map, status);

			if (this.handler != null) {
				this.handler.onRegisterDeviceSuccess(map);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "onRegisterDeviceSuccess() ", e);
			throw new RuntimeException(
					"Error calling onRegisterDeviceSuccess() : "
							+ e.getMessage(), e);
		}
	}

	void handleOnRegisterDeviceFail(JavaScriptObject status) {
		try {
			// 1: We create a map isntance
			Map<String, String> map = new HashMap<String, String>();

			// 2 : populate with the java from the json
			CordovaPluginUtil.parseJavascriptMap(map, status);

			if (this.handler != null) {
				this.handler.onRegisterDeviceFail(map);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "onRegisterDeviceFail() ", e);
			throw new RuntimeException(
					"Error calling onRegisterDeviceFail() : "
							+ e.getMessage(), e);
		}
	}

	// Get current push notification data instance
	public Map<String, String> getCurrentNotification() {
		return currentNotification;
	}

	public void setCurrentNotification(Map<String, String> notification) {
		this.currentNotification = notification;
	}
	

}
