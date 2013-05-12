package com.apptech.first.server.service;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import mobility.test.excel.ExcelParser;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apptech.first.client.service.MSLService;
import com.apptech.first.server.notifications.NotificationsException;
import com.apptech.first.server.notifications.NotificationsService;
import com.apptech.first.shared.model.AddressModel;
import com.apptech.first.shared.model.ApplicationsModel;
import com.apptech.first.shared.model.BankAccountModel;
import com.apptech.first.shared.model.DashBoardSummaryModel;
import com.apptech.first.shared.model.MailList;
import com.apptech.first.shared.model.MailOnlineAttachmentsModel;
import com.apptech.first.shared.model.MailOnlineModel;
import com.apptech.first.shared.model.PaymentsModel;
import com.apptech.first.shared.model.PersonalDetailsModel;
import com.apptech.first.shared.model.UserList;
import com.apptech.first.shared.model.UserModel;

@WebService(targetNamespace = "http://com.apptech.first.server.service", name = "MSLServiceWS")
@SOAPBinding(style = Style.RPC)
@Service
public class MSLServiceImpl implements MSLService {

	Logger logger = Logger.getLogger(MSLServiceImpl.class.getName());

	@Autowired
	protected NotificationsService notificationsService;

	protected SimpleDateFormat dtFmt = new SimpleDateFormat("dd/MM/yyyy");

	// mock data
	static List<UserModel> listOfUsers = mockDataUserData();

