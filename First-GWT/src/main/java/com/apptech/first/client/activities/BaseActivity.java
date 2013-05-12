package com.apptech.first.client.activities;


import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.First;
import com.apptech.first.client.places.LoginPlace;
import com.apptech.first.client.service.MSLServiceAsync;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class BaseActivity extends AbstractActivity {

	protected ClientFactory clientFactory;

	protected MSLServiceAsync service = MSLServiceAsync.helper.getService(); 
	
	public BaseActivity(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}
	
	/**
     * Ask user before stopping this activity
     */
    @Override
    public String mayStop() {
        //return "Please hold on. This activity is stopping.";
    	return null;
    }
	
    /**
     * Navigate to a new Place in the browser
     */
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }
    
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
    	//check if the user is set
    	if(First.getActiveUser() != null){
    		//continue
    		startImpl(panel, eventBus);
    	}
    	else{
    		//go to login screen
    		goTo(new LoginPlace());
    	}
    }

	protected abstract void startImpl(AcceptsOneWidget panel, EventBus eventBus);
}
