package com.apptech.first.client.views;

import java.util.List;

import com.apptech.first.shared.model.JobModel;
import com.google.gwt.user.client.ui.IsWidget;

public interface JobListView  extends IsWidget{

	public interface Presenter{

		void onJobSelected(int index);

		/**
		 * Opens the menu.
		 */
		public void openMenu();
	}
	
	void setPresenter(Presenter presenter);
	
	void setVisible(boolean b);

	void populate(List<JobModel> result);
	
	void startLoading();
	
	void stopLoading();
	
	void clear();
	
}
