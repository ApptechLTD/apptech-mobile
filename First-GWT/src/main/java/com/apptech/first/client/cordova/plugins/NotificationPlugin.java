package com.apptech.first.client.cordova.plugins;

import java.util.Map;

import com.apptech.first.client.ClientFactory;



public interface NotificationPlugin {
	 
	void Init(ClientFactory clientFactory);
	 
	 void registerDevice(boolean alertParamter, boolean badgeParamter,
			boolean soundParameter, String pwAppidParameter,
			String appnameParameter, String projectID);
	 
	 void setApplicationIconBadgeNumber(int number);
	 
	 public interface NotificationPluginCallback{
		 
			void onPushNotification(Map<String, String> notification);
			
			void onRegisterDeviceSuccess(Map<String, String> status);
			
			void onRegisterDeviceFail(Map<String, String> status);
	}
}
