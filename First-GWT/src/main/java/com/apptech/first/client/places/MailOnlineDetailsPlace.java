package com.apptech.first.client.places;


import com.apptech.first.shared.model.MailOnlineModel;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author Julio Helden
 *
 */
public class MailOnlineDetailsPlace extends Place {

	private MailOnlineModel model;
	private Long id;
	
	public MailOnlineDetailsPlace(){
		
	}
	
	public MailOnlineDetailsPlace(MailOnlineModel model) {
		this.model = model;
		this.id = model.getId();
	}

	public MailOnlineDetailsPlace(Long id) {
		this.id = id;
	}

	public MailOnlineModel getMailOnlineModel() {
		return model;
	}
	
	public static class Tokenizer implements PlaceTokenizer<MailOnlineDetailsPlace> {
		@Override
		public MailOnlineDetailsPlace getPlace(String token) {
			return new MailOnlineDetailsPlace(new Long(token));
		}

		@Override
		public String getToken(MailOnlineDetailsPlace place) {
			return place.getId().toString();
		}  
    }

	public Long getId() {
		return id;
	}
}
