package com.apptech.first.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import com.apptech.first.client.First;
import com.apptech.first.client.events.NotificationReadEvent;
import com.apptech.first.client.ui.ApplicationsSummaryCell;
import com.apptech.first.client.ui.Loading;
import com.apptech.first.client.ui.PaymentsSummaryCell;
import com.apptech.first.shared.model.ApplicationsModel;
import com.apptech.first.shared.model.DashBoardSummaryModel;
import com.apptech.first.shared.model.PaymentsModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;

public class DashboardViewImpl extends Composite implements DashboardView {

	private static DashboardViewImplUiBinder uiBinder = GWT
			.create(DashboardViewImplUiBinder.class);

	public interface Css extends CssResource {
		
		String icon();

		String type();

		String amount();

		String date();

		String provider();

		String program();

		String pending();

		String aproved();

		String declined();
	}

	interface DashboardViewImplUiBinder extends
			UiBinder<Widget, DashboardViewImpl> {
	}

	private DashboardView.Presenter presenter;

	@UiField
	Css style;

	@UiField(provided = true)
	CellList<PaymentsModel> paymentsList;

	@UiField(provided = true)
	CellList<ApplicationsModel> applicationsList;

	@UiField
	ScrollPanel scrollPanel;

	@UiField
	HTMLPanel noPaymentsMessage, noApplicationsMessage;

	@UiField
	Loading loadingPayments, loadingApplications;
	
	PaymentsSummaryCell paymentsCell = new PaymentsSummaryCell();

	ApplicationsSummaryCell applicationsCell = new ApplicationsSummaryCell();

	public DashboardViewImpl() {
		paymentsList = new CellList<PaymentsModel>(paymentsCell);

		applicationsList = new CellList<ApplicationsModel>(applicationsCell);

		initWidget(uiBinder.createAndBindUi(this));

		paymentsCell.setStyle(style);
		applicationsCell.setStyle(style);
	}

	/**
	 * Open the menu.
	 * 
	 * @param event
	 */
	@UiHandler("menuButton")
	public void onMenuTap(TapEvent event) {

		presenter.openMenu();
	}

	@UiHandler("paymentsList")
	void onPaymentSelected(CellSelectedEvent e) {
		if (presenter != null) {
			paymentsList.setSelectedIndex(e.getIndex(), true);
			presenter.onPaymentSelected(e.getIndex());
		}
	}

	@UiHandler("applicationsList")
	void onApplicationsSelected(CellSelectedEvent e) {
		if (presenter != null) {
			applicationsList.setSelectedIndex(e.getIndex(), true);
			presenter.onApplicationsSelected(e.getIndex());
		}
	}

	@Override
	public void startLoading() {
		loadingPayments.start();
		loadingApplications.start();
	}
	
	@Override
	public void stopLoading() {
		loadingPayments.stop();
		loadingApplications.stop();
	}
	
	@Override
	public void clear() {
		paymentsList.render(new ArrayList<PaymentsModel>());
		applicationsList.render(new ArrayList<ApplicationsModel>());
		scrollPanel.refresh();
		noPaymentsMessage.getElement().getStyle().setDisplay(Display.NONE);
		noApplicationsMessage.getElement().getStyle().setDisplay(Display.NONE);
	}
	
	@Override
	public void populate(DashBoardSummaryModel model) {
		
		stopLoading();
		
		paymentsList.render(model.getPayments());
		if (model.getPayments() != null && model.getPayments().size() > 0) {
			noPaymentsMessage.getElement().getStyle().setDisplay(Display.NONE);
		} 
		else {
			noPaymentsMessage.getElement().getStyle().setDisplay(Display.BLOCK);
		}

		applicationsList.render(model.getApplications());

		if (model.getApplications() != null && model.getApplications().size() > 0) {
			noApplicationsMessage.getElement().getStyle().setDisplay(Display.NONE);
		} 
		else {
			noApplicationsMessage.getElement().getStyle().setDisplay(Display.BLOCK);
		}

	
		scrollPanel.refresh();
		
		// Send out the push notification read event
		handleNotification();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	// Check if there is any unread application, it there is, post read notification event
	private void handleNotification() {
		// post a notification read event
		// if there is no unread count, post a 0 read data to trigger the menu button refresh
		// Clear the stored unread message number
		First.getClientFactory().getStorage().setItem("unreadapplication", "0");
		
		EventBus eventBus = First.getClientFactory().getEventBus();
		Map<String, String> notification = new HashMap<String, String>();
		notification.put("messageType", "application");  // set type of unread message
		// Send out the push notification read event
		eventBus.fireEvent(new NotificationReadEvent(notification));
	}
		
}
