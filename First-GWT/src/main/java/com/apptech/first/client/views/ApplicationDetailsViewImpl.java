package com.apptech.first.client.views;


import com.apptech.first.client.ui.PageTitle;
import com.apptech.first.client.ui.UIUtil;
import com.apptech.first.client.ui.mgwt.WidgetList;
import com.apptech.first.shared.model.ApplicationsModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;

public class ApplicationDetailsViewImpl extends Composite implements ApplicationDetailsView{

	private static final String LOAN_LIVING_COSTS = "Loan Living Costs";

	private static final String STUDENT_ALLOWANCE = "Student Allowance";

	private static final String STUDENT_LOAN = "Student Loan";

	private static final String APPROVED = "Approved";
	
	private static final String PENDING = "Pending";
	
	private static final String DECLINED = "Declined";

	private static ApplicationDetailsViewImplUiBinder uiBinder = GWT.create(ApplicationDetailsViewImplUiBinder.class);

	interface ApplicationDetailsViewImplUiBinder extends UiBinder<Widget, ApplicationDetailsViewImpl> {
	}

	private ApplicationDetailsView.Presenter presenter;
	
	@UiField
	HTML status, received, livingCostsStartDate, 
		livingCostsPaymentAmount, 
		stydyDetailsProvider, stydyDetailsCampus,
		stydyDetailsProgram, stydyDetailsStartDate, 
		stydyDetailsEndDate, stydyDetailsStudentID, 
		pendingStatementPanel, declinedStatement, declinedReason,
		week200Bar, week200BarText;
	
	@UiField
	HTMLPanel assistanceContainer, livingCostsContainer, 
	studyDetailsContainer, declinedStatementPanel, week200Panel;
	
	@UiField
	PageTitle type;
	
	@UiField
	HorizontalPanel assistancePanel;
	
	@UiField
	WidgetList applicationsDetailsList;
	
	boolean displayLivingDetails = false;
	
	public ApplicationDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("backButton")
	void onBackTap(TapEvent e){
		presenter.goBack();
	}
	@Override
	public void populate(ApplicationsModel model) {
		type.setText(model.getType());
		status.setHTML(model.getStatus());
		
		String receivedFmt = UIUtil.shortDateFmt(model.getDateReceived());
		received.setHTML(receivedFmt);
		
		if(model.getListOfAssistanceReceived() != null && model.getListOfAssistanceReceived().size() > 0){
			//We always remove the assistancePanel and add again to make sure we don't end up with empty rows
			applicationsDetailsList.remove(assistancePanel);
			applicationsDetailsList.add(assistancePanel);
			
			displayLivingDetails = false;
			
			for(String item : model.getListOfAssistanceReceived()){
				HTML assistance = new HTML(item);
				assistanceContainer.add(assistance);
				if(LOAN_LIVING_COSTS.equals(item)){
					displayLivingDetails = true;
				}
			}
		}
		else{
			//remove when there is no assistance to be displayed
			applicationsDetailsList.remove(assistancePanel);
		}
		
		//display or hide Living Costs
		if(STUDENT_LOAN.equals(model.getType()) && APPROVED.equals(model.getStatus()) && displayLivingDetails){
			//display
			livingCostsContainer.getElement().getStyle().setDisplay(Display.BLOCK);
			
			//populate
			if(model.getLivingCostsStartDate() != null){
				String lcStartDate = UIUtil.shortDateFmt(model.getLivingCostsStartDate());
				livingCostsStartDate.setHTML(lcStartDate);
			}
			
			if(model.getLivingCostsAmount() != null){
				String lcPaymentAmount = UIUtil.currencyFmt(model.getLivingCostsAmount());
				livingCostsPaymentAmount.setHTML(lcPaymentAmount);
			}
			
		}
		else{
			//hide
			livingCostsContainer.getElement().getStyle().setDisplay(Display.NONE);
		}
		
		//If it is a Student Allowance and is Aproved -> Display the 200 Week bar
		if(STUDENT_ALLOWANCE.equals(model.getType()) && APPROVED.equals(model.getStatus()) && model.getWeekEntitlement() != null){
			applicationsDetailsList.remove(week200Panel);
			applicationsDetailsList.add(week200Panel);
			
			double perc = model.getWeekEntitlement() / 2;
			week200Bar.getElement().getStyle().setWidth(perc, Unit.PCT);
			
			week200BarText.setHTML(model.getWeekEntitlement() + "/200 Weeks");
			
		}
		else{
			applicationsDetailsList.remove(week200Panel);
		}
		
		
		if(APPROVED.equals(model.getStatus())){
			studyDetailsContainer.getElement().getStyle().setDisplay(Display.BLOCK);
			//populate Study Details
			stydyDetailsProvider.setHTML(model.getProvider());
			stydyDetailsCampus.setHTML(model.getCampus());
			stydyDetailsProgram.setHTML(model.getProgram());
			stydyDetailsStartDate.setHTML(UIUtil.shortDateFmt(model.getStartDate()));
			stydyDetailsEndDate.setHTML(UIUtil.shortDateFmt(model.getEndDate()));
			stydyDetailsStudentID.setHTML(model.getStudentID());
		}
		else{
			studyDetailsContainer.getElement().getStyle().setDisplay(Display.NONE);
		}
		
		if(PENDING.equals(model.getStatus())){
			pendingStatementPanel.setHTML("Your " + model.getType() + " application is now being processed by our staff.");
			pendingStatementPanel.getElement().getStyle().setDisplay(Display.BLOCK);
		}
		else{
			pendingStatementPanel.getElement().getStyle().setDisplay(Display.NONE);
		}
		
		if(DECLINED.equals(model.getStatus())){
			declinedStatementPanel.getElement().getStyle().setDisplay(Display.BLOCK);
			declinedStatement.setHTML("Your " + model.getType() + " application was declined for the following reason:");
			declinedReason.setHTML(SafeHtmlUtils.fromString(model.getDeclinedReason()));
		}
		else{
			declinedStatementPanel.getElement().getStyle().setDisplay(Display.NONE);
		}
		
	}
	
	public void clear() {
		type.setText("");
		status.setHTML("");
		received.setHTML("");
		assistanceContainer.getElement().setInnerHTML("");
		
		livingCostsStartDate.setText("");
		livingCostsPaymentAmount.setText("");
		
		stydyDetailsProvider.setHTML("");
		stydyDetailsCampus.setHTML("");
		stydyDetailsProgram.setHTML("");
		stydyDetailsStartDate.setHTML("");
		stydyDetailsEndDate.setHTML("");
		stydyDetailsStudentID.setHTML("");
		
		pendingStatementPanel.setHTML("");
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
