package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.shared.model.ApplicationsModel;

import com.google.gwt.user.client.ui.IsWidget;

public interface ApplicationDetailsView  extends IsWidget{

	public interface Presenter{

		void goBack();
		
	}
	
	void setPresenter(Presenter presenter);

	void populate(ApplicationsModel model);

	void clear();

}
