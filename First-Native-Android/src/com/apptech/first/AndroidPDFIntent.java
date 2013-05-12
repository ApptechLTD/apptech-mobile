package com.apptech.first;

import org.apache.cordova.api.CallbackContext;

import android.app.Activity;
import android.content.Intent;

public class AndroidPDFIntent extends Intent {

	private CallbackContext callbackContext;
	
	public AndroidPDFIntent(Activity activity, Class<AndroidPDFActivity> clazz, 
							CallbackContext callbackContext) {
		
		super(activity, clazz);
		this.callbackContext = callbackContext;
		
	}

	
	public CallbackContext getCallbackContext() {
		return callbackContext;
	}
	
	public void setCallbackContext(CallbackContext callbackContext) {
		this.callbackContext = callbackContext;
	}
}
