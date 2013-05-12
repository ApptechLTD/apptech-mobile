package com.apptech.first.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LoginSuccessEvent extends GwtEvent<LoginSuccessEventHandler> {

	public static Type<LoginSuccessEventHandler> TYPE = new Type<LoginSuccessEventHandler>();
	
	public LoginSuccessEvent(){
		
	}
	
	
	@Override
	public Type<LoginSuccessEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoginSuccessEventHandler handler) {
		
		handler.onLoginSuccess(this);
	}

}