package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.client.ui.GroupCellList;
import nz.govt.studylink.mslapp.client.ui.MailOnlineAttachmentsCell;
import nz.govt.studylink.mslapp.client.ui.UIUtil;
import nz.govt.studylink.mslapp.shared.model.MailOnlineAttachmentsModel;
import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;
import com.googlecode.mgwt.ui.client.widget.touch.TouchDelegate;

public class MailOnlineDetailsViewImpl extends Composite implements MailOnlineDetailsView{

	private static MailOnlineDetailsViewImplUiBinder uiBinder = 
			GWT.create(MailOnlineDetailsViewImplUiBinder.class);
	
	public interface Css extends CssResource {
		
		String description();

		String icon();
		
	}
	
	interface MailOnlineDetailsViewImplUiBinder extends 
	UiBinder<Widget, MailOnlineDetailsViewImpl> {
	}
	
	private MailOnlineDetailsView.Presenter presenter;
	
	@UiField
	HTML mailTitle, mailTypeAndDate;
	
	@UiField
	HTMLPanel mailSummaryPanel;
	
	@UiField
	Css style;
	
	@UiField
	ScrollPanel scrollPanel;


	
	@UiField(provided = true)
	GroupCellList<MailOnlineAttachmentsModel> mailOnlineAttachmentsList;
	
	MailOnlineAttachmentsCell mailOnlineAttachmentsCell = new MailOnlineAttachmentsCell();
	
	public MailOnlineDetailsViewImpl() {
		
		mailOnlineAttachmentsList = new GroupCellList<MailOnlineAttachmentsModel>(mailOnlineAttachmentsCell);
		initWidget(uiBinder.createAndBindUi(this));
		
		TouchDelegate touchDelegate = new TouchDelegate(mailSummaryPanel);
        touchDelegate.addTapHandler(new TapHandler() {
			
			@Override
			public void onTap(TapEvent event) {
				// TODO Auto-generated method stub
				presenter.onMainMailSelected();
			}
		});
        
		mailOnlineAttachmentsCell.setStyle(style);
	}
	
	@UiHandler("backButton")
	void onBackTap(TapEvent e){
		presenter.goBack();
	}
	
	@Override
	public void populate(MailOnlineModel model) {
		mailTitle.setHTML(model.getMailTitle());
		mailTypeAndDate.setHTML(model.getMailType() + ", " + UIUtil.shortDateFmt(model.getMailOnlineDate()));
		mailOnlineAttachmentsList.render(model.getAttachments());

		scrollPanel.refresh();
	}
	
	@UiHandler("mailOnlineAttachmentsList")
	void onMailAttachmentsSelected(CellSelectedEvent e) {
		if (presenter != null) {
			mailOnlineAttachmentsList.setSelectedIndex(e.getIndex(), true);
			presenter.onMailAttachmentsSelected(e.getIndex());
			mailOnlineAttachmentsList.setSelectedIndex(e.getIndex(), false);
		}
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
}