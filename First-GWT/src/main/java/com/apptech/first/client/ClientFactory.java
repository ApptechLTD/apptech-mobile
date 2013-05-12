package com.apptech.first.client;


import com.apptech.first.client.cordova.plugins.NotificationPlugin;
import com.apptech.first.client.cordova.plugins.PDFPlugin;
import com.apptech.first.client.cordova.plugins.UserSettingPlugin;
import com.apptech.first.client.util.MSLStorage;
import com.apptech.first.client.views.AppMenuView;
import com.apptech.first.client.views.ApplicationDetailsView;
import com.apptech.first.client.views.DashboardView;
import com.apptech.first.client.views.LoginView;
import com.apptech.first.client.views.MailOnlineDetailsView;
import com.apptech.first.client.views.MailOnlineView;
import com.apptech.first.client.views.PaymentDetailsView;
import com.apptech.first.client.views.PersonalDetailsUpdateView;
import com.apptech.first.client.views.PersonalDetailsView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.googlecode.gwtphonegap.client.PhoneGap;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	// Views
	DashboardView getDashboardView();

	MailOnlineView getMailOnlineView();

	AppMenuView getAppMenuView();
    
	PaymentDetailsView getPaymentDetailsView();

	ApplicationDetailsView getApplicationDetailsView();

	MailOnlineDetailsView getMailOnlineDetailsView();

	LoginView getLoginView();

	PersonalDetailsView getPersonalDetailsView();
	
	PersonalDetailsUpdateView getPersonalDetailsUpdateView();

	PhoneGap getPhoneGap();

	NotificationPlugin getNotificationPlugin();
	
	UserSettingPlugin getUserSettingPlugin();
	
	PDFPlugin getPDFPlugin();

	MSLStorage getStorage();
}