	@Override
	public void setupNotifications(String deviceToken, String authToken,
			String deviceType) {
		try {
			notificationsService.setupNotifications(deviceToken, authToken,
					deviceType);
		} catch (NotificationsException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@WebMethod(operationName = "getDashBoardSummaryWS")
	public DashBoardSummaryModel getDashBoardSummary(@WebParam(name = "authToken") String authToken) {
		DashBoardSummaryModel model = new DashBoardSummaryModel();

//		// Artificial wait here for loading feature
//		long timeToWait = (1000 * 2);// 1 seconds
//
//		try {
//			Thread.sleep(timeToWait);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// mock some data while we don't have Camel :-)
		mockData(authToken, model);

		return model;
	}

	@WebMethod(operationName = "getMailOnlineListWS")
	public MailList getMailOnlineListWS(@WebParam(name = "authToken")String authToken) {
		return new MailList(getMailOnlineList(authToken));
	}
	
	
	@Override
	public List<MailOnlineModel> getMailOnlineList( String authToken) {

		List<MailOnlineModel> mailOnlineModelList = new ArrayList<MailOnlineModel>();

//		// Artificial wait here for loading feature
//		long timeToWait = (1000 * 2);// 1 seconds
//
//		try {
//			Thread.sleep(timeToWait);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// *** Invoke Camel here *** //

		// mock some data while we don't have Camel :-)
		mailOnlineModelList = getMockMailOnline(authToken);
		
		if (mailOnlineModelList == null) {
			mailOnlineModelList = new ArrayList<MailOnlineModel>();
		}

		return mailOnlineModelList;
	}
	
	@WebMethod(operationName = "getListOfUsersWS")
	public UserList getListOfUsersWS() {
		return new UserList(getListOfUsers());
	}

	@Override
	public List<UserModel> getListOfUsers() {

		// APNS Test call
		// apnsSender.push("student ID",
		// "19308314834701ACD8313AEBD92AEFDE192120371FE13982392831701318B943",
		// "message test");

		return listOfUsers;
	}

	private static List<UserModel> mockDataUserData() {
		String fileName = getFilepath("Users.xls");
		try {
			FileInputStream fis = new FileInputStream(fileName);

			final HSSFWorkbook wb = new HSSFWorkbook(fis);

			final ExcelParser<UserModel> parser = new ExcelParser<UserModel>(1);

			final Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(new Integer(1), "id");
			map.put(new Integer(4), "name");
			map.put(new Integer(5), "authToken");
			final List<UserModel> list = parser.getObjects(wb.getSheetAt(0),
					UserModel.class, map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getFilepath(String name) {
		String userHome = System.getProperty("user.home");
		String path = userHome + "/MSL-Test-Data/" + name;

		System.out.println("Excel file path: " + path);

		return path;
	}

	@Override
	public PaymentsModel getPayment(Long id) {
		try {
			return new PaymentsModel(1l, "Student Allowance Payment", 170.00,
					dtFmt.parse("1/02/2013"), "00-0000-000000-00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ApplicationsModel getApplications(String authToken, Long id) {
		List<ApplicationsModel> list = getMockApplications(authToken);
		for (ApplicationsModel application : list) {
			if (application.getId().equals(id)) {
				logger.info("Application found for id: " + id);
				return application;
			}
		}

		logger.info("Application NOT found for id: " + id + " authToken: "
				+ authToken);

		return null;
	}

	@Override
	public MailOnlineModel getMailOnlineDetails(Long id) {
		return null;
	}

	private void mockData(String authToken, DashBoardSummaryModel model) {
		// add mock data

		List<PaymentsModel> payments = getMockPayments(authToken);
		if (payments == null) {
			payments = new ArrayList<PaymentsModel>();
		}
		model.setPayments(payments);

		List<ApplicationsModel> applications = getMockApplications(authToken);
		if (applications == null) {
			applications = new ArrayList<ApplicationsModel>();
		}
		model.setApplications(applications);

	}

	static List<MailOnlineModel> getMockMailOnline(String authToken) {
		String fileName = getFilepath(authToken + ".xls");
		try {
			FileInputStream fis = new FileInputStream(fileName);

			final HSSFWorkbook wb = new HSSFWorkbook(fis);

			final ExcelParser<MailOnlineModel> parser = new ExcelParser<MailOnlineModel>(
					1);

			final Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(new Integer(1), "id");
			map.put(new Integer(3), "mailType");
			map.put(new Integer(2), "mailTitle");
			map.put(new Integer(4), "mailOnlineDate");
			map.put(new Integer(6), "mailStatusType");
			map.put(new Integer(5), "mailOnlinePDFPath");

			final List<MailOnlineModel> list = parser.getObjects(
					wb.getSheet("Mail Online"), MailOnlineModel.class, map);

			for (MailOnlineModel item : list) {
				String title = item.getMailTitle();
				String path = item.getMailOnlinePDFPath();

				String[] titles = title.split(",");
				String[] paths = path.split(",");

				if (titles.length > 1) {
					item.setMailTitle(titles[0].trim());
					item.setMailOnlinePDFPath(paths[0].trim());
					List<MailOnlineAttachmentsModel> attachments = new ArrayList<MailOnlineAttachmentsModel>(
							titles.length - 1);
					MailOnlineAttachmentsModel moam = null;
					for (int i = 1; i < titles.length; i++) {
						moam = new MailOnlineAttachmentsModel();
						moam.setAttachmentDescription(titles[i].trim());
						moam.setAttachmentPDFPath(paths[i].trim());
						attachments.add(moam);
					}
					item.setAttachments(attachments);
				}
			}

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static List<PaymentsModel> getMockPayments(String authToken) {
		String fileName = getFilepath(authToken + ".xls");
		try {
			FileInputStream fis = new FileInputStream(fileName);

			final HSSFWorkbook wb = new HSSFWorkbook(fis);

			final ExcelParser<PaymentsModel> parser = new ExcelParser<PaymentsModel>(
					1);

			final Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(new Integer(1), "id");
			map.put(new Integer(2), "type");
			map.put(new Integer(3), "date");
			map.put(new Integer(4), "amount");
			map.put(new Integer(5), "banckAccount");

			final List<PaymentsModel> list = parser.getObjects(
					wb.getSheetAt(0), PaymentsModel.class, map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	static List<ApplicationsModel> getMockApplications(String authToken) {
		String fileName = getFilepath(authToken + ".xls");
		try {
			FileInputStream fis = new FileInputStream(fileName);

			final HSSFWorkbook wb = new HSSFWorkbook(fis);

			final ExcelParser<ApplicationsModel> parser = new ExcelParser<ApplicationsModel>(
					1);

			final Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(new Integer(1), "id");
			map.put(new Integer(2), "type");
			map.put(new Integer(3), "provider");
			map.put(new Integer(4), "campus");
			map.put(new Integer(5), "program");
			map.put(new Integer(6), "status");
			map.put(new Integer(7), "dateReceived");
			map.put(new Integer(8), "listOfAssistanceReceived");
			map.put(new Integer(9), "declinedReason");
			map.put(new Integer(10), "weekEntitlement");
			map.put(new Integer(11), "livingCostsStartDate");
			map.put(new Integer(12), "livingCostsAmount");

			map.put(new Integer(13), "startDate");
			map.put(new Integer(14), "endDate");

			map.put(new Integer(15), "studentID");

			final List<ApplicationsModel> list = parser.getObjects(
					wb.getSheetAt(1), ApplicationsModel.class, map);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PersonalDetailsModel getPersonalDetails(String authToken) {

		String fileName = getFilepath(authToken + ".xls");
		PersonalDetailsModel personalDetailsModel = new PersonalDetailsModel();

		try {
			FileInputStream fis = new FileInputStream(fileName);

			final HSSFWorkbook wb = new HSSFWorkbook(fis);

			final ExcelParser<AddressModel> addressParser = new ExcelParser<AddressModel>(1);
			final ExcelParser<BankAccountModel> bankAccountsParser = new ExcelParser<BankAccountModel>(1);
			final ExcelParser<PersonalDetailsModel> personalDetailsParser = new ExcelParser<PersonalDetailsModel>(1);
		
			Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(new Integer(9), "startDate");
			map.put(new Integer(5), "streetLine1Address");
			map.put(new Integer(6), "streetLine2Address");
			map.put(new Integer(7), "suburb");
			map.put(new Integer(8), "city");
			final List<AddressModel> studyAddressList = addressParser.getObjects(wb.getSheet("Personal Details"), AddressModel.class, map);
			map.clear();

			map.put(new Integer(14), "startDate");
			map.put(new Integer(10), "streetLine1Address");
			map.put(new Integer(11), "streetLine2Address");
			map.put(new Integer(12), "suburb");
			map.put(new Integer(13), "city");
			final List<AddressModel> postalAddressList = addressParser.getObjects(wb.getSheet("Personal Details"), AddressModel.class, map);
			map.clear();

			map.put(new Integer(1), "bankAccountNumber");
			map.put(new Integer(2), "accountHolderName");
			map.put(new Integer(3), "listOfAssociatedAccountTypes");
			final List<BankAccountModel> bankDetailsList = bankAccountsParser.getObjects(wb.getSheet("Bank Account Details"), BankAccountModel.class, map);

			

			map.clear();
			map.put(new Integer(1), "name");
			map.put(new Integer(2), "phoneNumber");
			map.put(new Integer(4), "emailAddress");
			map.put(new Integer(3), "mobileNumber");
			map.put(new Integer(15), "employerName");
			map.put(new Integer(16), "primaryWeeklyIncomeTemp");
			map.put(new Integer(17), "secondaryWeeklyIncomeTemp");
			final List<PersonalDetailsModel> personalDetailsList = personalDetailsParser.getObjects(wb.getSheet("Personal Details"), PersonalDetailsModel.class, map);

			personalDetailsModel = (PersonalDetailsModel) personalDetailsList.get(0);
			personalDetailsModel.setStudyAddress(studyAddressList.get(0));
			personalDetailsModel.setPostalAddress(postalAddressList.get(0));
			personalDetailsModel.setBankAccounts(bankDetailsList);
			//personalDetailsModel.setListIncomeDetails(incomeDetailsList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(personalDetailsModel.getPrimaryWeeklyIncomeTemp() != null){
			BigDecimal primaryWeeklyIncome = new BigDecimal(personalDetailsModel.getPrimaryWeeklyIncomeTemp());
			primaryWeeklyIncome.setScale(2, BigDecimal.ROUND_DOWN);
			personalDetailsModel.setPrimaryWeeklyIncome(primaryWeeklyIncome);
		}
		if(personalDetailsModel.getSecondaryWeeklyIncomeTemp() != null){
			BigDecimal secondaryWeeklyIncome = new BigDecimal(personalDetailsModel.getSecondaryWeeklyIncomeTemp());
			secondaryWeeklyIncome.setScale(2, BigDecimal.ROUND_DOWN);
			personalDetailsModel.setSecondaryWeeklyIncome(secondaryWeeklyIncome);
		}
		
		return personalDetailsModel;

	}
}
