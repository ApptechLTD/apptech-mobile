package com.apptech.first.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.apptech.first.client.First;
import com.apptech.first.client.events.NotificationReadEvent;
import com.apptech.first.client.ui.GroupCellList;
import com.apptech.first.client.ui.JobSummaryCell;
import com.apptech.first.client.ui.Loading;
import com.apptech.first.shared.model.JobModel;
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
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;

public class JobListViewImpl extends Composite implements JobListView {

	private static JobListViewImplUiBinder uiBinder = GWT
			.create(JobListViewImplUiBinder.class);
	
	@UiField
	Loading searchingJobs;

	public interface Css extends CssResource {
		
		String displayNone();
		String jobTitle();
		String jobDescription();
		String jobAddress();
		String jobReleaseDate();

	}

	interface JobListViewImplUiBinder extends
			UiBinder<Widget, JobListViewImpl> {
	}

	private JobListView.Presenter presenter;

	@UiField
	Css style;

	@UiField(provided = true)
	GroupCellList<JobModel> jobList;

	@UiField
	ScrollPanel scrollPanel;

	@UiField
	HTMLPanel noJobMessage;

	JobSummaryCell jobSummaryCell = new JobSummaryCell();
	
	public JobListViewImpl() {
		
		jobList = new GroupCellList<JobModel>(jobSummaryCell);

		initWidget(uiBinder.createAndBindUi(this));

		jobSummaryCell.setStyle(style);
	}
	
	@Override
	public void clear() {
		jobList.render(new ArrayList<JobModel>());
		scrollPanel.refresh();
		noJobMessage.getElement().getStyle().setDisplay(Display.NONE);
	}

	public void populate(List<JobModel> modelList) {
		
		stopLoading();
		
		jobList.render(modelList);

		if (modelList != null && modelList.size() > 0) {
			noJobMessage.getElement().getStyle().setDisplay(Display.NONE);
		} else {
			noJobMessage.getElement().getStyle().setDisplay(Display.BLOCK);
		}

		scrollPanel.refresh();
		
		// Check if there is any unread mail, it there is, post rean notification event
		handleNotification();
	}

	// Check if there is any unread mail, it there is, post rean notification event
	private void handleNotification() {
		// post a notification read event
		// if there is no unread count, also post a read event to trigger the menu button refresh
		// Clear the stored unread message number before trigger event
		First.getClientFactory().getStorage().setItem("unreadmail", "0");

		EventBus eventBus = First.getClientFactory().getEventBus();
		Map<String, String> notification = new HashMap<String, String>();
		notification.put("messageType", "mail"); // set the type of notification
		// Send out the push notification read event
		eventBus.fireEvent(new NotificationReadEvent(notification));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("jobList")
	void onMailSelected(CellSelectedEvent e) {
		if (presenter != null) {
			jobList.setSelectedIndex(e.getIndex(), true);
			presenter.onJobSelected(e.getIndex());
			jobList.setSelectedIndex(e.getIndex(), false);
		}
	}
	
	@Override
	public void startLoading() {
		searchingJobs.start();
	}
	
	@Override
	public void stopLoading() {
		searchingJobs.stop();
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
}
