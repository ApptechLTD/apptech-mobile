package nz.govt.studylink.mslapp.client.views;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nz.govt.studylink.mslapp.client.ui.AddressWidget;
import nz.govt.studylink.mslapp.client.ui.BankAccountDetailsWidget;
import nz.govt.studylink.mslapp.client.ui.ImageButton;
import nz.govt.studylink.mslapp.client.ui.IncomeDetailsWidget;
import nz.govt.studylink.mslapp.shared.model.AddressModel;
import nz.govt.studylink.mslapp.shared.model.BankAccountModel;
import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.dialog.Dialogs;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.WidgetList;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

public class PersonalDetailsUpdateViewImpl extends Composite implements PersonalDetailsUpdateView {

	private static PersonalDetailsUpdateViewImplUiBinder uiBinder = GWT.create(PersonalDetailsUpdateViewImplUiBinder.class);

	interface PersonalDetailsUpdateViewImplUiBinder extends UiBinder<Widget, PersonalDetailsUpdateViewImpl> {
	}

	interface PageStyle extends CssResource {
		String divMandatory();
		String mandatoryContainerHighlightIOS();
		String mandatoryEmailHighlight();
		String mandatoryContainerHighlightAndroid();
	}

	public PersonalDetailsUpdateViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	/*
	 * This object saves the instance of the current view. Place model is
	 * retained so that if cancel is pressed the data is retained in the place.
	 */

	private PersonalDetailsModel personalDetailsModel;

	Map<String, HTMLPanel> banckAcountPanelsMap = new HashMap<String, HTMLPanel>();
	Map<String, HTMLPanel> mapIncomeDetailsCellContainer = new HashMap<String, HTMLPanel>();

	Map<String, BankAccountDetailsWidget> mapBankAccountDetailsWidget = new HashMap<String, BankAccountDetailsWidget>();
	Map<String, IncomeDetailsWidget> mapIncomeDetailsWidget = new HashMap<String, IncomeDetailsWidget>();
	String mandatoryContainerHighlight = "";
	String mandatoryContainerHighlightClassName = "";

	private PersonalDetailsUpdateView.Presenter presenter;

	@UiField
	ImageButton deleteName;

	@UiField
	Label name;

	@UiField
	MTextBox phoneNumber, mobileNumber, emailAddress, employerName,
			primaryWeeklyIncome, secondaryWeeklyIncome;

	@UiField
	AddressWidget studyAddressWidget, postalAddressWidget;

	@UiField
	HeaderButton doneButton, cancelButton;

	@UiField
	HTMLPanel deleteButtonContainer, bankAccountDetailsContainer,
			bankAccountsSectionContainer, incomeDetailsContainer,
			deleteButtonForIncomeDetailsContainer,
			incomeDetailsSectionContainer;

	@UiField
	ImageButton delete;

	@UiField
	PageStyle style;

	@UiField
	ScrollPanel scrollPanel;
	
	@UiField
	WidgetList incomeDetails;
	
	@UiField
	HorizontalPanel emailContainer;

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("cancelButton")
	void onBackTap(TapEvent e) {
		presenter.cancelPressed();
	}

