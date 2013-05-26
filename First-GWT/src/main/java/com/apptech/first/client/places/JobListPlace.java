package com.apptech.first.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class JobListPlace extends Place {

	public JobListPlace( ){
	}
	
	public static class Tokenizer implements PlaceTokenizer<JobListPlace> {
		@Override
		public JobListPlace getPlace(String token) {
			return new JobListPlace();
		}

		@Override
		public String getToken(JobListPlace place) {
			return null;
		}        
    }
}
