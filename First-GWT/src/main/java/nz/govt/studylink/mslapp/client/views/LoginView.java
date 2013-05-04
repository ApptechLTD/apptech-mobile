package nz.govt.studylink.mslapp.client.views;

import java.util.List;

import nz.govt.studylink.mslapp.shared.model.UserModel;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView  extends IsWidget{

	public interface Presenter{

		void onUserSelected(int index);
		
	}
	
	
	void setPresenter(Presenter presenter);

	void populate(List<UserModel> listOfUsers);

}
