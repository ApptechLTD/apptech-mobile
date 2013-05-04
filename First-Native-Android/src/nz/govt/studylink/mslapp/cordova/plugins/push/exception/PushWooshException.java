//
//  PushWooshException.java
//
// Pushwoosh Push Notifications SDK
// www.pushwoosh.com
//
// MIT Licensed
package nz.govt.studylink.mslapp.cordova.plugins.push.exception;

/**
 * Date: 16.08.12
 * Time: 21:45
 *
 * @author mig35
 */
public class PushWooshException extends Exception
{
	private static final long serialVersionUID = 1L;

	public PushWooshException(Exception e)
	{
		super(e);
	}

	public PushWooshException(String s)
	{
		super(s);
	}
}
