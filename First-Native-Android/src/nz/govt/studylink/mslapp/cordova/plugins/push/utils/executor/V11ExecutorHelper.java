//
//  V11ExecutorHelper.java
//
// Pushwoosh Push Notifications SDK
// www.pushwoosh.com
//
// MIT Licensed
package nz.govt.studylink.mslapp.cordova.plugins.push.utils.executor;

import android.os.AsyncTask;

/**
 * User: MiG35
 * Date: 07.08.12
 * Time: 9:54
 */
public class V11ExecutorHelper
{
    public static void executeOnExecutor(AsyncTask<Void, Void, Void> asyncTask)
    {
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void) null);
    }
}
