package nz.govt.studylink.mslapp.client;

import nz.govt.studylink.mslapp.client.places.AppMenuPlace;
import nz.govt.studylink.mslapp.client.places.ApplicationDetailsPlace;
import nz.govt.studylink.mslapp.client.places.DashboardPlace;
import nz.govt.studylink.mslapp.client.places.LoginPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlineDetailsPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlinePlace;
import nz.govt.studylink.mslapp.client.places.PaymentDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsUpdatePlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
	DashboardPlace.Tokenizer.class, 
	AppMenuPlace.Tokenizer.class,
	PaymentDetailsPlace.Tokenizer.class,
	ApplicationDetailsPlace.Tokenizer.class,
	LoginPlace.Tokenizer.class,
	MailOnlinePlace.Tokenizer.class,
	MailOnlineDetailsPlace.Tokenizer.class,
	PersonalDetailsPlace.Tokenizer.class,
	PersonalDetailsUpdatePlace.Tokenizer.class
	})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper
{
}