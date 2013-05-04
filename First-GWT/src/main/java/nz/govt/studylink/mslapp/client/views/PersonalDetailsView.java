package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.user.client.ui.IsWidget;

public interface PersonalDetailsView extends IsWidget {

	public interface Presenter {

		void goBack();

		void editPressed();
		
		void openMenu();

	}

	void setPresenter(Presenter presenter);

	void populate(PersonalDetailsModel paymentsModel);

	void clear();

}
