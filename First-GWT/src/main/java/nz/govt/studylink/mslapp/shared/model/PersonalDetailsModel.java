package nz.govt.studylink.mslapp.shared.model;

import java.math.BigDecimal;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PersonalDetailsModel implements IsSerializable {

	private String name;
	private Long id;

	private String authToken;

	private AddressModel studyAddress;
	private AddressModel postalAddress;
	private String phoneNumber;
	private String emailAddress;
	private String mobileNumber;
	private String faxNumber;
	private List<BankAccountModel> bankAccounts;
	private String employerName;
	private BigDecimal primaryWeeklyIncome;
	private BigDecimal secondaryWeeklyIncome;
	private Integer primaryWeeklyIncomeTemp;  	// Added because reading BigDecimal from excel does'nt have a converter
	private Integer secondaryWeeklyIncomeTemp; // Added because reading BigDecimal from excel does'nt have a converter
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PersonalDetailsModel() {

	}

	public PersonalDetailsModel(long id, String name, String authToken) {
		this.id = id;
		this.name = name;
		this.authToken = authToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public AddressModel getStudyAddress() {
		return studyAddress;
	}

	public void setStudyAddress(AddressModel studyAddress) {
		this.studyAddress = studyAddress;
	}

	public AddressModel getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(AddressModel postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public List<BankAccountModel> getBankAccounts() {
		return bankAccounts;
	}

	public void setBankAccounts(List<BankAccountModel> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public BigDecimal getPrimaryWeeklyIncome() {
		return primaryWeeklyIncome;
	}

	public void setPrimaryWeeklyIncome(BigDecimal primaryWeeklyIncome) {
		this.primaryWeeklyIncome = primaryWeeklyIncome;
	}

	public BigDecimal getSecondaryWeeklyIncome() {
		return secondaryWeeklyIncome;
	}

	public void setSecondaryWeeklyIncome(BigDecimal secondaryWeeklyIncome) {
		this.secondaryWeeklyIncome = secondaryWeeklyIncome;
	}

	public Integer getPrimaryWeeklyIncomeTemp() {
		return primaryWeeklyIncomeTemp;
	}

	public void setPrimaryWeeklyIncomeTemp(Integer primaryWeeklyIncomeTemp) {
		this.primaryWeeklyIncomeTemp = primaryWeeklyIncomeTemp;
	}

	public Integer getSecondaryWeeklyIncomeTemp() {
		return secondaryWeeklyIncomeTemp;
	}

	public void setSecondaryWeeklyIncomeTemp(Integer secondaryWeeklyIncomeTemp) {
		this.secondaryWeeklyIncomeTemp = secondaryWeeklyIncomeTemp;
	}

	
	
}
