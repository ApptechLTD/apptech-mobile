package nz.govt.studylink.mslapp.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class MailOnlinePlace extends Place {

	public MailOnlinePlace( ){
	}
	
	public static class Tokenizer implements PlaceTokenizer<MailOnlinePlace> {
		@Override
		public MailOnlinePlace getPlace(String token) {
			return new MailOnlinePlace();
		}

		@Override
		public String getToken(MailOnlinePlace place) {
			return null;
		}        
    }
}
