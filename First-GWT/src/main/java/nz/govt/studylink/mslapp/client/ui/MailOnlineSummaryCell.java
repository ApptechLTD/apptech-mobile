package nz.govt.studylink.mslapp.client.ui;

import nz.govt.studylink.mslapp.client.resources.icons.Icons;
import nz.govt.studylink.mslapp.client.views.MailOnlineViewImpl.Css;
import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;

public class MailOnlineSummaryCell extends FloatingCell<MailOnlineModel> {
	
	private Icons icons = GWT.create(Icons.class);
	
	interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(
				" <div class='{6}'><img src='{7}'/></div>"
				+ " <div class='{0}'>{1}</div>"
				+ " <div class='{2}'>{3}</div>"
				+ " <div class='{4}'><img src='{5}'/></div>")
		SafeHtml content(String mailTitleClass, String mailTitle, 
				String mailTypeAndDateClass, String mailTypeDate,
				String iconClass,  SafeUri iconImg, 
				String unreadClass, SafeUri UnreadImg);
	}

	private static Template TEMPLATE = GWT.create(Template.class);

	private Css style;

	public void setStyle(Css style) {
		this.style = style;
	}

	@Override
	protected SafeHtml getCellContents(MailOnlineModel model) {
		// format data to display
		String dateFmt = UIUtil.shortDateFmt(model.getMailOnlineDate());
		
		// create the content using the template
		return TEMPLATE.content(style.mailTitle(), model.getMailTitle(),
				style.mailTypeAndDate(), model.getMailType() + ", " + dateFmt, 
				style.icon(),
				(model.getAttachments().size() > 0) ? icons.mailOnlineAttachmentIcon().getSafeUri() : icons.mailOnlinePDFIcon().getSafeUri(),
				("READ".equals(model.getMailStatusType())) ? style.displayNone() : style.unreadIcon(),
				icons.mailOnlineUnreadIcon().getSafeUri());
	}

	@Override
	public boolean canBeSelected(MailOnlineModel model) {
		return true;
	}

	@Override
	public boolean isGroupCell(MailOnlineModel model) {
		return model.getAttachments().size() > 0;
	}
}
