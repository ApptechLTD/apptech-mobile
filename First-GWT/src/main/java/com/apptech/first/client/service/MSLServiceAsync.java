package com.apptech.first.client.service;

import java.util.List;
import java.util.logging.Logger;

import com.apptech.first.client.First;
import com.apptech.first.client.cordova.plugins.UserSettingPlugin;
import com.apptech.first.shared.model.ApplicationsModel;
import com.apptech.first.shared.model.DashBoardSummaryModel;
import com.apptech.first.shared.model.JobModel;
import com.apptech.first.shared.model.JobSearchConditionModel;
import com.apptech.first.shared.model.MailOnlineModel;
import com.apptech.first.shared.model.PaymentsModel;
import com.apptech.first.shared.model.PersonalDetailsModel;
import com.apptech.first.shared.model.UserModel;
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
	
	// Service for searching job basing on the search condition
	void searchJobList(JobSearchConditionModel condition, AsyncCallback<List<JobModel>> callback);
	
	
	void getApplications(String authToken, Long id, AsyncCallback<ApplicationsModel> callback);
	
	void getPayment(Long id, AsyncCallback<PaymentsModel> callback);
	void getListOfUsers(AsyncCallback<List<UserModel>> asyncCallback);
	void getPersonalDetails(String authToken,
			AsyncCallback<PersonalDetailsModel> asyncCallback);
	void setupNotifications(String deviceToken, String authToken,
			String deviceType, AsyncCallback<Void> callback);
	
}