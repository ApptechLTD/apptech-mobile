package com.apptech.first.client.views;

import java.util.List;


import com.apptech.first.shared.model.MailOnlineModel;
import com.google.gwt.user.client.ui.IsWidget;

public interface MailOnlineView  extends IsWidget{

	public interface Presenter{

		void onMailSelected(int index);

		/**
		 * Opens the menu.
		 */
		public void openMenu();
	}
	
	void setPresenter(Presenter presenter);
	
	void setVisible(boolean b);

	void populate(List<MailOnlineModel> result);
	
	void startLoading();
	
	void stopLoading();
	
	void clear();
	
}
