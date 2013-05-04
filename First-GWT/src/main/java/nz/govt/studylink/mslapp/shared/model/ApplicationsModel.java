package nz.govt.studylink.mslapp.shared.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.google.gwt.user.client.rpc.IsSerializable;


public class ApplicationsModel implements IsSerializable{

	private String type; //Student Allowance OR Student Loan
	
	private String status; //Pending, Approved, Declined
	private Long id;
	
	private Date dateReceived;
	
	//For Student Loan
	//Possible items: Compulsory Fees, Course-Related Costs, Living Costs
	private List<String> listOfAssistanceReceived;
	
	private Integer weekEntitlement;
	private String 	declinedReason;
	
	//Current Living Cost Details
	private Date 	livingCostsStartDate;
	private Double 	livingCostsAmount;
	
	private String reasonDeclined;
	
	//Study Details
	private String 	provider;
	private String 	program;
	private String 	campus;
	private Date 	startDate;
	private Date 	endDate;
	private String 	studentID;
	
	public ApplicationsModel(){
		
	}
	
	public ApplicationsModel(long id, String type, String provider, String program, String status) {
		this.id = id;
		this.type = type;
		this.provider = provider;
		this.program = program;
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	@XmlElementWrapper(name="assistanceReceivedList")
	@XmlElement
	public List<String> getListOfAssistanceReceived() {
		return listOfAssistanceReceived;
	}

	public void setListOfAssistanceReceived(List<String> listOfAssistanceReceived) {
		this.listOfAssistanceReceived = listOfAssistanceReceived;
	}

	public Integer getWeekEntitlement() {
		return weekEntitlement;
	}

	public void setWeekEntitlement(Integer weekEntitlement200) {
		this.weekEntitlement = weekEntitlement200;
	}

	public String getDeclinedReason() {
		return declinedReason;
	}

	public void setDeclinedReason(String declinedReason) {
		this.declinedReason = declinedReason;
	}
	
	public Date getLivingCostsStartDate() {
		return livingCostsStartDate;
	}

	public void setLivingCostsStartDate(Date livingCostsStartDate) {
		this.livingCostsStartDate = livingCostsStartDate;
	}

	public Double getLivingCostsAmount() {
		return livingCostsAmount;
	}

	public void setLivingCostsAmount(Double livingCostsAmount) {
		this.livingCostsAmount = livingCostsAmount;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReasonDeclined() {
		return reasonDeclined;
	}

	public void setReasonDeclined(String reasonDeclined) {
		this.reasonDeclined = reasonDeclined;
	}	
	
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
}