package nz.govt.studylink.mslapp.client;

import nz.govt.studylink.mslapp.client.places.AppMenuPlace;
import nz.govt.studylink.mslapp.client.places.ApplicationDetailsPlace;
import nz.govt.studylink.mslapp.client.places.DashboardPlace;
import nz.govt.studylink.mslapp.client.places.LoginPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlineDetailsPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlinePlace;
import nz.govt.studylink.mslapp.client.places.PaymentDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsUpdatePlace;

import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.mvp.client.AnimationMapper;

public class PhoneAnimationMapper implements AnimationMapper {

    @Override
    public Animation getAnimation(Place oldPlace, Place newPlace) {
    	
        //decide which animation to use between places
    	if (newPlace instanceof AppMenuPlace) {
            return Animation.POP_REVERSE;
    	}
    	
    	if (oldPlace instanceof LoginPlace && newPlace instanceof DashboardPlace) {
            return Animation.SLIDE_UP_REVERSE;
    	}
    	
    	//Mail online
    	if (oldPlace instanceof MailOnlinePlace && newPlace instanceof MailOnlineDetailsPlace) {
            return Animation.SLIDE;
    	}
    	
    	if (oldPlace instanceof MailOnlineDetailsPlace && newPlace instanceof MailOnlinePlace) {
            return Animation.SLIDE_REVERSE;
    	}
    	
    	//PaymentDetailsPlace
    	if (oldPlace instanceof DashboardPlace && newPlace instanceof PaymentDetailsPlace) {
            return Animation.SLIDE;
    	}
    	
    	if (oldPlace instanceof PaymentDetailsPlace && newPlace instanceof DashboardPlace) {
            return Animation.SLIDE_REVERSE;
    	}
    	
    	//ApplicationDetailsPlace
    	if (oldPlace instanceof DashboardPlace && newPlace instanceof ApplicationDetailsPlace) {
            return Animation.SLIDE;
    	}
    	
    	if (oldPlace instanceof ApplicationDetailsPlace && newPlace instanceof DashboardPlace) {
            return Animation.SLIDE_REVERSE;
    	}
    	
    	if (oldPlace instanceof PersonalDetailsPlace && newPlace instanceof PersonalDetailsUpdatePlace) {
    		return Animation.FLIP_REVERSE;
    	}
    	
    	if (oldPlace instanceof PersonalDetailsUpdatePlace && newPlace instanceof PersonalDetailsPlace) {
    		return Animation.FLIP_REVERSE;
    	}
        
    	return Animation.FADE;
    }
}
