package nz.govt.studylink.mslapp.cordova.plugins.push.utils.notification;

import android.app.Notification;

/**
 * Date: 30.10.12
 * Time: 18:14
 *
 * @author MiG35
 */
public interface NotificationFactory
{
	void generateNotification();

	void addSoundAndVibrate();

	void addCancel();

	Notification getNotification();

}
