package com.apptech.first.client;


import com.apptech.first.client.activities.AppMenuActivity;
import com.apptech.first.client.activities.ApplicationDetailsActivity;
import com.apptech.first.client.activities.DashboardActivity;
import com.apptech.first.client.activities.LoginActivity;
import com.apptech.first.client.activities.MailOnlineActivity;
import com.apptech.first.client.activities.MailOnlineDetailsActivity;
import com.apptech.first.client.activities.PaymentDetailsActivity;
import com.apptech.first.client.activities.PersonalDetailsActivity;
import com.apptech.first.client.activities.PersonalDetailsUpdateActivity;
import com.apptech.first.client.places.AppMenuPlace;
import com.apptech.first.client.places.ApplicationDetailsPlace;
import com.apptech.first.client.places.DashboardPlace;
import com.apptech.first.client.places.LoginPlace;
import com.apptech.first.client.places.MailOnlineDetailsPlace;
import com.apptech.first.client.places.MailOnlinePlace;
import com.apptech.first.client.places.PaymentDetailsPlace;
import com.apptech.first.client.places.PersonalDetailsPlace;
import com.apptech.first.client.places.PersonalDetailsUpdatePlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

	private ClientFactory clientFactory;
	
	public AppActivityMapper(ClientFactory clientFactory){
		this.clientFactory = clientFactory;
	}
	
	/**
	 * Creates a new activity accordingly to the place
	 */
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof DashboardPlace) {
            return new DashboardActivity((DashboardPlace) place, clientFactory);
		}
		
		if (place instanceof PaymentDetailsPlace) {
            return new PaymentDetailsActivity((PaymentDetailsPlace) place, clientFactory);
		}
		
		if (place instanceof ApplicationDetailsPlace) {
            return new ApplicationDetailsActivity((ApplicationDetailsPlace) place, clientFactory);
		}
		
		if (place instanceof AppMenuPlace) {
            return new AppMenuActivity((AppMenuPlace) place, clientFactory);
		}
		
		if(place instanceof LoginPlace){
			return new LoginActivity((LoginPlace) place, clientFactory);
		}
		
		if(place instanceof PersonalDetailsPlace){
			return new PersonalDetailsActivity((PersonalDetailsPlace) place, clientFactory);
		}
		
		if(place instanceof PersonalDetailsUpdatePlace){
			return new PersonalDetailsUpdateActivity((PersonalDetailsUpdatePlace) place, clientFactory);
		}
		
		if(place instanceof MailOnlinePlace){
			return new MailOnlineActivity((MailOnlinePlace) place, clientFactory);
		}
		
		if(place instanceof MailOnlineDetailsPlace){
			return new MailOnlineDetailsActivity((MailOnlineDetailsPlace) place, clientFactory);
		}
		
        return null;
	}	
}