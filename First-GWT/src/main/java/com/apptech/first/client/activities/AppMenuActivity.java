package com.apptech.first.client.activities;


import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.First;
import com.apptech.first.client.places.AppMenuPlace;
import com.apptech.first.client.places.DashboardPlace;
import com.apptech.first.client.places.LoginPlace;
import com.apptech.first.client.places.MailOnlinePlace;
import com.apptech.first.client.places.PersonalDetailsPlace;
import com.apptech.first.client.views.AppMenuView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class AppMenuActivity extends BaseActivity implements AppMenuView.Presenter {

	public AppMenuActivity(AppMenuPlace place, ClientFactory clientFactory){
		super(clientFactory);
	}
	
	/**
     * Invoked by the ActivityManager to start a new Activity
     */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		AppMenuView view = clientFactory.getAppMenuView();
		
		//set parameters to the view
		
		//set the presenter
		view.setPresenter(this);
		
		//add it to the dom
		panel.setWidget(view.asWidget());
		
		view.populate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeMenu() {

		// TODO: How do you go back to the previous place? - Em
		goTo(new DashboardPlace());
	}

	@Override
	public void logout() {
		First.setActiveUser(null);
		goTo(new LoginPlace());
	}
	
	@Override
	public void gotoDashBoard() {
		goTo(new DashboardPlace());
	}

	@Override
	public void gotoPersonalDetails() {
		goTo(new PersonalDetailsPlace());
		
	}

	@Override
	public void gotoMailOnline() {
		goTo(new MailOnlinePlace());
		
	}

}
