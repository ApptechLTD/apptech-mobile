package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.shared.model.DashBoardSummaryModel;

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
