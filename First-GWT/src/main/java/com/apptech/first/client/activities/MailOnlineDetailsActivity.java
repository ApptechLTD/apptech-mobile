package com.apptech.first.client.activities;


import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.First;
import com.apptech.first.client.cordova.plugins.UserSettingPlugin;
import com.apptech.first.client.places.MailOnlineDetailsPlace;
import com.apptech.first.client.places.MailOnlinePlace;
import com.apptech.first.client.views.MailOnlineDetailsView;
import com.apptech.first.shared.model.MailOnlineAttachmentsModel;
import com.apptech.first.shared.model.MailOnlineModel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MailOnlineDetailsActivity extends BaseActivity implements MailOnlineDetailsView.Presenter {

	private MailOnlineDetailsPlace place;
	
	private MailOnlineModel mailOnlineModel;

	public MailOnlineDetailsActivity(MailOnlineDetailsPlace place, ClientFactory clientFactory){
		super(clientFactory);
		this.place = place;
	}
	
	
	/**
     * Invoked by the ActivityManager to start a new Activity
     */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		final MailOnlineDetailsView view = clientFactory.getMailOnlineDetailsView();
		
		//set parameters to the view
		//set the presenter
		view.setPresenter(this);
		
		if(place.getMailOnlineModel() == null){
			//TODO: display loading
			
			service.getMailOnlineDetails(place.getId(), new AsyncCallback<MailOnlineModel>() {
				@Override
				public void onSuccess(MailOnlineModel model) {
					mailOnlineModel = model;
					view.populate(mailOnlineModel);
				}
				@Override
				public void onFailure(Throwable caught) {
					// TODO error handling
					
				}
			});
			
		}
		else{
			//load with the local data
			mailOnlineModel = place.getMailOnlineModel(); 
			view.populate(mailOnlineModel);
		}
		
		//add it to the dom
		panel.setWidget(view.asWidget());
	}	
	
	@Override
	public void goBack() {
		goTo(new MailOnlinePlace());
	}

	@Override
	public void onMainMailSelected() {
		// open the main PDF file
		String serverUrl = clientFactory.getUserSettingPlugin().getUserSetting().get(UserSettingPlugin.APP_URL);
		StringBuilder sb = new StringBuilder(serverUrl + "/MSLApp/PDF/Mail/");
		sb.append(First.getActiveUser().getAuthToken()).append("/");
		sb.append(mailOnlineModel.getMailOnlinePDFPath());
		clientFactory.getPDFPlugin().showDocument(sb.toString());
	}
	
	@Override
	public void onMailAttachmentsSelected(int index) {
		// grab the Application
		MailOnlineAttachmentsModel attachment = null;
		try {
			attachment = mailOnlineModel.getAttachments().get(index);
		} catch (Exception exp) {
			// error trying to get the application
			// could be dashBoardSummaryModel is null, or the applications list,
			// or the index is invalid..
		}
		if (attachment != null) {
			// open the PDF file
			String serverUrl = clientFactory.getUserSettingPlugin().getUserSetting().get(UserSettingPlugin.APP_URL);
			StringBuilder sb = new StringBuilder(serverUrl + "/MSLApp/PDF/Mail/");
			sb.append(First.getActiveUser().getAuthToken()).append("/");
			sb.append(attachment.getAttachmentPDFPath());
			clientFactory.getPDFPlugin().showDocument(sb.toString());
		}
	}
	
}
