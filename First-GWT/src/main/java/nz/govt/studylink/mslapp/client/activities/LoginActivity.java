package nz.govt.studylink.mslapp.client.activities;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import nz.govt.studylink.mslapp.client.ClientFactory;
import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.events.LoginSuccessEvent;
import nz.govt.studylink.mslapp.client.events.NotificationArrivedEvent;
import nz.govt.studylink.mslapp.client.events.NotificationArrivedEventHandler;
import nz.govt.studylink.mslapp.client.places.LoginPlace;
import nz.govt.studylink.mslapp.client.service.MSLServiceAsync;
import nz.govt.studylink.mslapp.client.views.LoginView;
import nz.govt.studylink.mslapp.shared.model.UserModel;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class LoginActivity extends AbstractActivity implements LoginView.Presenter {
	private Logger logger = Logger.getLogger("MSLApp.LoginActivity");
	ClientFactory clientFactory;
	private Map<String, String> currentPushNotification;

	public LoginActivity(LoginPlace place, ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	protected MSLServiceAsync service = MSLServiceAsync.helper.getService();
	private List<UserModel> listOfUsers;

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		final LoginView view = clientFactory.getLoginView();

		// set parameters to the view
		
		// set the presenter
		view.setPresenter(this);
		logger.info("start to getListOfUsers");
		// populate the view with the summary details
		service.getListOfUsers(new AsyncCallback<List<UserModel>>() {
			@Override
			public void onSuccess(List<UserModel> list) {
				logger.info("getListOfUsers successfully, usercount = " + list.size());
				listOfUsers = list;
				view.populate(listOfUsers);
			}

			@Override
			public void onFailure(Throwable caught) {
				logger.log(Level.SEVERE, "Error getting list of users : " + caught.getMessage(), caught);
				
			}
		});

		// add it to the dom
		panel.setWidget(view.asWidget());

		// register push event handler
		registerPushNotificationEventHandler();
	}

	@Override
	public void onUserSelected(int index) {
		// grab the Payment
		UserModel model = null;
		try {
			model = listOfUsers.get(index);
		} catch (Exception exp) {
			// error trying to get the application
			// could be LoginSummaryModel is null, or the applications list,
			// or the index is invalid..
		}
		if (model != null) {
			// store the user for future requests
			First.setActiveUser(model);

			handleLoginSuccess();
		}
	}

	public void handleLoginSuccess() {
		// trigger a login success event
		EventBus eventBus = this.clientFactory.getEventBus();
		eventBus.fireEvent(new LoginSuccessEvent());
	}

	public void registerPushNotificationEventHandler() {
		clientFactory.getEventBus().addHandler(NotificationArrivedEvent.TYPE, new NotificationArrivedEventHandler() {
			@Override
			public void onNotificationArrived(NotificationArrivedEvent notificationEvent) {
				currentPushNotification = notificationEvent.getNotification();
			}
		});
	}

}
