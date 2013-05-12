package com.apptech.first.client;


import com.apptech.first.client.places.AppMenuPlace;
import com.apptech.first.client.places.ApplicationDetailsPlace;
import com.apptech.first.client.places.DashboardPlace;
import com.apptech.first.client.places.LoginPlace;
import com.apptech.first.client.places.MailOnlineDetailsPlace;
import com.apptech.first.client.places.MailOnlinePlace;
import com.apptech.first.client.places.PaymentDetailsPlace;
import com.apptech.first.client.places.PersonalDetailsPlace;
import com.apptech.first.client.places.PersonalDetailsUpdatePlace;
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