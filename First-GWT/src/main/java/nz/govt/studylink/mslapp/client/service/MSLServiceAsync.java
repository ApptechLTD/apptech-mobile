package nz.govt.studylink.mslapp.client.service;

import java.util.List;
import java.util.logging.Logger;

import nz.govt.studylink.mslapp.client.ClientFactory;
import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPlugin;
import nz.govt.studylink.mslapp.shared.model.ApplicationsModel;
import nz.govt.studylink.mslapp.shared.model.DashBoardSummaryModel;
import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;
import nz.govt.studylink.mslapp.shared.model.PaymentsModel;
import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;
import nz.govt.studylink.mslapp.shared.model.UserModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;

public interface MSLServiceAsync {

	class Helper{
		private Logger logger = Logger.getLogger("MSLApp");
		
		
		public MSLServiceAsync getService() {

			MSLServiceAsync service = GWT.create(MSLService.class);
			
			//Retrieve the host parameter from the Native App. (call Phonegap)
			String host = First.getClientFactory().getUserSettingPlugin().getUserSetting().get(UserSettingPlugin.APP_URL);
			
			//re-write URL requests for the server (since the HTML is locally and not served by a server)
			PhonegapUtil.prepareService((ServiceDefTarget)service, host + "/MSLApp/", "/MSLApp/service");
			
			logger.info("MSLServiceAsync.Helper.getService() service host: " + host);
			
			return service;
		}
	}
	Helper helper = new Helper();
	

	void getDashBoardSummary(String authToken, AsyncCallback<DashBoardSummaryModel> callback);
	
	void getMailOnlineDetails(Long id, AsyncCallback<MailOnlineModel> callback);
	void getMailOnlineList(String authToken, AsyncCallback<List<MailOnlineModel>> callback);
	
	void getApplications(String authToken, Long id, AsyncCallback<ApplicationsModel> callback);
	
	void getPayment(Long id, AsyncCallback<PaymentsModel> callback);
	void getListOfUsers(AsyncCallback<List<UserModel>> asyncCallback);
	void getPersonalDetails(String authToken,
			AsyncCallback<PersonalDetailsModel> asyncCallback);
	void setupNotifications(String deviceToken, String authToken,
			String deviceType, AsyncCallback<Void> callback);
	
}