package nz.govt.studylink.mslapp.client.views;

import java.util.logging.Logger;

import nz.govt.studylink.mslapp.client.ui.AddressWidget;
import nz.govt.studylink.mslapp.client.ui.BankAccountDetailsWidget;
import nz.govt.studylink.mslapp.client.ui.UIUtil;
import nz.govt.studylink.mslapp.client.ui.mgwt.WidgetList;
import nz.govt.studylink.mslapp.shared.model.AddressModel;
import nz.govt.studylink.mslapp.shared.model.BankAccountModel;
import nz.govt.studylink.mslapp.shared.model.PersonalDetailsModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.HeaderButton;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;

public class PersonalDetailsViewImpl extends Composite implements PersonalDetailsView {

	private Logger logger = Logger.getLogger("MSLApp.PersonalDetailsViewImpl");

	private static PersonalDetailsViewImplUiBinder uiBinder = GWT.create(PersonalDetailsViewImplUiBinder.class);

	interface PersonalDetailsViewImplUiBinder extends UiBinder<Widget, PersonalDetailsViewImpl> {
	}

	private PersonalDetailsView.Presenter presenter;
	private Boolean isViewCreated = false;
	private Boolean isSecondaryIncomePanelRemoved = false;
	private Boolean isHomePhonePanelRemoved = false;
	private Boolean isMobilePhonePanelRemoved = false;
	

	@UiField
	LayoutPanel layoutPanel;

	@UiField
	ScrollPanel scrollPanel;

	@UiField
	HTML name, phoneNumber, mobileNumber, emailAddress, employerName,
			primaryWeeklyIncome, secondaryWeeklyIncome;

	@UiField
	HTMLPanel contactDetailsAddressContainer, bankAccountDetailsContainer,
			scrollContent, defaultPanel;

	@UiField
	HeaderButton editButton;

	@UiField
	AddressWidget studyAddressWidget, postalAddressWidget;

	@UiField
	HTMLPanel incomeDetails, bankAccountDetails, panelSecondaryWeeklyIncome;

	@UiField
	HTMLPanel panelHomePhone, panelMobilePhone;

	@UiField
	WidgetList widgetIncomeDetails, contactDetailsPrimaryInfo;

