package nz.govt.studylink.mslapp.client;

import nz.govt.studylink.mslapp.client.cordova.plugins.NotificationPlugin;
import nz.govt.studylink.mslapp.client.cordova.plugins.PDFPlugin;
import nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPlugin;
import nz.govt.studylink.mslapp.client.util.MSLStorage;
import nz.govt.studylink.mslapp.client.util.MSLStorageImpl;
import nz.govt.studylink.mslapp.client.views.AppMenuView;
import nz.govt.studylink.mslapp.client.views.AppMenuViewImpl;
import nz.govt.studylink.mslapp.client.views.ApplicationDetailsView;
import nz.govt.studylink.mslapp.client.views.ApplicationDetailsViewImpl;
import nz.govt.studylink.mslapp.client.views.DashboardView;
import nz.govt.studylink.mslapp.client.views.DashboardViewImpl;
import nz.govt.studylink.mslapp.client.views.LoginView;
import nz.govt.studylink.mslapp.client.views.LoginViewImpl;
import nz.govt.studylink.mslapp.client.views.MailOnlineDetailsView;
import nz.govt.studylink.mslapp.client.views.MailOnlineDetailsViewImpl;
import nz.govt.studylink.mslapp.client.views.MailOnlineView;
import nz.govt.studylink.mslapp.client.views.MailOnlineViewImpl;
import nz.govt.studylink.mslapp.client.views.PaymentDetailsView;
import nz.govt.studylink.mslapp.client.views.PaymentDetailsViewImpl;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsUpdateView;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsUpdateViewImpl;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsView;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsViewImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.googlecode.gwtphonegap.client.PhoneGap;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    
    private MSLStorage mslStorage;

    private DashboardView dashboardView;
    
    private MailOnlineView mailOnlineView;
    private MailOnlineDetailsView mailOnlineDetailsView;
    
    private AppMenuView appMenuView;
    
    private PaymentDetailsView paymentDetailsView;
    private ApplicationDetailsView applicationDetailsView;
	
    private LoginView loginView;
	
    private PersonalDetailsView personalDetailsView;
    private PersonalDetailsUpdateView personalDetailsUpdateView;
    

    private PhoneGap phoneGap;
    private NotificationPlugin notificationPlugin;
    private UserSettingPlugin userSettingPlugin;
    private PDFPlugin pdfPlugin;

    @Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public AppMenuView getAppMenuView() {
		if(appMenuView == null){
			appMenuView = new AppMenuViewImpl();
		}
		return appMenuView;
	}	
	
	@Override
	public DashboardView getDashboardView() {
		if(dashboardView == null){
			dashboardView = new DashboardViewImpl();
		}
		return dashboardView;
	}
	
	@Override
	public MailOnlineView getMailOnlineView() {
		if(mailOnlineView == null){
			mailOnlineView = new MailOnlineViewImpl();
		}
		return mailOnlineView;
	}

	@Override
	public PaymentDetailsView getPaymentDetailsView() {
		if(paymentDetailsView == null){
			paymentDetailsView = new PaymentDetailsViewImpl();
		}
		return paymentDetailsView;
	}	
	
	@Override
	public LoginView getLoginView() {
		if(loginView == null){
			loginView = new LoginViewImpl();
		}
		return loginView;
	}
	
	@Override
	public PersonalDetailsView getPersonalDetailsView() {
		if(personalDetailsView == null){
			personalDetailsView = new PersonalDetailsViewImpl();
		}
		return personalDetailsView;
	}
	
	@Override
	public ApplicationDetailsView getApplicationDetailsView() {
		if(applicationDetailsView == null){
			applicationDetailsView = new ApplicationDetailsViewImpl();
		}
		return applicationDetailsView;
	}

	@Override
	public PersonalDetailsUpdateView getPersonalDetailsUpdateView() {
		if(personalDetailsUpdateView == null){
			personalDetailsUpdateView = new PersonalDetailsUpdateViewImpl();
		}
		return personalDetailsUpdateView;
	}	
	
	@Override
	public MailOnlineDetailsView getMailOnlineDetailsView() {
		if(mailOnlineDetailsView == null){
			mailOnlineDetailsView = new MailOnlineDetailsViewImpl();
		}
		return mailOnlineDetailsView;
	}
	
	 @Override
	public PhoneGap getPhoneGap() {
		if(phoneGap == null){
			phoneGap = GWT.create(PhoneGap.class); // the phonegap wrapper class instance
		}
		return phoneGap;
	}
	 
	public NotificationPlugin getNotificationPlugin() {
		if(notificationPlugin == null){
			notificationPlugin = GWT.create(NotificationPlugin.class); // the push notification management class instance
		}
		return notificationPlugin;
	}
	
	public UserSettingPlugin getUserSettingPlugin() {
		if(userSettingPlugin == null){
			userSettingPlugin = GWT.create(UserSettingPlugin.class); // the phonegap wrapper class instance
		}
		return userSettingPlugin;
	}
	
	public PDFPlugin getPDFPlugin() {
		if(pdfPlugin == null){
			pdfPlugin = GWT.create(PDFPlugin.class); // the PDF management class instance
		}
		return pdfPlugin;
	}
	
	public MSLStorage getStorage()
	{
		if (mslStorage == null)
		{
			mslStorage = new MSLStorageImpl();
		}
		return mslStorage;
	}
}
