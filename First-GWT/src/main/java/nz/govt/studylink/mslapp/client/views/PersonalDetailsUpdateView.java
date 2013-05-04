package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.user.client.ui.IsWidget;

public interface PersonalDetailsUpdateView extends IsWidget {

	public interface Presenter {

		void donePressed(PersonalDetailsModel personalDetailsModel);
	
		void cancelPressed();

		PersonalDetailsModel getModel();
	}

	void setPresenter(Presenter presenter);

	void populate(PersonalDetailsModel paymentsModel);

	void clear();

}
