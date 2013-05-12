package com.apptech.first.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoginEventHandler extends EventHandler {

	void onLogin(LoginEvent loginEvent);
}