package com.apptech.first.cordova.plugins.push.utils.notification;

import com.apptech.first.cordova.plugins.push.preference.SoundType;
import com.apptech.first.cordova.plugins.push.preference.VibrateType;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Date: 30.10.12
 * Time: 18:08
 *
 * @author MiG35
 */
public class BannerNotificationFactory extends BaseNotificationFactory
{
	public static final String sNotificationLayout = "notification";

	public BannerNotificationFactory(Context context, Bundle data, String appName, String title, SoundType soundType, VibrateType vibrateType)
	{
		super(context, data, appName, title, soundType, vibrateType);
	}

	@SuppressLint("NewApi")
	@Override
	Notification generateNotificationInner(Context context, Bundle data, String appName, String tickerTitle)
	{
		Notification notification = new Notification();

		int layoutId = getContext().getResources().getIdentifier(sNotificationLayout, "layout", getContext().getPackageName());

		if (0 == layoutId)
		{
			throw new IllegalArgumentException();
		}

		RemoteViews remoteViews = new RemoteViews(getContext().getPackageName(), layoutId);

		String link = getData().getString("b");
		//link = "https://cp.pushwoosh.com/img/arello-logo.png";
		Bitmap bitmap = Helper.tryToGetBitmapFromInternet(link, getContext(), -1);

		if (null != bitmap)
		{
			remoteViews.setBitmap(getContext().getResources().getIdentifier("image", "id", getContext().getPackageName()), "setImageBitmap", bitmap);
		}
		else
		{
			remoteViews.setBitmap(getContext().getResources().getIdentifier("image", "id", getContext().getPackageName()), "setImageBitmap",
					((BitmapDrawable) getContext().getResources().getDrawable(getContext().getApplicationInfo().icon)).getBitmap());
		}

		notification.contentView = remoteViews;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
		{
			notification.bigContentView = remoteViews;
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
		{
			notification.tickerView = remoteViews;
		}
		else
		{
			notification.tickerText = tickerTitle;
		}

		notification.icon = Helper.tryToGetIconFormStringOrGetFromApplication(getData().getString("i"), getContext());

		return notification;
	}
}
