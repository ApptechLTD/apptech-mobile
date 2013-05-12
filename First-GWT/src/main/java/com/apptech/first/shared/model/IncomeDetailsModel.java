package com.apptech.first.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class IncomeDetailsModel implements IsSerializable {
	
	private String employerName;
	private Integer incomeAmount;
	
	public IncomeDetailsModel() {
	}

	public IncomeDetailsModel(String employerName, Integer incomeAmount) {
		this.employerName = employerName;
		this.incomeAmount = incomeAmount;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public Integer getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Integer incomeAmount) {
		this.incomeAmount = incomeAmount;
	}
	

}
