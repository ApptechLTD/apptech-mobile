//
//  VersionHelper.java
//
// Pushwoosh Push Notifications SDK
// www.pushwoosh.com
//
// MIT Licensed
package com.apptech.first.cordova.plugins.push.request.versions;

import android.content.Context;
import android.location.Location;
import com.apptech.first.cordova.plugins.push.data.PushZoneLocation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Date: 17.08.12
 * Time: 10:27
 *
 * @author mig35
 */
public interface VersionHelper
{
	Map<String, Object> getRegistrationUnregistrationData(Context context, String deviceRegistrationID);

	Map<String, Object> getSendPushStatData(Context context, String hash);
	
	Map<String, Object> getSendGoalAchievedData(Context context, String goal, Integer count);

	Map<String,Object> getSendTagsData(Context context);

	Map<String,Object> getNearestZoneData(Context context, Location location);

	PushZoneLocation getPushZoneLocationFromData(JSONObject resultData) throws JSONException;

	Map<String, Object> getSendAppOpenData(Context context);
}
