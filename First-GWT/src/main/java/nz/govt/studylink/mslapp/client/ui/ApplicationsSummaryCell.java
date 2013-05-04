package nz.govt.studylink.mslapp.client.ui;

import nz.govt.studylink.mslapp.client.resources.icons.Icons;
import nz.govt.studylink.mslapp.client.views.DashboardViewImpl.Css;
import nz.govt.studylink.mslapp.shared.model.ApplicationsModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeUri;

public class ApplicationsSummaryCell extends FloatingCell<ApplicationsModel> {

	interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(
				"<table><tr>" +
				" <td class='{1}'><img src='{0}'/></td>" +
				" <td>" +
				"  <div class='{3}'>{2}</div>" +
				"  <div class='{5}'>{4}</div>" +
				"  <div class='{7}'>{6}</div>" +
				"  <div class='{9}'>{8}</div>" +
				" </td>" +
				"</tr></table>")
		SafeHtml content(
				SafeUri iconImg, String iconClass,
				String type, String typeClass, 
				String provider, String providerClass, 
				String program, String programClass, 
				String status, String statusClass );
	}
	private static Template TEMPLATE = GWT.create(Template.class);
	
	Css style;
	
	private Icons icons = GWT.create(Icons.class);
	
	@Override
	protected SafeHtml getCellContents(ApplicationsModel model) {
		
		//style the status accordingly
		String statusStyle = "";
		if("Pending".equals(model.getStatus())){
			statusStyle = style.pending();
		}
		if("Approved".equals(model.getStatus())){
			statusStyle = style.aproved();
		}
		if("Declined".equals(model.getStatus())){
			statusStyle = style.declined();
		}
		
		String provider ="";
		String program ="";
		if(model.getProvider() !=null){
			provider = model.getProvider();
		}
		if(model.getProgram() !=null){
			program = model.getProgram();
		}
		
		//create the content using the template
		return TEMPLATE.content(
				icons.dashboardIconApplications().getSafeUri(), style.icon(),
				model.getType(), style.type(),
				provider, style.provider(),
				program, style.program(),
				model.getStatus(), statusStyle);
	}
	

	@Override
	public boolean canBeSelected(ApplicationsModel model) {
		return false;
	}


	public void setStyle(Css style) {
		this.style = style;
	}


	@Override
	public boolean isGroupCell(ApplicationsModel model) {
		return true;
	}
}
