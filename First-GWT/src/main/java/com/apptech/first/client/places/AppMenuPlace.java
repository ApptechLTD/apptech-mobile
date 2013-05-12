package com.apptech.first.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class AppMenuPlace extends Place {

	public AppMenuPlace( ){
	}
	
	public static class Tokenizer implements PlaceTokenizer<AppMenuPlace> {
		@Override
		public AppMenuPlace getPlace(String token) {
			return new AppMenuPlace();
		}

		@Override
		public String getToken(AppMenuPlace place) {
			return null;
		}        
    }
}
