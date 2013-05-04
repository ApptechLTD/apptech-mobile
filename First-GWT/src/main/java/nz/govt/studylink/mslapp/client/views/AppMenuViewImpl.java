
package nz.govt.studylink.mslapp.client.views;

import java.util.Map;
import java.util.logging.Logger;

import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.events.NotificationArrivedEvent;
import nz.govt.studylink.mslapp.client.events.NotificationArrivedEventHandler;
import nz.govt.studylink.mslapp.client.events.NotificationReadEvent;
import nz.govt.studylink.mslapp.client.events.NotificationReadEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;

/**
 * UI Binder class for the application menu view.
 */
public class AppMenuViewImpl extends Composite implements AppMenuView {

	private static final Logger logger = Logger.getLogger("AppMenuViewImpl");

	private static AppMenuViewImplUiBinder uiBinder = GWT.create(AppMenuViewImplUiBinder.class);

	interface AppMenuViewImplUiBinder extends UiBinder<Widget, AppMenuViewImpl> {
	}

	@UiField Label dashboardBadge;
	@UiField Label personalDetailsBadge;
	@UiField Label mailOnlineBadge;

	private AppMenuView.Presenter presenter;

	/**
	 * Constructor, ties this object to the UI of the same name.
	 */
	public AppMenuViewImpl() {

		initWidget(uiBinder.createAndBindUi(this));

		// Listen for events
		EventBus eventBus = First.getClientFactory().getEventBus();

		// Register the push notification arriving event handler
		eventBus.addHandler(NotificationArrivedEvent.TYPE, new NotificationArrivedEventHandler() {
			@Override
			public void onNotificationArrived(NotificationArrivedEvent notificationEvent) {
				Map<String, String> notification = notificationEvent.getNotification();
				logger.info("onNotificationArrived() : notification : " +  notification);
				// update badge count display
				updateBadge();
			}
		});

		// Register the notification read event handler
		eventBus.addHandler(NotificationReadEvent.TYPE, new NotificationReadEventHandler() {
			@Override
			public void onNotificationRead(NotificationReadEvent notificationReadEvent) {
				Map<String, String> notification = notificationReadEvent.getNotification();
				logger.info("onNotificationArrived() : notification : " +  notification);

				// update badge count
				updateBadge();
			}
		});
	}

	@SuppressWarnings("unused")
	@UiHandler("dashboardButton")
	public void onDashBoardTap(TapEvent tap) {
		presenter.gotoDashBoard();
	}

	@SuppressWarnings("unused")
	@UiHandler("logoutButton")
	public void onLogoutTap(TapEvent tap) {
		presenter.logout();
	}

	@SuppressWarnings("unused")
	@UiHandler("mailOnlineButton")
	public void onMailOnlineButton(TapEvent tap) {
		presenter.gotoMailOnline();
	}

	@SuppressWarnings("unused")
	@UiHandler("menuButton")
	public void onMenuTap(TapEvent event) {
		presenter.closeMenu();
	}

	@SuppressWarnings("unused")
	@UiHandler("personalDetailsButton")
	public void onPersonalDetailsButton(TapEvent tap) {
		presenter.gotoPersonalDetails();
	}

	@Override
	public void populate() {
		updateBadge();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	// Load the unread message number and update the badge displaying
	private void updateBadge() {

		int dashboardBadgeCount = 0;
		int personalDetailsBadgeCount = 0; 
		int mailOnlineBadgeCount = 0;

		// load stored data
		String mailCountStr = First.getClientFactory().getStorage().getItem("unreadmail");
		String applicationCountStr = First.getClientFactory().getStorage().getItem("unreadapplication");

		if (mailCountStr != null) {
			mailOnlineBadgeCount = Integer.valueOf(mailCountStr);
		}

		if (applicationCountStr != null) {
			dashboardBadgeCount = Integer.valueOf(applicationCountStr);
		}

		// update dashboard button badge
		if (dashboardBadgeCount > 0) {
			dashboardBadge.setVisible(true);
			dashboardBadge.setText(String.valueOf(dashboardBadgeCount));
		}
		else {
			dashboardBadge.setVisible(false);
		}

		// update personal detail button badge
		if (personalDetailsBadgeCount > 0) {
			personalDetailsBadge.setVisible(true);
			personalDetailsBadge.setText(String.valueOf(personalDetailsBadgeCount));
		}
		else {
			personalDetailsBadge.setVisible(false);
		}

		// update mail online badge
		if (mailOnlineBadgeCount > 0) {
			mailOnlineBadge.setVisible(true);
			mailOnlineBadge.setText(String.valueOf(mailOnlineBadgeCount));
		}
		else {
			mailOnlineBadge.setVisible(false);
		}
	}
}
