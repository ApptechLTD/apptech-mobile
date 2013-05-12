package com.apptech.first.client.views;


import com.apptech.first.shared.model.PersonalDetailsModel;
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
