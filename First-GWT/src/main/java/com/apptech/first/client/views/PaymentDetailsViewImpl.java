package com.apptech.first.client.views;


import com.apptech.first.client.ui.PageTitle;
import com.apptech.first.client.ui.UIUtil;
import com.apptech.first.shared.model.PaymentsModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;

public class PaymentDetailsViewImpl extends Composite implements
		PaymentDetailsView {

	private static PaymentDetailsViewImplUiBinder uiBinder = GWT
			.create(PaymentDetailsViewImplUiBinder.class);

	interface PaymentDetailsViewImplUiBinder extends
			UiBinder<Widget, PaymentDetailsViewImpl> {
	}

	private PaymentDetailsView.Presenter presenter;

	@UiField
	HTML bankAccount, amount, date;

	@UiField
	PageTitle pageTitle;

	public PaymentDetailsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));

	}

	@UiHandler("backButton")
	void onBackTap(TapEvent e) {
		presenter.goBack();
	}

	@Override
	public void populate(PaymentsModel model) {
		pageTitle.setText(model.getType());
		amount.setHTML(UIUtil.currencyFmt(model.getAmount()));
		date.setHTML(UIUtil.shortDateFmt(model.getDate()));
		bankAccount.setHTML(model.getBanckAccount());
	}

	@Override
	public void clear() {
		pageTitle.setText("");
		bankAccount.setHTML("");
		amount.setHTML("");
		date.setHTML("");
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
