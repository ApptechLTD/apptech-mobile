
package com.apptech.first.client.activities;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.First;
import com.apptech.first.client.events.NotificationArrivedEvent;
import com.apptech.first.client.events.NotificationArrivedEventHandler;
import com.apptech.first.client.places.AppMenuPlace;
import com.apptech.first.client.places.ApplicationDetailsPlace;
import com.apptech.first.client.places.DashboardPlace;
import com.apptech.first.client.places.MailOnlinePlace;
import com.apptech.first.client.places.PaymentDetailsPlace;
import com.apptech.first.client.views.DashboardView;
import com.apptech.first.shared.model.ApplicationsModel;
import com.apptech.first.shared.model.DashBoardSummaryModel;
import com.apptech.first.shared.model.PaymentsModel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DashboardActivity extends BaseActivity implements DashboardView.Presenter {

	
	public DashboardActivity(DashboardPlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	private Logger logger = Logger.getLogger("DashboardActivity");
	private DashBoardSummaryModel dashBoardSummaryModel;

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		
		final DashboardView view = clientFactory.getDashboardView();

		// set the presenter
		view.setPresenter(this);
		
		//check if the dashboard model already exists locally for this user
		
		view.startLoading();
		
		view.clear();
		
		// populate the view with the summary details
		service.getDashBoardSummary(First.getActiveUser().getAuthToken(), new AsyncCallback<DashBoardSummaryModel>() {
			@Override
			public void onSuccess(DashBoardSummaryModel model) {
				dashBoardSummaryModel = model;
				view.populate(dashBoardSummaryModel);
				
				// Check if there is any push notification and handle the page redirection
				//handlePushNotification();
			}

			@Override
			public void onFailure(Throwable caught) {
				// shiiii something wrong happened
				// TODO: display error to user
				caught.toString();
				
				view.stopLoading();
			}
		});

		// add it to the dom
		panel.setWidget(view.asWidget());
		
		// Register push handler
		configPushEventHandler(eventBus);
	}
	
	private void configPushEventHandler(EventBus eventBus) {
		// Handle the push arrive event
		eventBus.addHandler(NotificationArrivedEvent.TYPE, new NotificationArrivedEventHandler() {
			@Override
			public void onNotificationArrived(NotificationArrivedEvent notificationEvent) {

				Map<String, String> notification = notificationEvent.getNotification(); 
				// check if it is login
				// Check if it is push start
				// If it is not push start, do not jump
				if (!"true".equalsIgnoreCase(notification.get("pushStart"))) // start from background by push
				{
					return;
				} 
				
				// has push
				// Get the push type
				String messageType = notification.get("messageType");
				if (messageType.equals("application")) // notification for
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
				
			}
		});
	}

	private void AddNotificationBadge(Map<String, String> notification)
	{
		
	}
	
	@Override
	public void onApplicationsSelected(int index) {
		// grab the Application
		ApplicationsModel model = null;
		try {
			model = dashBoardSummaryModel.getApplications().get(index);
		} catch (Exception exp) {
			// error trying to get the application
			// could be dashBoardSummaryModel is null, or the applications list,
			// or the index is invalid..
		}
		if (model != null) {
			// goto applications details
			ApplicationDetailsPlace place = new ApplicationDetailsPlace(model);
			goTo(place);
		}
	}

	@Override
	public void onPaymentSelected(int index) {
		// grab the Payment
		PaymentsModel model = null;
		try {
			model = dashBoardSummaryModel.getPayments().get(index);
		} catch (Exception exp) {
			// error trying to get the application
			// could be dashBoardSummaryModel is null, or the applications list,
			// or the index is invalid..
		}
		if (model != null) {
			// goto payments details
			PaymentDetailsPlace place = new PaymentDetailsPlace(model);
			goTo(place);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void openMenu() {

		goTo(new AppMenuPlace());
	}
}
