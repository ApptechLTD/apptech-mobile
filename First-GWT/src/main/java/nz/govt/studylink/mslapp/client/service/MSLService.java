package nz.govt.studylink.mslapp.client.service;

import java.util.List;

import nz.govt.studylink.mslapp.shared.model.ApplicationsModel;
import nz.govt.studylink.mslapp.shared.model.DashBoardSummaryModel;
import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;
import nz.govt.studylink.mslapp.shared.model.PaymentsModel;
import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;
import nz.govt.studylink.mslapp.shared.model.UserModel;

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
