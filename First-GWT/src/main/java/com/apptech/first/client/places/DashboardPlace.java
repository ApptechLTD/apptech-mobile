package com.apptech.first.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class DashboardPlace extends Place {

	public DashboardPlace( ){
	}
	
	public static class Tokenizer implements PlaceTokenizer<DashboardPlace> {
		@Override
		public DashboardPlace getPlace(String token) {
			return new DashboardPlace();
		}

		@Override
		public String getToken(DashboardPlace place) {
			return null;
		}        
    }
}
