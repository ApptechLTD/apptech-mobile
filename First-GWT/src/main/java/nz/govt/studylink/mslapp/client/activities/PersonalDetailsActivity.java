package nz.govt.studylink.mslapp.client.activities;

import nz.govt.studylink.mslapp.client.ClientFactory;
import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.places.AppMenuPlace;
import nz.govt.studylink.mslapp.client.places.DashboardPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsUpdatePlace;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsView;
import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PersonalDetailsActivity extends BaseActivity implements
		PersonalDetailsView.Presenter {

	public PersonalDetailsActivity(PersonalDetailsPlace place,
			ClientFactory clientFactory) {
		super(clientFactory);
		this.place = place;
	}

	private PersonalDetailsPlace place;

	// PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		final PersonalDetailsView view = clientFactory.getPersonalDetailsView();

		// set parameters to the view
		// set the presenter
		view.setPresenter(this);
		view.clear();

		if (place.getPersonalDetailsModel() == null) {
			// TODO: display loading .. since the data will come from the server

			service.getPersonalDetails(First.getActiveUser().getAuthToken(),
					new AsyncCallback<PersonalDetailsModel>() {
						@Override
						public void onSuccess(PersonalDetailsModel model) {
							// populate screen
							getPlace().setPersonalDetailsModel(model);
							view.populate(model);
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO implement error handling
							System.out.println("failure");
						}
					});
		} else {
			// load with the local data
			view.populate(place.getPersonalDetailsModel());
		}

		// add it to the dom
		panel.setWidget(view.asWidget());
	}

	public PersonalDetailsPlace getPlace() {
		return place;
	}

	@Override
	public void goBack() {
		goTo(new DashboardPlace());
	}

	@Override
	public void editPressed() {
		goTo(new PersonalDetailsUpdatePlace(getPlace()
				.getPersonalDetailsModel()));

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void openMenu() {

		goTo(new AppMenuPlace());
	}
}