	@UiHandler("doneButton")
	void onDoneTap(TapEvent e) {
		
		if (validateContactFields() && validateBankAccountFields() && validateIncomeDetails()) {

			personalDetailsModel.setName(name.getText());
			
			if (phoneNumber.getText().trim().equals(""))
				personalDetailsModel.setPhoneNumber(null);
			else
				personalDetailsModel.setPhoneNumber(phoneNumber.getText());
			if (mobileNumber.getText().trim().equals(""))
				personalDetailsModel.setMobileNumber(null);
			else
				personalDetailsModel.setMobileNumber(mobileNumber.getText());
				
			personalDetailsModel.setEmailAddress(emailAddress.getText());

			AddressModel studyAddress = new AddressModel();
			studyAddress.setStreetLine1Address(studyAddressWidget.getTxtBoxAddressLine1().getText());
			studyAddress.setStreetLine2Address(studyAddressWidget.getTxtBoxAddressLine2().getText());
			studyAddress.setSuburb(studyAddressWidget.getTxtBoxAddressSuburb().getText());
			studyAddress.setCity(studyAddressWidget.getTxtBoxAddressCity().getText());

			AddressModel postalAddress = new AddressModel();
			postalAddress.setStreetLine1Address(postalAddressWidget.getTxtBoxAddressLine1().getText());
			postalAddress.setStreetLine2Address(postalAddressWidget.getTxtBoxAddressLine2().getText());
			postalAddress.setSuburb(postalAddressWidget.getTxtBoxAddressSuburb().getText());
			postalAddress.setCity(postalAddressWidget.getTxtBoxAddressCity().getText());

			personalDetailsModel.setStudyAddress(studyAddress);
			personalDetailsModel.setPostalAddress(postalAddress);

			if (personalDetailsModel.getBankAccounts() != null) {
				personalDetailsModel.getBankAccounts().clear();
			}

			for (Entry<String, BankAccountDetailsWidget> entry : mapBankAccountDetailsWidget.entrySet()) {

				if (!entry.getKey().equals("blank")) {
					BankAccountModel bankAccountModel = new BankAccountModel();
					bankAccountModel.setAccountHolderName(entry.getValue().getTxtBoxBankAccountName().getText());
					bankAccountModel.setBankAccountNumber(entry.getValue().getTxtBoxBankAccountNumber().getText());
					List<String> listUsedFor = new ArrayList<String>();
					
					if (entry.getValue().getAllowanceCheckBox().getValue()) {
						listUsedFor.add("Allowance");
					}
					if (entry.getValue().getLoanCheckBox().getValue()) {
						listUsedFor.add("Loan Living Costs");
					}
					if (entry.getValue().getCourseCostsCheckBox().getValue()) {
						listUsedFor.add("Course Related Costs");
					}

					bankAccountModel.setListOfAssociatedAccountTypes(listUsedFor);
					personalDetailsModel.getBankAccounts().add(bankAccountModel);
				}
			}
			personalDetailsModel.setEmployerName(employerName.getText());
			if (primaryWeeklyIncome.getText().trim().equals(""))
				personalDetailsModel.setPrimaryWeeklyIncome(null);
			else
			{
				BigDecimal value = new BigDecimal(primaryWeeklyIncome.getText());
				value.setScale(2, BigDecimal.ROUND_DOWN);
				personalDetailsModel.setPrimaryWeeklyIncome(value);
			}
			if (secondaryWeeklyIncome.getText().trim().equals(""))
				personalDetailsModel.setSecondaryWeeklyIncome(null);
			else
			{
				BigDecimal value = new BigDecimal(secondaryWeeklyIncome.getText());
				value.setScale(2, BigDecimal.ROUND_DOWN);
				personalDetailsModel.setSecondaryWeeklyIncome(value);
			}
			presenter.donePressed(personalDetailsModel);

			banckAcountPanelsMap.clear();
			mapBankAccountDetailsWidget.clear();
		}

	}

	@UiHandler("deleteHomePhone")
	void onDeleteHomePhoneButtonTap(TapEvent e) {
		phoneNumber.setText("");
	}

	@UiHandler("deleteMobilePhone")
	void onDeleteMobilePhoneButtonTap(TapEvent e) {
		mobileNumber.setText("");
	}

	@UiHandler("deleteEmail")
	void onDeleteEmailButtonTap(TapEvent e) {
		emailAddress.setText("");
	}

	@UiHandler("deleteStudyAddress")
	void onDeleteStudyAddressButtonTap(TapEvent e) {

		studyAddressWidget.setTextTxtBoxAddressLine1("");
		studyAddressWidget.setTextTxtBoxAddressLine2("");
		studyAddressWidget.setTextTxtBoxAddressSuburb("");
		studyAddressWidget.setTextTxtBoxAddressCity("");

	}

