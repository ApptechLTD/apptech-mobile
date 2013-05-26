package com.apptech.first.client;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.apptech.first.client.cordova.plugins.UserSettingPlugin;
import com.apptech.first.client.cordova.plugins.UserSettingPlugin.UserSettingPluginCallback;
import com.apptech.first.client.events.LoginSuccessEvent;
import com.apptech.first.client.events.LoginSuccessEventHandler;
import com.apptech.first.client.events.MenuEvent;
import com.apptech.first.client.events.MenuEventHandler;
import com.apptech.first.client.events.NotificationArrivedEvent;
import com.apptech.first.client.events.NotificationArrivedEventHandler;
import com.apptech.first.client.places.AppMenuPlace;
import com.apptech.first.client.places.ApplicationDetailsPlace;
import com.apptech.first.client.places.DashboardPlace;
import com.apptech.first.client.places.LoginPlace;
import com.apptech.first.client.places.MailOnlinePlace;
import com.apptech.first.shared.model.UserModel;
import com.apptech.first.theme.client.CustomTheme;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.mgwt.mvp.client.AnimatableDisplay;
import com.googlecode.mgwt.mvp.client.AnimatingActivityManager;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.MGWTStyle;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class First implements EntryPoint {

	private Logger logger = Logger.getLogger("MSLApp");

	// Default view to be displayed when the application is loaded
	private Place defaultPlace = new LoginPlace();

	private static ClientFactory clientFactory = GWT
			.create(ClientFactory.class);

	public static ClientFactory getClientFactory() {
		return clientFactory;
	}

	public static CustomTheme THEME = new CustomTheme();

	private static UserModel activeUser;

	public static void setActiveUser(UserModel user) {
		activeUser = user;
	}

	public static UserModel getActiveUser() {
		return activeUser;
	}

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//handler to start the app
		//it only starts when PhoneGap is ready !
		clientFactory.getPhoneGap().addHandler(new PhoneGapAvailableHandler() {

			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				// start your app - phonegap is ready
				phoneGapAvailable(event);	
			}
		});
		
		//initialize phonegap
		clientFactory.getPhoneGap().initializePhoneGap();
	}

	protected void phoneGapAvailable(PhoneGapAvailableEvent event) {
		logger.info("Starting MVP on MSLApp.");

		// Read user setting for server url
		logger.info("Read user settings");
		clientFactory.getUserSettingPlugin().init(new UserSettingPluginCallback() {
			
			@Override
			public void onUserSettingReady() {
				onUserSettingAvailable();
			}
			
			@Override
			public void onUserSettingFail(String message) {
				logger.info("onUserSettingFail() Read user setting error !!!!! message:" + message);
				
			}
		});
	}
	
	// The user setting read successfully event handler.  After phonegapready, anything else has to be started after this method is invoked.
	private void onUserSettingAvailable() {
		MGWTStyle.setTheme(THEME);

		// set viewport and other settings for mobile
		MGWTSettings appSettings = new MGWTSettings();
		appSettings = MGWTSettings.getAppSetting();
		appSettings.setDisablePhoneNumberDetection(true);
		MGWT.applySettings(appSettings);

		// Start MVP Stuff
		EventBus eventBus = clientFactory.getEventBus();

		PlaceController placeController = clientFactory.getPlaceController();

		// Center Panel (AnimatableDisplay is form MGWT)
		// SimplePanel display = new SimplePanel();
		AnimatableDisplay display = GWT.create(AnimatableDisplay.class);

		// SimplePanel centerContainer = new SimplePanel();
		RootPanel.get("center").add(display);

		// instantiate your animationMapper
		PhoneAnimationMapper appAnimationMapper = new PhoneAnimationMapper();

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityMapper activityMapper = new AppActivityMapper(clientFactory);

		// AnimatingActivityManager is form MGWT
		AnimatingActivityManager activityManager = new AnimatingActivityManager(
				activityMapper, appAnimationMapper, clientFactory.getEventBus());
		// ActivityManager activityManager = new ActivityManager(activityMapper,
		// eventBus);
		activityManager.setDisplay(display);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);

		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
	
		clientFactory.getNotificationPlugin().Init(clientFactory); // create push notification plugin instance
		
		logger.info("onUserSettingAvailable() Read user setting sucessfully. serverUrl = " + 
		clientFactory.getUserSettingPlugin().getUserSetting().get(UserSettingPlugin.APP_URL));
		logger.info("onUserSettingAvailable() going to default place");
		configEventHandlers();
	}

	
	/**
	 * Method responsible to register all event handlers required by the app on start-up
	 * 
	 */
	private void configEventHandlers() {
		EventBus eventBus = clientFactory.getEventBus();

		eventBus.addHandler(MenuEvent.TYPE, new MenuEventHandler() {
			@Override
			public void onDisplayMenu(MenuEvent menuEvent) {
				// display the menu
				clientFactory.getPlaceController().goTo(new AppMenuPlace());
			}
		});
		
		configPushEventHandler(eventBus);
		
		configLoginSuccessEventHandler(eventBus);
		
		//create menu view to listen for notification events
		clientFactory.getAppMenuView();
		
	}
	
	
	private Map<String, String> currentNotificationData;
	private void configPushEventHandler(EventBus eventBus) {
		// Handle the push arrive event
		eventBus.addHandler(NotificationArrivedEvent.TYPE, new NotificationArrivedEventHandler() {
			@Override
			public void onNotificationArrived(NotificationArrivedEvent notificationEvent) {

				Map<String, String> notification = notificationEvent.getNotification(); 
				// check if it is login
				if	(getActiveUser() == null) // not login, keep the notification data
				{
					currentNotificationData = notification;
				}
				else // already login, check if it is push start and jump to specify page
				{
					handlePushNotificationNavigation(notification);
				}
				
			}
		});
	}

	private void configLoginSuccessEventHandler(EventBus eventBus) {
		// Handle the push arrive event
		eventBus.addHandler(LoginSuccessEvent.TYPE, new LoginSuccessEventHandler() {
			@Override
			public void onLoginSuccess(LoginSuccessEvent event) {

				// Check if there is any push data
				if (currentNotificationData == null)
				{
					// goto dashboard
					DashboardPlace place = new DashboardPlace();
					clientFactory.getPlaceController().goTo(place);

					// user selected.
					try {
						clientFactory.getNotificationPlugin().registerDevice(true,
								true, false, "6DFBA-FE4D1", "MyStudyLink", "472407715256");
					} catch (Exception e) {
						logger.info("onLoginSuccess() Error calling registerDevice : " + e);
					}
				}
				else
				{
					handlePushNotificationNavigation(currentNotificationData);
					currentNotificationData = null; // clear the temple data after processing
				}
				
			}
		});
	}
	
	
	private void handlePushNotificationNavigation(Map<String, String> notification)
	{
		// Check if it is push start
		// If it is not push start, do not jump
		if (!"true".equalsIgnoreCase(notification.get("pushStart"))) // start from background by push
		{
			return;
		} 
		
		// has push
		// Get the push type
		String messageType = notification.get("messageType");
		if (messageType.equals("mail")) // notification for mail, jump to
										// mail list page
		{
			logger.info("handlePushNotification() : going to Mail Place: " );
			MailOnlinePlace place = new MailOnlinePlace();
			clientFactory.getPlaceController().goTo(place);

		} 
		else if (messageType.equals("application")) // notification for
														// application, jump
														// to application
														// page
		{
			String applicationIDStr = notification.get("applicationID");
			Long applicationID = new Long(applicationIDStr);
			
			logger.info("handlePushNotification() : going to Application Place  applicationID: " + applicationID );
			
			ApplicationDetailsPlace place = new ApplicationDetailsPlace(applicationID);
			clientFactory.getPlaceController().goTo(place);
		}
		else{
			logger.log(Level.SEVERE, "handlePushNotification() : invalid message type : " + messageType);
			throw new RuntimeException("invalid message type : " + messageType);
		}
	}
	
}
