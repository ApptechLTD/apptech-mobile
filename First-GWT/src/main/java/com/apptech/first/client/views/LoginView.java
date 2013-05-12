package com.apptech.first.client.views;

import java.util.List;


import com.apptech.first.shared.model.UserModel;
import com.google.gwt.user.client.ui.IsWidget;

public interface LoginView  extends IsWidget{

	public interface Presenter{

		void onUserSelected(int index);
		
	}
	
	
	void setPresenter(Presenter presenter);

	void populate(List<UserModel> listOfUsers);

}
