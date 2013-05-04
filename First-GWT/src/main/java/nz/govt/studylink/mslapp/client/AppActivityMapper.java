package nz.govt.studylink.mslapp.client;

import nz.govt.studylink.mslapp.client.activities.AppMenuActivity;
import nz.govt.studylink.mslapp.client.activities.ApplicationDetailsActivity;
import nz.govt.studylink.mslapp.client.activities.DashboardActivity;
import nz.govt.studylink.mslapp.client.activities.LoginActivity;
import nz.govt.studylink.mslapp.client.activities.MailOnlineActivity;
import nz.govt.studylink.mslapp.client.activities.MailOnlineDetailsActivity;
import nz.govt.studylink.mslapp.client.activities.PaymentDetailsActivity;
import nz.govt.studylink.mslapp.client.activities.PersonalDetailsActivity;
import nz.govt.studylink.mslapp.client.activities.PersonalDetailsUpdateActivity;
import nz.govt.studylink.mslapp.client.places.AppMenuPlace;
import nz.govt.studylink.mslapp.client.places.ApplicationDetailsPlace;
import nz.govt.studylink.mslapp.client.places.DashboardPlace;
import nz.govt.studylink.mslapp.client.places.LoginPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlineDetailsPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlinePlace;
import nz.govt.studylink.mslapp.client.places.PaymentDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsUpdatePlace;

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