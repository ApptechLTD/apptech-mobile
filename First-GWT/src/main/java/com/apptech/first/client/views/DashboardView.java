package com.apptech.first.client.views;


import com.apptech.first.shared.model.DashBoardSummaryModel;
import com.google.gwt.user.client.ui.IsWidget;

public interface DashboardView  extends IsWidget{

	public interface Presenter {

		void onPaymentSelected(int index);

		void onApplicationsSelected(int index);

		/**
		 * Opens the menu.
		 */
		public void openMenu();
	}
	
	void setPresenter(Presenter presenter);
	
	void setVisible(boolean b);

	void populate(DashBoardSummaryModel result);

	void startLoading();
	
	void stopLoading();

	void clear();
	
}
