package nz.govt.studylink.mslapp.client.activities;

import java.util.List;

import nz.govt.studylink.mslapp.client.ClientFactory;
import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPlugin;
import nz.govt.studylink.mslapp.client.places.AppMenuPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlineDetailsPlace;
import nz.govt.studylink.mslapp.client.places.MailOnlinePlace;
import nz.govt.studylink.mslapp.client.views.MailOnlineView;
import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MailOnlineActivity extends BaseActivity implements
		MailOnlineView.Presenter {
	
	public MailOnlineActivity(MailOnlinePlace place, ClientFactory clientFactory) {
		super(clientFactory);
	}

	private List<MailOnlineModel> mailOnlineModelList;

	/**
	 * Invoked by the ActivityManager to start a new Activity
	 */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		
		final MailOnlineView view = clientFactory.getMailOnlineView();

		// set parameters to the view

		// set the presenter
		view.setPresenter(this);
		
		view.startLoading();
		
		view.clear();

		//TODO: handle when Active User is not set
		// populate the view with the mail online list
		service.getMailOnlineList(First.getActiveUser().getAuthToken(), new AsyncCallback<List<MailOnlineModel>>() {
			@Override
			public void onSuccess(List<MailOnlineModel> list) {
				mailOnlineModelList = list;
				view.populate(mailOnlineModelList);
			}

			@Override
			public void onFailure(Throwable caught) {
				// shiiii something wrong happened
				// TODO: display error to user
				caught.toString();
				view.stopLoading();
			}
		});
		// add it to the dom
		panel.setWidget(view.asWidget());
	}

	@Override
	public void onMailSelected(int index) {
		// grab the Application
		MailOnlineModel model = null;
		try {
			model = mailOnlineModelList.get(index);
		} catch (Exception exp) {
			// error trying to get the application
			// could be dashBoardSummaryModel is null, or the applications list,
			// or the index is invalid..
		}
		if (model != null) {
			// goto mail details
			if (model.getAttachments().size() > 0) {
			MailOnlineDetailsPlace place = new MailOnlineDetailsPlace(model);
			goTo(place);
		}
			else {
				
				//TODO: Call PDF directly
				String serverUrl = clientFactory.getUserSettingPlugin().getUserSetting().get(UserSettingPlugin.APP_URL);
				StringBuilder sb = new StringBuilder(serverUrl + "/MSLApp/PDF/Mail/");
				sb.append(First.getActiveUser().getAuthToken()).append("/");
				sb.append(model.getMailOnlinePDFPath());
				clientFactory.getPDFPlugin().showDocument(sb.toString());
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void openMenu() {

		goTo(new AppMenuPlace());
	}
}
