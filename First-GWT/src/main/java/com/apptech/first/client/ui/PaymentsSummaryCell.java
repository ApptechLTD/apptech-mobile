package com.apptech.first.client.ui;


import com.apptech.first.client.resources.icons.Icons;
import com.apptech.first.client.views.DashboardViewImpl.Css;
import com.apptech.first.shared.model.PaymentsModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;

public class PaymentsSummaryCell extends FloatingCell<PaymentsModel> {

	interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(
				"<table><tr>"
				+ " <td class='{1}'><img src='{0}'/></td>" + " <td>"
				+ "  <div class='{3}'>{2}</div>"
				+ "  <div class='{5}'>{4}</div>"
				+ "  <div class='{7}'>{6}</div>" + 
				" </td>" + 
				"</tr></table>")
		SafeHtml content(SafeUri iconImg, String iconClass, String type,
				String typeClass, String amount, String amountClass,
				String date, String dateClass);
	}

	private static Template TEMPLATE = GWT.create(Template.class);

	private Icons icons = GWT.create(Icons.class);

	private Css style;

	public void setStyle(Css style) {
		this.style = style;
	}

	@Override
	protected SafeHtml getCellContents(PaymentsModel model) {
		// format data to display
		String amountFmt = UIUtil.currencyFmt(model.getAmount());
		String dateFmt = UIUtil.shortDateFmt(model.getDate());
		// create the content using the template
		return TEMPLATE.content(icons.dashboardIconPayments().getSafeUri(),
				style.icon(), model.getType(), style.type(), amountFmt,
				style.amount(), dateFmt, style.date());
	}

	@Override
	public boolean canBeSelected(PaymentsModel model) {
		return true;
	}

	@Override
	public boolean isGroupCell(PaymentsModel model) {
		return true;
	}
}
