package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;

import com.google.gwt.user.client.ui.IsWidget;

public interface MailOnlineDetailsView  extends IsWidget{

	public interface Presenter{
		
		void onMailAttachmentsSelected(int index);
		
		void onMainMailSelected();

		void goBack();
		
	}
	
	void setPresenter(Presenter presenter);

	void populate(MailOnlineModel model);

}
