package nz.govt.studylink.mslapp.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoginSuccessEventHandler extends EventHandler {

	void onLoginSuccess(LoginSuccessEvent event);
	
}