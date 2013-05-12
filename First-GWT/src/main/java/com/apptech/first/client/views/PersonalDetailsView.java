package com.apptech.first.client.views;


import com.apptech.first.shared.model.PersonalDetailsModel;
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