	@UiHandler("deletePostalAddress")
	void onDeletePostalAddressButtonTap(TapEvent e) {

		postalAddressWidget.setTextTxtBoxAddressLine1("");
		postalAddressWidget.setTextTxtBoxAddressLine2("");
		postalAddressWidget.setTextTxtBoxAddressSuburb("");
		postalAddressWidget.setTextTxtBoxAddressCity("");

	}

	@UiHandler("deleteEmployerName")
	void onDeleteEmployerName(TapEvent e) {
		employerName.setText("");
	}

	@UiHandler("deletePrimaryWeeklyIncome")
	void onDeletePrimaryWeeklyIncome(TapEvent e) {
		primaryWeeklyIncome.setText("");
	}

	@UiHandler("deleteSecondaryWeeklyIncome")
	void onDeleteSecondaryWeeklyIncome(TapEvent e) {
		secondaryWeeklyIncome.setText("");
	}

	@Override
	public void populate(PersonalDetailsModel model) {

		this.personalDetailsModel = model;
		
		
		populateContactInformation(model);
		populateBankAccountDetails(model);
		populateIncomeDetails(model);

		addHandlersForTextBoxKeyPressed();
		scrollPanel.refresh();
		scrollPanel.scrollTo(0, 0);
	}

	@Override
	public void clear() {

		if(MGWT.getOsDetection().isAndroid()){
			mandatoryContainerHighlight = style.mandatoryContainerHighlightAndroid();
			//mandatoryContainerHighlightClassName = "mandatoryContainerHighlightAndroid";
		}
		else if (MGWT.getOsDetection().isIOs()){
			mandatoryContainerHighlight = style.mandatoryContainerHighlightIOS();
			//mandatoryContainerHighlightClassName = "mandatoryContainerHighlightIOS";
		}
		
		name.setText("");

		phoneNumber.setText("");
		mobileNumber.setText("");
		emailAddress.setText("");
		employerName.setText("");
		primaryWeeklyIncome.setText("");
		secondaryWeeklyIncome.setText("");
		bankAccountsSectionContainer.clear();
		emailContainer.getElement().getParentElement().removeClassName(style.mandatoryEmailHighlight());
		studyAddressWidget.hightLightAddressWidget(false);
		postalAddressWidget.hightLightAddressWidget(false);
		
		for (Entry<String, BankAccountDetailsWidget> entry : mapBankAccountDetailsWidget.entrySet()) {
			entry.getValue().hightLightAddressWidget(false);
		}
		incomeDetails.getElement().removeClassName(mandatoryContainerHighlight);
	}

	private void populateContactInformation(PersonalDetailsModel model) {

		name.setText(model.getName());
		phoneNumber.setText(model.getPhoneNumber());
		mobileNumber.setText(model.getMobileNumber());
		emailAddress.setText(model.getEmailAddress());

		AddressModel studyAddress = (AddressModel) model.getStudyAddress();
		AddressModel postalAddress = (AddressModel) model.getPostalAddress();

		studyAddressWidget.getAddressType().getElement().getStyle().setWidth(85, Unit.PX);
		postalAddressWidget.getAddressType().getElement().getStyle().setWidth(85, Unit.PX);

		studyAddressWidget.setAddressModelForUpdate(studyAddress);
		postalAddressWidget.setAddressModelForUpdate(postalAddress);

		studyAddressWidget.setAddressType("Study:");
		postalAddressWidget.setAddressType("Postal:");
	}

