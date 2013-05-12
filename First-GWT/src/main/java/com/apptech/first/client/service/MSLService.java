package com.apptech.first.client.service;

import java.util.List;


import com.apptech.first.shared.model.ApplicationsModel;
import com.apptech.first.shared.model.DashBoardSummaryModel;
import com.apptech.first.shared.model.MailOnlineModel;
import com.apptech.first.shared.model.PaymentsModel;
import com.apptech.first.shared.model.PersonalDetailsModel;
import com.apptech.first.shared.model.UserModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface MSLService extends RemoteService{

	DashBoardSummaryModel getDashBoardSummary(String authToken);
	
	List<MailOnlineModel> getMailOnlineList(String authToken);
	
	PaymentsModel getPayment(Long id);
	
	ApplicationsModel getApplications(String authToken, Long id);
	
	MailOnlineModel getMailOnlineDetails(Long id);

	List<UserModel> getListOfUsers();

	PersonalDetailsModel getPersonalDetails(String authToken);
	
	void setupNotifications(String deviceToken, String authToken, String deviceType) ;
}
