package nz.govt.studylink.mslapp.client.places;

import nz.govt.studylink.mslapp.shared.model.PaymentsModel;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

/**
 * Basic Place that does not carry any information.
 * 
 * @author rafaelalmeida
 *
 */
public class PaymentDetailsPlace extends Place {

	private PaymentsModel model;
	private Long id;
	
	public PaymentDetailsPlace(){
		
	}
	
	public PaymentDetailsPlace(PaymentsModel model) {
		this.model = model;
		this.id = model.getId();
	}

	public PaymentDetailsPlace(Long id) {
		this.id = id;
	}

	public PaymentsModel getPaymentsModel() {
		return model;
	}
	
	public static class Tokenizer implements PlaceTokenizer<PaymentDetailsPlace> {
		@Override
		public PaymentDetailsPlace getPlace(String token) {
			return new PaymentDetailsPlace(new Long(token));
		}

		@Override
		public String getToken(PaymentDetailsPlace place) {
			return place.getId().toString();
		}  
    }

	public Long getId() {
		return id;
	}
}
