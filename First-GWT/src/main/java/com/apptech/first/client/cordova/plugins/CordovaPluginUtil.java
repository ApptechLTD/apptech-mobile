package com.apptech.first.client.cordova.plugins;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;

public class CordovaPluginUtil {
	
	public static native void parseJavascriptMap(Map<String, String> map,
			JavaScriptObject jsObject) /*-{
		for ( var prop in jsObject) {
			if (jsObject.hasOwnProperty(prop)) {
				map.@java.util.Map::put(Ljava/lang/Object;Ljava/lang/Object;)(prop, jsObject[prop]);
			}
		}
		
	}-*/;
	
	// Parse the push notification javascript object
	public static Map<String, String> parseNotificationJavascriptObjct(JavaScriptObject notificationjs)
	{
		Map<String, String> map =  new HashMap<String, String>();
		parseNotificationJavascriptIntoMap(map, notificationjs);
		
		if (map.containsKey("userdata"))
		{
			JSONObject obj = JSONParser.parseStrict(map.get("userdata")).isObject();
			String messageType = obj.get("messageType").isString().toString().replaceAll("\"", "");
			map.put("messageType", messageType);
			String applicationID = obj.get("applicationID").isString().toString().replaceAll("\"", "");
			map.put("applicationID", applicationID);
			map.remove("userdata");
			map.put("pushStart", "true");
		}
		
		return map;
	}
	
	private static native void parseNotificationJavascriptIntoMap(Map<String, String> map,
			JavaScriptObject notificationjs)/*-{
		// Check if it is android notification data
		if (notificationjs.hasOwnProperty("userdata")) 
		{
			// android notification data is inside userData
			var userData = notificationjs["userdata"];
			map.@java.util.Map::put(Ljava/lang/Object;Ljava/lang/Object;)("userdata", userData);
			
		}
		else // on iOS all the key-value are flat
		{
			for ( var prop in notificationjs) {
				if (notificationjs.hasOwnProperty(prop)) {
					map.@java.util.Map::put(Ljava/lang/Object;Ljava/lang/Object;)(prop, notificationjs[prop]);
				}
			}
		}
		
	}-*/;
	
			

}
