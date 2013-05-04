package nz.govt.studylink.mslapp.client.activities;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import nz.govt.studylink.mslapp.client.ClientFactory;
import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsPlace;
import nz.govt.studylink.mslapp.client.places.PersonalDetailsUpdatePlace;
import nz.govt.studylink.mslapp.client.views.PersonalDetailsUpdateView;
import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

public class PersonalDetailsUpdateActivity extends BaseActivity implements
		PersonalDetailsUpdateView.Presenter {

	public PersonalDetailsUpdateActivity(PersonalDetailsUpdatePlace place,
			ClientFactory clientFactory) {
		super(clientFactory);
		this.place = place;
	}

	private PersonalDetailsUpdatePlace place;

	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		final PersonalDetailsUpdateView view = clientFactory
				.getPersonalDetailsUpdateView();

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
							place.setModel(model);
							view.populate(model);
						}

						@Override
						public void onFailure(Throwable caught) {
							// TODO implement error handling
						}
					});
		} else {
			// load with the local data
			view.populate(place.getPersonalDetailsModel());
		}

		// add it to the dom
		panel.setWidget(view.asWidget());

	}

	@Override
	public void cancelPressed() {
		// TODO Auto-generated method stub
		goTo(new PersonalDetailsPlace(getModel()));
	}

	@Override
	public void donePressed(PersonalDetailsModel personalDetailsModel) {
		// TODO Auto-generated method stub
		goTo(new PersonalDetailsPlace(personalDetailsModel));
	}

	@Override
	public PersonalDetailsModel getModel() {
		return place.getPersonalDetailsModel();
	};

}
