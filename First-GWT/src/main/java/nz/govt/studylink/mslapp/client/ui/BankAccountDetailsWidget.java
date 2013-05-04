package nz.govt.studylink.mslapp.client.ui;

import nz.govt.studylink.mslapp.shared.model.BankAccountModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

import java.util.Random;

public class BankAccountDetailsWidget extends Composite {

	interface BankAccountDetailsWidgetUiBinder extends UiBinder<Widget, BankAccountDetailsWidget> {
	}

	private static BankAccountDetailsWidgetUiBinder uiBinder = GWT.create(BankAccountDetailsWidgetUiBinder.class);

	interface PageStyle extends CssResource {
		String mandatoryContainerHighlightIOS();
		String mandatoryContainerHighlightAndroid();
	}

	String mandatoryContainerHighlight = "";
	String mandatoryContainerHighlightClassName = "";
	
	@UiField
	Label lblBankAccountNumber, lblBankAccountName;

	@UiField
	MTextBox txtBoxBankAccountNumber, txtBoxBankAccountName;

	@UiField
	HTMLPanel usedForContainer;
	@UiField
	CheckBox allowanceCheckBox;
	@UiField
	CheckBox loanCheckBox;
	@UiField
	CheckBox courseCostsCheckBox;
	@UiField
	Label lblAllowance, lblLoan, lblCourceCosts;

	@UiField
	HTML bankAccountNumber, bankAccountName, usedFor;

	@UiField
	PageStyle style;

	@UiField
	WidgetList bankAccountDetailsList;

	public boolean canAddNextBlankWidget = true;

	/**
	 * Constructor, initiate this widget to the UI XML of the same name.
	 */
	public BankAccountDetailsWidget() {

		initWidget(uiBinder.createAndBindUi(this));
	}

	/**
	 * Reset all the values
	 */
	public void clearAll() {

		/*
		 * bankAccountNumberTextBox.setText("");
		 * bankAccountNameTextBox.setText("");
		 */
		allowanceCheckBox.setValue(false);
		loanCheckBox.setValue(false);
		courseCostsCheckBox.setValue(false);
	}

	public boolean isBankAccountNameEmpty() {

		if (txtBoxBankAccountName.getText().trim().equals(""))
			return true;

		return false;
	}

	public Boolean isBankAccountNumberEmpty() {
		if (txtBoxBankAccountNumber.getText().trim().equals(""))
			return true;

		return false;
	}

	public void populateUsedForUpdateContainer(BankAccountModel bankAccountModel) {

		allowanceCheckBox.setValue(false);
		loanCheckBox.setValue(false);
		courseCostsCheckBox.setValue(false);

		for (String usedFor : bankAccountModel.getListOfAssociatedAccountTypes()) {
			if (usedFor.trim().equals("Allowance")) {
				allowanceCheckBox.setValue(true);
			}
			if (usedFor.trim().equals("Loan Living Costs")) {
				loanCheckBox.setValue(true);
			}
			if (usedFor.trim().equals("Course Related Costs")) {
				courseCostsCheckBox.setValue(true);
			}

		}
		
		addHandlersForLabelInUsedForContainer();
	}

	public void populateUsedForViewContainer(BankAccountModel bankAccountModel) {

		// radioButtonAllowance.setVisible(false);
		// radioButtonLoanCosts.setVisible(false);
		// radioButtonCourseCosts.setVisible(false);
		lblAllowance.setVisible(false);
		lblCourceCosts.setVisible(false);
		lblLoan.setVisible(false);
		allowanceCheckBox.setVisible(false);
		loanCheckBox.setVisible(false);
		courseCostsCheckBox.setVisible(false);

		for (String usedFor : bankAccountModel.getListOfAssociatedAccountTypes()) {
			if (usedFor.trim().equals("Allowance")) {
				lblAllowance.setVisible(true);
			}
			if (usedFor.trim().equals("Loan Living Costs")) {
				lblLoan.setVisible(true);
			}
			if (usedFor.trim().equals("Course Related Costs")) {
				lblCourceCosts.setVisible(true);
			}
			/* HTML htmlUsedFor = new HTML(usedFor); */
			/*
			 * Label lblUsedFor = new Label(); lblUsedFor.setText(usedFor);
			 * usedForContainer.add(lblUsedFor);
			 */

		}
	}

	public void setTextLblBankAccountName(String text) {
		lblBankAccountName.setText(text);
	}

	public void setTextLblBankAccountNumber(String text) {
		lblBankAccountNumber.setText(text);
	}

	public void setTextTxtBoxBankAccountNumber(String text) {
		txtBoxBankAccountNumber.setText(text);
	}

	public void setTextTxtBoxLblBankAccountName(String text) {
		txtBoxBankAccountName.setText(text);
	}

	public MTextBox getTxtBoxBankAccountNumber() {
		return txtBoxBankAccountNumber;
	}

	public MTextBox getTxtBoxBankAccountName() {
		return txtBoxBankAccountName;
	}

	public HTML getBankAccountNumber() {
		return bankAccountNumber;
	}

	public HTML getBankAccountName() {
		return bankAccountName;
	}

	public HTML getUsedFor() {
		return usedFor;
	}

	public CheckBox getAllowanceCheckBox() {
		return allowanceCheckBox;
	}

	public CheckBox getCourseCostsCheckBox() {
		return courseCostsCheckBox;
	}

	public CheckBox getLoanCheckBox() {
		return loanCheckBox;
	}

	public void hightLightAddressWidget(Boolean bool) {
		
		if(MGWT.getOsDetection().isAndroid()){
			mandatoryContainerHighlight = style.mandatoryContainerHighlightAndroid();
			//mandatoryContainerHighlightClassName = "mandatoryContainerHighlightAndroid";
		}
		else if (MGWT.getOsDetection().isIOs()){
			mandatoryContainerHighlight = style.mandatoryContainerHighlightIOS();
			//mandatoryContainerHighlightClassName = "mandatoryContainerHighlightIOS";
		}

		if (bool)

			bankAccountDetailsList.getElement().addClassName(mandatoryContainerHighlight);
		else
			bankAccountDetailsList.getElement().removeClassName(mandatoryContainerHighlight);

	}
	
	public void addHandlersForLabelInUsedForContainer(){
		//, , ;
		TouchDelegate delegateLblAllowance = new TouchDelegate(lblAllowance);
		delegateLblAllowance.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				allowanceCheckBox.setValue(!allowanceCheckBox.getValue());
				
			}
		});
		
		TouchDelegate delegateLblLoan = new TouchDelegate(lblLoan);
		delegateLblLoan.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				loanCheckBox.setValue(!loanCheckBox.getValue());
				
			}
		});
		
		TouchDelegate delegateLblCourceCosts = new TouchDelegate(lblCourceCosts);
		delegateLblCourceCosts.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				courseCostsCheckBox.setValue(!courseCostsCheckBox.getValue());
				
			}
		});
	}

}
