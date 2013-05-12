package com.apptech.first.client.ui;


import com.apptech.first.client.resources.icons.Icons;
import com.apptech.first.client.views.MailOnlineDetailsViewImpl.Css;
import com.apptech.first.shared.model.MailOnlineAttachmentsModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;

public class MailOnlineAttachmentsCell extends FloatingCell<MailOnlineAttachmentsModel> {
	
	private Icons icons = GWT.create(Icons.class);

	interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(
				"<div class='{0}'>{1}</div>"
				+ "<div class='{2}'><img src='{3}'/></div>")
		SafeHtml content(String descritpionClass, String description,
						 String displayIconClass, SafeUri iconImg);
	}

	private static Template TEMPLATE = GWT.create(Template.class);

	private Css style;

	public void setStyle(Css style) {
		this.style = style;
	}

	@Override
	protected SafeHtml getCellContents(MailOnlineAttachmentsModel model) {
		// create the content using the template
		return TEMPLATE.content(style.description(), model.getAttachmentDescription(),
				style.icon(), icons.mailOnlinePDFIcon().getSafeUri());
	}

	@Override
	public boolean canBeSelected(MailOnlineAttachmentsModel model) {
		return false;
	}

	@Override
	public boolean isGroupCell(MailOnlineAttachmentsModel model) {
		return false;
	}
}
