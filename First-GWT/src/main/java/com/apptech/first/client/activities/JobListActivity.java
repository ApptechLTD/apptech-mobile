package com.apptech.first.client.activities;

import java.util.List;

import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.First;
import com.apptech.first.client.cordova.plugins.UserSettingPlugin;
import com.apptech.first.client.places.AppMenuPlace;
import com.apptech.first.client.places.JobListPlace;
import com.apptech.first.client.places.MailOnlineDetailsPlace;
import com.apptech.first.client.views.JobListView;
import com.apptech.first.client.views.MailOnlineView;
import com.apptech.first.shared.model.JobModel;
import com.apptech.first.shared.model.MailOnlineModel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class JobListActivity extends BaseActivity implements
		JobListView.Presenter {
	
	public JobListActivity(JobListPlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	private List<JobModel> jobList;

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		
		final JobListView view = clientFactory.getJobListView();

		// set parameters to the view

		// set the presenter
		view.setPresenter(this);
		
		view.startLoading();
		
		view.clear();

		//TODO: handle when Active User is not set
		// populate the view with the mail online list
		service.searchJobList(null, new AsyncCallback<List<JobModel>>() {
			@Override
			public void onSuccess(List<JobModel> list) {
				jobList = list;
				view.populate(jobList);
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
	}

	@Override
	public void onJobSelected(int index) {
		// grab the Application
		JobModel model = null;
		try {
			model = jobList.get(index);
		} catch (Exception exp) {
			// error trying to get the application
			// could be dashBoardSummaryModel is null, or the applications list,
			// or the index is invalid..
		}
		if (model != null) {
			// goto mail details
			//if (model.getAttachments().size() > 0) {
			//	MailOnlineDetailsPlace place = new MailOnlineDetailsPlace(model);
			//	goTo(place);
			//}
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
