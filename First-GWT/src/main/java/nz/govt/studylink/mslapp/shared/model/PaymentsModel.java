package nz.govt.studylink.mslapp.shared.model;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class PaymentsModel implements IsSerializable{

	private String type;
	private Double amount;
	private Date date;
	private Long id;
	private String banckAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentsModel(){
		
	}
	
	public PaymentsModel(long id, String type, double amount, Date date, String banckAccount) {
		this.banckAccount = banckAccount;
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.date = date;
	}
	


	public String getBanckAccount() {
		return banckAccount;
	}
	public void setBanckAccount(String banckAccount) {
		this.banckAccount = banckAccount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
