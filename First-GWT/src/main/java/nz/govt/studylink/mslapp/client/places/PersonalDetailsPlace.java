package nz.govt.studylink.mslapp.client.places;

import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author Rafael Almeida
 * 
 */
public class PersonalDetailsPlace extends Place {

	private PersonalDetailsModel model;

	public PersonalDetailsPlace() {

	}

	public PersonalDetailsPlace(PersonalDetailsModel model) {
		this.model = model;
	}

	public PersonalDetailsModel getPersonalDetailsModel() {
		return model;
	}

	public void setPersonalDetailsModel(PersonalDetailsModel model) {
		this.model = model;
	}

	public static class Tokenizer implements
			PlaceTokenizer<PersonalDetailsPlace> {
		@Override
		public PersonalDetailsPlace getPlace(String token) {
			return new PersonalDetailsPlace();
		}

		@Override
		public String getToken(PersonalDetailsPlace place) {
			return null;
		}
	}
}
