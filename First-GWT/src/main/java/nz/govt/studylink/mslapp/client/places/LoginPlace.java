package nz.govt.studylink.mslapp.client.places;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class LoginPlace extends Place {

	public LoginPlace( ){
	}
	
	public static class Tokenizer implements PlaceTokenizer<LoginPlace> {
		@Override
		public LoginPlace getPlace(String token) {
			return new LoginPlace();
		}

		@Override
		public String getToken(LoginPlace place) {
			return null;
		}        
    }
}
