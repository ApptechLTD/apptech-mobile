package com.apptech.first.client.cordova.plugins;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.storage.client.Storage;

public abstract class UserSettingPluginBase implements UserSettingPlugin {

	protected Logger logger = Logger.getLogger("MSLApp.UserSettingPluginBase");
	private UserSettingPluginCallback callbackHandler; 
	
	protected Map<String, String> userSetting;
	Storage stockStore = Storage.getLocalStorageIfSupported();

	@Override
	public void init(UserSettingPluginCallback callbackHandler)
	{
		// keep the callback handler instance
		this.callbackHandler = callbackHandler;
		
		// Read setting from native by phonegap 
		readUserSetting();
	}
	
	@Override
	public Map<String, String> getUserSetting()
	{
		// return the setting 
		// Read the server url from local storage
		String serverUrl = userSetting.get(APP_URL);
		// The server url is invalid, read from local storage
		if (serverUrl == null || serverUrl.length() == 0)
		{
			if (stockStore != null)
			{
				serverUrl = stockStore.getItem(APP_URL);
			}
		}
		
		if (serverUrl == null)
		{
			serverUrl = DefaultServerUrl;
		}
		
		userSetting.put(APP_URL, serverUrl);
		return userSetting;
	}

	void readSettingSuccess(JavaScriptObject data) {
		
		try {
			// 1: We create a map isntance
			Map<String, String> map = new HashMap<String, String>();

			// 2 : populate with the java from the json
			CordovaPluginUtil.parseJavascriptMap(map, data);
			
			userSetting = map; // keep the setting in memory
			
			if (callbackHandler != null) {
				callbackHandler.onUserSettingReady();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "readSettingSuccess() ", e);
			throw new RuntimeException("Error calling onUserSettingReady() : "
					+ e.getMessage(), e);
		}
	}

	void readSettingFail(JavaScriptObject status) {
		if (callbackHandler != null) {
			callbackHandler.onUserSettingFail("Read user setting failed");
		}
	}

	protected void readUserSetting()
	{
		logger.info("readUserSetting   unimplemented..should be override by subclass");
	}
}
