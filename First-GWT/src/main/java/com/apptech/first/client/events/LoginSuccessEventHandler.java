package com.apptech.first.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoginSuccessEventHandler extends EventHandler {

	void onLoginSuccess(LoginSuccessEvent event);
	
}