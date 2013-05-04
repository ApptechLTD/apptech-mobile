package nz.govt.studylink.mslapp.client;

import nz.govt.studylink.mslapp.client.cordova.plugins.NotificationPlugin;
import nz.govt.studylink.mslapp.client.cordova.plugins.PDFPlugin;
import nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPlugin;
import nz.govt.studylink.mslapp.client.util.MSLStorage;
import nz.govt.studylink.mslapp.client.views.AppMenuView;
import nz.govt.studylink.mslapp.client.views.ApplicationDetailsView;
import nz.govt.studylink.mslapp.client.views.DashboardView;
import nz.govt.studylink.mslapp.client.views.LoginView;
import nz.govt.studylink.mslapp.client.views.MailOnlineDetailsView;
import nz.govt.studylink.mslapp.client.views.MailOnlineView;
import nz.govt.studylink.mslapp.client.views.PaymentDetailsView;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsUpdateView;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsView;

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