package com.apptech.first.shared.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

public class BankAccountModel implements IsSerializable {

	private String bankAccountNumber;
	private String accountHolderName;

	private List<String> listOfAssociatedAccountTypes;

	public BankAccountModel() {
		listOfAssociatedAccountTypes = new ArrayList<String>();
	}

	public BankAccountModel(String bankAccountNumber, String accountHolderName,
			List<String> listOfAssociatedAccountTypes) {
		this.bankAccountNumber = bankAccountNumber;
		this.accountHolderName = accountHolderName;
		this.listOfAssociatedAccountTypes = listOfAssociatedAccountTypes;
	}

	public List<String> getListOfAssociatedAccountTypes() {
		return listOfAssociatedAccountTypes;
	}

	public void setListOfAssociatedAccountTypes(
			List<String> listOfAssociatedAccountTypes) {
		this.listOfAssociatedAccountTypes = listOfAssociatedAccountTypes;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

}
