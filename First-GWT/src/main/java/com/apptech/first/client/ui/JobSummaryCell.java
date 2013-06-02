package com.apptech.first.client.ui;


import com.apptech.first.client.resources.icons.Icons;
import com.apptech.first.client.views.JobListViewImpl.Css;
import com.apptech.first.shared.model.JobModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;

public class JobSummaryCell extends FloatingCell<JobModel> {
	
	private Icons icons = GWT.create(Icons.class);
	
	interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(
				" <div class='{0}'>{1}</div>"
				+ " <div class='{2}'>{3}</div>"
				+ " <div class='{4}'>{5}</div>"
				+ " <div class='{6}'>{7}</div>")
		SafeHtml content(String titleClass, String jobTitle,
				String descpritionClass, String jobDescription, 
				String addressClass, String jobAddress,
				String jobReleaseDateClass, String jobReleaseDate);
	}

	private static Template TEMPLATE = GWT.create(Template.class);

	private Css style;

	public void setStyle(Css style) {
		this.style = style;
	}

	@Override
	protected SafeHtml getCellContents(JobModel model) {
		// format data to display
		String dateFmt = UIUtil.shortDateFmt(model.getReleaseDate());
		String titleStyle = style.jobTitle();
		String descriptionStyle = style.jobDescription();
		String addressStyle = style.jobAddress();
		String dateStyle = style.jobReleaseDate();
		
		// create the content using the template
		SafeHtml sh =  TEMPLATE.content(titleStyle, model.getTitle(),
				descriptionStyle, model.getDescription(),
				addressStyle, "Wellington CBD",//model.getAddress().toString()
				dateStyle,  dateFmt);
		
//		SafeHtml sh =  TEMPLATE.content("123", "123",
//				"123", "123",
//				"123", "Wellington CBD",//model.getAddress().toString()
//				"123",  "123");
		
		return sh;
	}

	@Override
	public boolean canBeSelected(JobModel model) {
		return true;
	}

	@Override
	public boolean isGroupCell(JobModel model) {
		return false;
	}
}