	public PersonalDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		logger.info("PersonalDetailsViewImpl : initWidget");
		isViewCreated = true;

	}

	/**
	 * Open the menu.
	 * 
	 * @param event
	 */
	@UiHandler("menuButton")
	public void onMenuTap(TapEvent event) {

		presenter.openMenu();
	}

	@UiHandler("editButton")
	void onEditTap(TapEvent e) {
		presenter.editPressed();
	}

	@Override
	public void populate(PersonalDetailsModel model) {

		populateContactInformation(model);
		populateBankAccountDetails(model);
		populateIncomeDetails(model);

		addDefaultPaneltoParentHTML(); // Done to remove a scrollpanel bug. This
										// could be a MGWT bug.
		/* scrollPanel.setMaxScrollY(1000); */
		scrollPanel.refresh();
		scrollPanel.scrollTo(0, 0);

	}

	@Override
	public void clear() {
		editButton.setText("Edit");

		// clear fields
		name.setText("");
		phoneNumber.setText("");
		mobileNumber.setText("");
		emailAddress.setText("");
		employerName.setText("");
		primaryWeeklyIncome.setText("");
		secondaryWeeklyIncome.setText("");

		bankAccountDetailsContainer.clear();
		postalAddressWidget.clear();
		studyAddressWidget.clear();

		// TODO: Clear all remaining fields
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	private void populateContactInformation(PersonalDetailsModel model) {
		name.setText(model.getName());
		
		if (model.getPhoneNumber() == null && !isHomePhonePanelRemoved) {
			contactDetailsPrimaryInfo.remove(panelHomePhone);
			isHomePhonePanelRemoved = true;
		}

		if (isHomePhonePanelRemoved && model.getPhoneNumber() != null) {
			contactDetailsPrimaryInfo.add(panelHomePhone);
			isHomePhonePanelRemoved = false;
		}
		
		if (model.getMobileNumber() == null && !isMobilePhonePanelRemoved) {
			contactDetailsPrimaryInfo.remove(panelMobilePhone);
			isMobilePhonePanelRemoved = true;
		}

		if (isMobilePhonePanelRemoved && model.getMobileNumber() != null) {
			contactDetailsPrimaryInfo.add(panelMobilePhone);
			isMobilePhonePanelRemoved = false;
		}
			
		phoneNumber.setText(model.getPhoneNumber());
		mobileNumber.setText(model.getMobileNumber());
		emailAddress.setText(model.getEmailAddress());

		AddressModel studyAddress = model.getStudyAddress();

		AddressModel postalAddress = model.getPostalAddress();

		studyAddressWidget.setAddressModelForView(studyAddress);
		if (postalAddress.getStreetLine1Address() != null) {
			if (!postalAddress.getStreetLine1Address().trim().equals("")) {
				postalAddressWidget.setAddressModelForView(postalAddress);
				postalAddressWidget.setVisible(true);
			} else
				postalAddressWidget.setVisible(false);
		} else
			postalAddressWidget.setVisible(false);

		studyAddressWidget.setAddressType("Study:");
		postalAddressWidget.setAddressType("Postal:");

		studyAddressWidget.getTxtBoxAddressLine1().setVisible(false);
		studyAddressWidget.getTxtBoxAddressLine2().setVisible(false);
		studyAddressWidget.getTxtBoxAddressSuburb().setVisible(false);
		studyAddressWidget.getTxtBoxAddressCity().setVisible(false);

		postalAddressWidget.getTxtBoxAddressLine1().setVisible(false);
		postalAddressWidget.getTxtBoxAddressLine2().setVisible(false);
		postalAddressWidget.getTxtBoxAddressSuburb().setVisible(false);
		postalAddressWidget.getTxtBoxAddressCity().setVisible(false);

	}

	private void populateBankAccountDetails(PersonalDetailsModel model) {

		if (model.getBankAccounts().size() == 0) {
			bankAccountDetails.setVisible(false);
		} else {
			bankAccountDetails.setVisible(true);
			for (BankAccountModel bankAccountModel : model.getBankAccounts()) {
				BankAccountDetailsWidget bankAccountDetailsWidget = new BankAccountDetailsWidget();
				bankAccountDetailsWidget.setTextLblBankAccountNumber(bankAccountModel.getBankAccountNumber());
				bankAccountDetailsWidget.setTextLblBankAccountName(bankAccountModel.getAccountHolderName());
				bankAccountDetailsWidget.populateUsedForViewContainer(bankAccountModel);
				bankAccountDetailsWidget.getTxtBoxBankAccountNumber().setVisible(false);
				bankAccountDetailsWidget.getTxtBoxBankAccountName().setVisible(false);
				bankAccountDetailsContainer.add(bankAccountDetailsWidget);
			}
		}

	}

	private void populateIncomeDetails(PersonalDetailsModel model) {

		if (model.getEmployerName() != null) {
			if (!model.getEmployerName().trim().equals(""))
				incomeDetails.setVisible(true);
			else
				incomeDetails.setVisible(false);
		} else
			incomeDetails.setVisible(false);

		employerName.setText(model.getEmployerName());
		primaryWeeklyIncome.setText(UIUtil.currencyFmt(model.getPrimaryWeeklyIncome()));
		secondaryWeeklyIncome.setText(UIUtil.currencyFmt(model.getSecondaryWeeklyIncome()));

		if (model.getSecondaryWeeklyIncome() == null && !isSecondaryIncomePanelRemoved) {
			widgetIncomeDetails.remove(panelSecondaryWeeklyIncome);
			isSecondaryIncomePanelRemoved = true;
		}

		if (isSecondaryIncomePanelRemoved && model.getSecondaryWeeklyIncome() != null) {
			widgetIncomeDetails.add(panelSecondaryWeeklyIncome);
			isSecondaryIncomePanelRemoved = false;
		}

	}

	private void addDefaultPaneltoParentHTML() {

		if (isViewCreated) {
			defaultPanel.setVisible(true);
			isViewCreated = false;
		} else
			defaultPanel.setVisible(false);
	}
}
