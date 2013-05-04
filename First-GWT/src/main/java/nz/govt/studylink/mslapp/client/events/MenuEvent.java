package nz.govt.studylink.mslapp.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class MenuEvent extends GwtEvent<MenuEventHandler> {

	public static Type<MenuEventHandler> TYPE = new Type<MenuEventHandler>();

	private MenuAction action;
	
	public MenuEvent(MenuAction action){
		this.action = action;
	}
	
	@Override
	public Type<MenuEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(MenuEventHandler handler) {
		if(MenuAction.DISPLAY_MENU.equals(action)){
			handler.onDisplayMenu(this);
		}
		//add more actions
	}
}