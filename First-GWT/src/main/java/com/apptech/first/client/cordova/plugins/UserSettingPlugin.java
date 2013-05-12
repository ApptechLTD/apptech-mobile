package com.apptech.first.client.cordova.plugins;

import java.util.Map;

public interface UserSettingPlugin {
	String APP_URL =  "serverUrl";
	String DefaultServerUrl = "http://172.20.10.7:8080";
	
	void init(UserSettingPluginCallback callbackHandler);
	Map<String, String> getUserSetting();
	
	public interface UserSettingPluginCallback {
		 void onUserSettingReady();
		 void onUserSettingFail(String message);
	}

}
