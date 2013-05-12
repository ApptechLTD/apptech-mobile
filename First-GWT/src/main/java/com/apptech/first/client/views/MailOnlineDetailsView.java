package com.apptech.first.client.views;


import com.apptech.first.shared.model.MailOnlineModel;
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
