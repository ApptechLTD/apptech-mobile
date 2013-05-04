package nz.govt.studylink.mslapp.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface MenuEventHandler extends EventHandler {
    void onDisplayMenu(MenuEvent menuEvent);
    
}