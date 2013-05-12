package com.apptech.first.client.views;


import com.apptech.first.shared.model.ApplicationsModel;
import com.google.gwt.user.client.ui.IsWidget;

public interface ApplicationDetailsView  extends IsWidget{

	public interface Presenter{

		void goBack();
		
	}
	
	void setPresenter(Presenter presenter);

	void populate(ApplicationsModel model);

	void clear();

}
