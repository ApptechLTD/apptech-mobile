
package nz.govt.studylink.mslapp.client.views;

import com.google.gwt.user.client.ui.IsWidget;

public interface AppMenuView  extends IsWidget{

	public interface Presenter {

		/**
		 * Closes the menu, returning to the previous screen.
		 */
		public void closeMenu();

		void gotoDashBoard();

		void gotoMailOnline();

		void gotoPersonalDetails();
		
		void logout();
	}

	void setPresenter(Presenter presenter);
	
	void populate();
}
