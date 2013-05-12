package com.apptech.first.cordova.plugins.push.tags;


import java.util.Map;

import com.apptech.first.cordova.plugins.push.exception.PushWooshException;

/**
 * Date: 27.08.12
 * Time: 14:37
 *
 * @author MiG35
 */
public interface SendPushTagsCallBack
{
	void taskStarted();

	void onSentTagsSuccess(Map<String, String> skippedTags);

	void onSentTagsError(PushWooshException error);
}