	private void populateBankAccountDetails(PersonalDetailsModel model) {

		delete.setVisible(false);

		deleteButtonContainer.clear();
		bankAccountDetailsContainer.clear();
		bankAccountsSectionContainer.clear();

		for (int i = 0; i < model.getBankAccounts().size(); i++) {

			HTMLPanel bankAccountContainerDetails = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);
			HTMLPanel bankAccountContainer = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);
			HTMLPanel deleteButtonContainerActual = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);

			bankAccountContainerDetails.setStyleName(bankAccountDetailsContainer.getStyleName());
			deleteButtonContainerActual.setStyleName(deleteButtonContainer.getStyleName());

			ImageButton deleteButton = new ImageButton();
			deleteButton.setStyleName(delete.getStyleName());
			deleteButton.setImage(delete.getImage());

			TouchDelegate touchDelegate = new TouchDelegate(deleteButton);
			setDeleteButtonHandlers(model.getBankAccounts().get(i).getBankAccountNumber(), touchDelegate);

			BankAccountDetailsWidget bankAccountDetailsWidget = new BankAccountDetailsWidget();
			/*bankAccountDetailsWidget.getBankAccountName().getElement().getStyle().setWidth(60, Unit.PX);
			bankAccountDetailsWidget.getBankAccountNumber().getElement().getStyle().setWidth(60, Unit.PX);
			bankAccountDetailsWidget.getUsedFor().getElement().getStyle().setWidth(60, Unit.PX);*/

			bankAccountDetailsWidget.setTextTxtBoxBankAccountNumber(model.getBankAccounts().get(i).getBankAccountNumber());
			bankAccountDetailsWidget.setTextTxtBoxLblBankAccountName(model.getBankAccounts().get(i).getAccountHolderName());
			bankAccountDetailsWidget.populateUsedForUpdateContainer(model.getBankAccounts().get(i));

			bankAccountContainerDetails.add(bankAccountDetailsWidget);

			deleteButtonContainerActual.add(deleteButton);

			bankAccountContainer.add(deleteButtonContainerActual);
			bankAccountContainer.add(bankAccountContainerDetails);

			mapBankAccountDetailsWidget.put(model.getBankAccounts().get(i).getBankAccountNumber(), bankAccountDetailsWidget);
			banckAcountPanelsMap.put(model.getBankAccounts().get(i).getBankAccountNumber(), bankAccountContainer);

			bankAccountsSectionContainer.add(bankAccountContainer);

		}

		addBlankBankAccountDetails();
	}

	private void populateIncomeDetails(PersonalDetailsModel model) {
		//incomeDetails.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		employerName.setText(model.getEmployerName());
		if (model.getPrimaryWeeklyIncome() != null)
			primaryWeeklyIncome.setText(model.getPrimaryWeeklyIncome().toString());

		if (model.getSecondaryWeeklyIncome() != null)
			secondaryWeeklyIncome.setText(model.getSecondaryWeeklyIncome().toString());

	}

	ImageButton blankDeleteButton;

	private void addBlankBankAccountDetails() {
		HTMLPanel blankBankAccountContainerDetails = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);
		HTMLPanel blankBankAccountContainer = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);
		HTMLPanel deleteButtonBlankContainerActual = new HTMLPanel(SafeHtmlUtils.EMPTY_SAFE_HTML);

		blankBankAccountContainerDetails.setStyleName(bankAccountDetailsContainer.getStyleName());
		deleteButtonBlankContainerActual.setStyleName(deleteButtonContainer.getStyleName());

		blankDeleteButton = new ImageButton();
		blankDeleteButton.setStyleName(delete.getStyleName());
		blankDeleteButton.setImage(delete.getImage());

		BankAccountDetailsWidget blankBankAccountDetailsWidget = new BankAccountDetailsWidget();
		blankBankAccountDetailsWidget.addHandlersForLabelInUsedForContainer();
		/*blankBankAccountDetailsWidget.getBankAccountName().getElement().getStyle().setWidth(60, Unit.PX);
		blankBankAccountDetailsWidget.getBankAccountNumber().getElement().getStyle().setWidth(60, Unit.PX);
		blankBankAccountDetailsWidget.getUsedFor().getElement().getStyle().setWidth(60, Unit.PX);*/

		blankBankAccountContainerDetails.add(blankBankAccountDetailsWidget);
		setKeyUpHandlerForBlankAccountDetails(blankBankAccountDetailsWidget);

		deleteButtonBlankContainerActual.add(blankDeleteButton);
		blankBankAccountContainer.add(deleteButtonBlankContainerActual);
		blankBankAccountContainer.add(blankBankAccountContainerDetails);

		bankAccountsSectionContainer.add(blankBankAccountContainer);
		banckAcountPanelsMap.put("blank", blankBankAccountContainer);
		mapBankAccountDetailsWidget.put("blank", blankBankAccountDetailsWidget);
		scrollPanel.refresh();
	}

	private void setDeleteButtonHandlers(final String accountNumber, TouchDelegate delegate) {
		delegate.addTapHandler(new TapHandler() {

			@Override
			public void onTap(TapEvent event) {

				HTMLPanel container = banckAcountPanelsMap.get(accountNumber);
				container.removeFromParent();
				for (BankAccountModel bankAccount : personalDetailsModel.getBankAccounts()) {
					if (bankAccount.getBankAccountNumber().equals(accountNumber)) {
						personalDetailsModel.getBankAccounts().remove(bankAccount);
						break;
					}
				}
				mapBankAccountDetailsWidget.remove(accountNumber);

			}
		});

	}

	private void setKeyUpHandlerForBlankAccountDetails(final BankAccountDetailsWidget bankAccountDetailsWidget) {

		bankAccountDetailsWidget.getTxtBoxBankAccountName().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (bankAccountDetailsWidget.canAddNextBlankWidget) {

					if (!(bankAccountDetailsWidget.isBankAccountNameEmpty()) && !(bankAccountDetailsWidget.isBankAccountNumberEmpty())) {
						HTMLPanel panel = banckAcountPanelsMap.get("blank");
						banckAcountPanelsMap.remove("blank");
						banckAcountPanelsMap.put(bankAccountDetailsWidget.getTxtBoxBankAccountNumber().getText(), panel);

						BankAccountDetailsWidget widget = mapBankAccountDetailsWidget.get("blank");
						mapBankAccountDetailsWidget.remove("blank");
						mapBankAccountDetailsWidget.put(bankAccountDetailsWidget.getTxtBoxBankAccountNumber().getText(), widget);

						TouchDelegate touchDelegate = new TouchDelegate(blankDeleteButton);
						setDeleteButtonHandlers(bankAccountDetailsWidget.getTxtBoxBankAccountNumber().getText(), touchDelegate);

						addBlankBankAccountDetails();
						bankAccountDetailsWidget.canAddNextBlankWidget = false;

					}
				}

			}
		});

		bankAccountDetailsWidget.getTxtBoxBankAccountNumber().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {

				if (bankAccountDetailsWidget.canAddNextBlankWidget) {

					if (!(bankAccountDetailsWidget.isBankAccountNameEmpty()) && !(bankAccountDetailsWidget.isBankAccountNumberEmpty())) {

						HTMLPanel panel = banckAcountPanelsMap.get("blank");
						banckAcountPanelsMap.remove("blank");
						banckAcountPanelsMap.put(bankAccountDetailsWidget.getTxtBoxBankAccountNumber().getText(), panel);

						addBlankBankAccountDetails();
						bankAccountDetailsWidget.canAddNextBlankWidget = false;

					}
				}
			}
		});
	}

	private Boolean validateContactFields() {
		
		emailContainer.getElement().getParentElement().removeClassName(style.mandatoryEmailHighlight());
		studyAddressWidget.hightLightAddressWidget(false);
		postalAddressWidget.hightLightAddressWidget(false);
		
		Boolean displayAlert = false;
		if (emailAddress.getText().equals("")) {
			emailContainer.getElement().getParentElement().addClassName(style.mandatoryEmailHighlight());
			displayAlert = true;
			
		}
		if (studyAddressWidget.getTxtBoxAddressLine1().getText().equals("") || studyAddressWidget.getTxtBoxAddressCity().getText().equals("")) {
			studyAddressWidget.hightLightAddressWidget(true);
			displayAlert = true;
			
		}
		
		if (!postalAddressWidget.getTxtBoxAddressLine1().getText().trim().equals("") || !postalAddressWidget.getTxtBoxAddressLine2().getText().trim().equals("") 
					|| !postalAddressWidget.getTxtBoxAddressSuburb().getText().trim().equals("") || !postalAddressWidget.getTxtBoxAddressCity().getText().trim().equals("")){
			if (postalAddressWidget.getTxtBoxAddressLine1().getText().equals("") || postalAddressWidget.getTxtBoxAddressCity().getText().equals("")) {
				postalAddressWidget.hightLightAddressWidget(true);
				displayAlert = true;
				
				
			}
		}
		
		if (displayAlert) {
			Dialogs.alert("MyStudyLink", "Please enter all mandatory fields.", null);
			return false;
		} else {
			if (!emailAddress.getText().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
				emailContainer.getElement().getParentElement().addClassName(style.mandatoryEmailHighlight());
				Dialogs.alert("MyStudyLink", "Please enter a valid email address.", null);
				return false;
			}
		}

		return true;

	}

	private Boolean validateBankAccountFields() {
		
		for (Entry<String, BankAccountDetailsWidget> entry : mapBankAccountDetailsWidget.entrySet()) {
			entry.getValue().hightLightAddressWidget(false);
		}
		
		Boolean displayAlert = false;
		for (Entry<String, BankAccountDetailsWidget> entry : mapBankAccountDetailsWidget.entrySet()) {
			if (entry.getValue().getTxtBoxBankAccountName().getText().trim().equals("")) {
				if (entry.getKey().equals("blank") && entry.getValue().getTxtBoxBankAccountNumber().getText().trim().equals("") && entry.getValue().getTxtBoxBankAccountName().getText().trim().equals("")) {
					entry.getValue().hightLightAddressWidget(false);
					continue;
				}
				entry.getValue().hightLightAddressWidget(true);
				displayAlert = true;
				
			}

			else if (entry.getValue().getTxtBoxBankAccountNumber().getText().trim().equals("")) {
				if (entry.getKey().equals("blank") && entry.getValue().getTxtBoxBankAccountNumber().getText().trim().equals("") && entry.getValue().getTxtBoxBankAccountName().getText().trim().equals("")) {
					entry.getValue().hightLightAddressWidget(false);
					continue;
				}
				entry.getValue().hightLightAddressWidget(true);
				displayAlert = true;
			}
		}
		
		if(displayAlert)
		{
			Dialogs.alert("MyStudyLink", "Please enter all mandatory fields.", null);
			return false;
		}

		return true;
	}

	private boolean validateIncomeDetails() {

		incomeDetails.getElement().removeClassName(mandatoryContainerHighlight);
		
		Boolean displayAlert = false;
		
		if (!primaryWeeklyIncome.getText().trim().equals("")) {
			try {
		            BigDecimal value = new BigDecimal(primaryWeeklyIncome.getText());
		            
			} catch (NumberFormatException nfe) {
				displayAlert = true;
			}
		} else if (!secondaryWeeklyIncome.getText().trim().equals("")) {
			try {
				//Integer.parseInt(secondaryWeeklyIncome.getText());
				 BigDecimal value = new BigDecimal(secondaryWeeklyIncome.getText());
			} catch (NumberFormatException nfe) {
				displayAlert = true;		
			}
		}

		if (!primaryWeeklyIncome.getText().trim().equals("") && employerName.getText().trim().equals("")) {
			displayAlert = true;
			
		} else if (!secondaryWeeklyIncome.getText().trim().equals("") && primaryWeeklyIncome.getText().trim().equals("")) {
			displayAlert = true;
			
		} else if (primaryWeeklyIncome.getText().trim().equals("") && !employerName.getText().trim().equals("")) {
			displayAlert = true;
			
		}
		
		if(displayAlert){
			incomeDetails.getElement().addClassName(mandatoryContainerHighlight);
			Dialogs.alert("MyStudyLink", "Please enter primary weekly income for the employer.", null);
			return false;
		}

		return true;
	}

	private void addHandlersForTextBoxKeyPressed() {

		phoneNumber.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode()))
					((MTextBox) event.getSource()).cancelKey();

			}
		});

		mobileNumber.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (!Character.isDigit(event.getCharCode()))
					((MTextBox) event.getSource()).cancelKey();

			}
		});
	}

}
