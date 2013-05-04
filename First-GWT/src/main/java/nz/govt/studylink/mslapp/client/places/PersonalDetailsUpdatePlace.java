package nz.govt.studylink.mslapp.client.places;

import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class PersonalDetailsUpdatePlace extends Place {
	private PersonalDetailsModel model;

	public PersonalDetailsUpdatePlace() {

	}

	public PersonalDetailsUpdatePlace(PersonalDetailsModel model) {
		this.model = model;
	}

	public PersonalDetailsModel getPersonalDetailsModel() {
		return model;
	}

	public void setModel(PersonalDetailsModel model) {
		this.model = model;
	}

	public static class Tokenizer implements
			PlaceTokenizer<PersonalDetailsUpdatePlace> {

		@Override
		public PersonalDetailsUpdatePlace getPlace(String token) {
			return new PersonalDetailsUpdatePlace();
		}

		@Override
		public String getToken(PersonalDetailsUpdatePlace place) {
			return null;
		}
	}
}
