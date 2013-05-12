package com.apptech.first.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.MTextBox;

public class IncomeDetailsWidget extends Composite  {

	private static IncomeDetailsWidgetUiBinder uiBinder = GWT.create(IncomeDetailsWidgetUiBinder.class);
	
	@UiField
	MTextBox companyName, income;

	interface IncomeDetailsWidgetUiBinder extends UiBinder<Widget, IncomeDetailsWidget> {
	}

	public IncomeDetailsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	

	public IncomeDetailsWidget(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		
	}

	public void setTextTxtBoxCompanyName(String text){
		this.companyName.setText(text);
	}
	
	public void setTextTxtBoxIncome (String text){
		this.income.setText(text);
	}


	public MTextBox getCompanyName() {
		return companyName;
	}


	public MTextBox getIncome() {
		return income;
	}

}
