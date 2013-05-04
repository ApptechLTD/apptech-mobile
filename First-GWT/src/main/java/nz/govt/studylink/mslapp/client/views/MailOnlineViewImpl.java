package nz.govt.studylink.mslapp.client.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.events.NotificationReadEvent;
import nz.govt.studylink.mslapp.client.ui.GroupCellList;
import nz.govt.studylink.mslapp.client.ui.Loading;
import nz.govt.studylink.mslapp.client.ui.MailOnlineSummaryCell;
import nz.govt.studylink.mslapp.shared.model.MailOnlineModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.googlecode.mgwt.ui.client.widget.celllist.CellSelectedEvent;

public class MailOnlineViewImpl extends Composite implements MailOnlineView {

	private static MailOnlineViewImplUiBinder uiBinder = GWT
			.create(MailOnlineViewImplUiBinder.class);
	
	@UiField
	Loading loadingMails;

	public interface Css extends CssResource {
		
		String displayNone();
		String icon();
		String mailTitle();
		String mailTypeAndDate();
		String unreadIcon();
	}

	interface MailOnlineViewImplUiBinder extends
			UiBinder<Widget, MailOnlineViewImpl> {
	}

	private MailOnlineView.Presenter presenter;

	@UiField
	Css style;

	@UiField(provided = true)
	GroupCellList<MailOnlineModel> mailOnlineList;

	@UiField
	ScrollPanel scrollPanel;

	@UiField
	HTMLPanel noMailOnlineMessage;

	MailOnlineSummaryCell mailOnlineSummaryCell = new MailOnlineSummaryCell();

	public MailOnlineViewImpl() {
		mailOnlineList = new GroupCellList<MailOnlineModel>(mailOnlineSummaryCell);

		initWidget(uiBinder.createAndBindUi(this));

		mailOnlineSummaryCell.setStyle(style);
	}
	
	@Override
	public void clear() {
		mailOnlineList.render(new ArrayList<MailOnlineModel>());
		scrollPanel.refresh();
		noMailOnlineMessage.getElement().getStyle().setDisplay(Display.NONE);
	}

	public void populate(List<MailOnlineModel> mailOnlineModelList) {
		
		stopLoading();
		
		mailOnlineList.render(mailOnlineModelList);

		if (mailOnlineModelList != null && mailOnlineModelList.size() > 0) {
			noMailOnlineMessage.getElement().getStyle().setDisplay(Display.NONE);
		} else {
			noMailOnlineMessage.getElement().getStyle().setDisplay(Display.BLOCK);
		}

		scrollPanel.refresh();
		
		// Check if there is any unread mail, it there is, post rean notification event
		handleNotification();
	}

	// Check if there is any unread mail, it there is, post rean notification event
	private void handleNotification() {
		// post a notification read event
		// if there is no unread count, also post a read event to trigger the menu button refresh
		// Clear the stored unread message number before trigger event
		First.getClientFactory().getStorage().setItem("unreadmail", "0");

		EventBus eventBus = First.getClientFactory().getEventBus();
		Map<String, String> notification = new HashMap<String, String>();
		notification.put("messageType", "mail"); // set the type of notification
		// Send out the push notification read event
		eventBus.fireEvent(new NotificationReadEvent(notification));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("mailOnlineList")
	void onMailSelected(CellSelectedEvent e) {
		if (presenter != null) {
			mailOnlineList.setSelectedIndex(e.getIndex(), true);
			presenter.onMailSelected(e.getIndex());
			mailOnlineList.setSelectedIndex(e.getIndex(), false);
		}
	}
	
	@Override
	public void startLoading() {
		loadingMails.start();
	}
	
	@Override
	public void stopLoading() {
		loadingMails.stop();
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
}
