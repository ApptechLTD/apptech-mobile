package com.apptech.first.client.places;


import com.apptech.first.shared.model.ApplicationsModel;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class ApplicationDetailsPlace extends Place {

	private ApplicationsModel model;
	private Long id;
	
	
	public ApplicationDetailsPlace( ){
	}
	
	public ApplicationDetailsPlace(ApplicationsModel model) {
		this.model = model;
		this.id = model.getId();
	}
	
	public ApplicationDetailsPlace(Long id) {
		this.id = id;
	}

	public ApplicationsModel getApplicationsModel() {
		return model;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}



	public static class Tokenizer implements PlaceTokenizer<ApplicationDetailsPlace> {
		@Override
		public ApplicationDetailsPlace getPlace(String token) {
			return new ApplicationDetailsPlace(new Long(token));
		}

		@Override
		public String getToken(ApplicationDetailsPlace place) {
			return place.getId().toString();
		}        
    }

	public Long getId() {
		return id;
	}
}
