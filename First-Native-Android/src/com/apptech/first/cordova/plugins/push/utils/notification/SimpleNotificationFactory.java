package com.apptech.first.cordova.plugins.push.utils.notification;

import com.apptech.first.cordova.plugins.push.preference.SoundType;
import com.apptech.first.cordova.plugins.push.preference.VibrateType;
import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

/**
 * Date: 28.09.12
 * Time: 12:08
 *
 * @author MiG35
 */
public class SimpleNotificationFactory extends BaseNotificationFactory
{
	public SimpleNotificationFactory(Context context, Bundle data, String appName, String title, SoundType soundType,
			VibrateType vibrateType)
	{
		super(context, data, appName, title, soundType, vibrateType);
	}

	@Override
	Notification generateNotificationInner(Context context, Bundle data, String appName, String tickerTitle)
	{
		Notification notification;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			notification = V11NotificationCreator.generateNotification(context, data, tickerTitle);
		}
		else
		{
			notification = NotificationCreator.generateNotification(context, data, tickerTitle);
		}
		notification.setLatestEventInfo(context, appName, getTitle(), notification.contentIntent);

		return notification;
	}

}
