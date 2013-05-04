
package nz.govt.studylink.mslapp.client.ui;

import java.util.Map;

import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.events.NotificationArrivedEvent;
import nz.govt.studylink.mslapp.client.events.NotificationArrivedEventHandler;
import nz.govt.studylink.mslapp.client.events.NotificationReadEvent;
import nz.govt.studylink.mslapp.client.events.NotificationReadEventHandler;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.HeaderCss;
import com.googlecode.mgwt.ui.client.widget.base.ButtonBase;

/**
 * Menu button found in the header panel of the MyStudyLink app.
 * 
 * @author Emanuel Rabina
 */
public class HeaderMenuButton extends ButtonBase {

	private final Element innerdiv;

	interface BadgeTemplate extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template(
				"<div style='background-color: red; position: absolute; margin-top: -33px; margin-left: 17px; padding: 2px;  border-radius: 4px; padding-left: 3px; padding-right: 3px;'>{0}</div>")
		SafeHtml content(String number);
	}
	
	private static BadgeTemplate BADGE_TEMPLATE = GWT.create(BadgeTemplate.class);
	private int badgeCount = 0; // count the badge number for push notification
	/**
	 * Default constructor, create the standard menu button style.
	 */
	public HeaderMenuButton() {

		this(MGWTStyle.getTheme().getMGWTClientBundle().getHeaderCss());
	}

	/**
	 * Constructor, apply the given style to the menu button.
	 * 
	 * @param menucss
	 */
	protected HeaderMenuButton(HeaderCss menucss) {

		super(menucss);

		// Create default header button style
		innerdiv = DOM.createDiv();
		innerdiv.addClassName(menucss.headerButtonText());
		getElement().appendChild(innerdiv);
		addStyleName(menucss.headerButton());
		addStyleName(menucss.round());
		
		getElement().getStyle().setPaddingTop(5.0, Unit.PX);
		getElement().getStyle().setPaddingBottom(7.0, Unit.PX);
		getElement().getStyle().setPaddingLeft(0, Unit.PX);
		getElement().getStyle().setPaddingRight(25.0, Unit.PX);
		
		getElement().getStyle().setMarginTop(1.0, Unit.PX);
		getElement().getStyle().setMarginBottom(0, Unit.PX);
		//getElement().getStyle().setMarginLeft(5.0, Unit.PX);
		getElement().getStyle().setMarginRight(-5.0, Unit.PX);
		
		// register event handler for push notification
		configPushEventHandler();
	}

	/**
	 * Set whether this button should close the menu instead of opening it.
	 */
	// Update badge displaying for push notification
	private int getStoredUnreadNotificationCount() {
		// Load the unread message number and update the badge displaying
		int count = 0;

		String mailCountStr = First.getClientFactory().getStorage()
				.getItem("unreadmail");
		String applicationCountStr = First.getClientFactory().getStorage()
				.getItem("unreadapplication");

		if (mailCountStr != null) {
			int mailCount = Integer.valueOf(mailCountStr);
			count += mailCount;
		}

		if (applicationCountStr != null) {
			int applicationCount = Integer.valueOf(applicationCountStr);
			count += applicationCount;
		}

		return count;
	}

	/**
	 * Set the image to use in this button.
	 * 
	 * @param imageresource
	 */
	public void setImage(ImageResource imageresource) {

		Image image = new Image(imageresource);
		Element imageelement = image.getElement();
		Style imagestyle = imageelement.getStyle();
		imagestyle.setProperty("backgroundPosition", "center -3px");
		imagestyle.setProperty("backgroundSize", "20px 20px");
		imagestyle.setWidth(20, Unit.PX);
		imagestyle.setHeight(20, Unit.PX);
		innerdiv.appendChild(imageelement);
		
		// Display badge if there is any unread message
		refreshBadgeNumber();
	}
	
	Element badgerDiv = null;
	private void displayBadgeNumber(int badge){
		
		if(badge > 0){
		
			if(badgerDiv != null){
				badgerDiv.removeFromParent();
			}
			
			badgerDiv = DOM.createDiv();
			SafeHtml safeHtml = BADGE_TEMPLATE.content(String.valueOf(badge));
			badgerDiv.setInnerHTML(safeHtml.asString());
			innerdiv.appendChild(badgerDiv);
		}
		else{
			if(badgerDiv != null){
				badgerDiv.removeFromParent();
			}
		}
		
	}
	
	// Reload the stored data and refresh the badge displaying
	public void refreshBadgeNumber()
	{
		badgeCount = getStoredUnreadNotificationCount();
		displayBadgeNumber(badgeCount);
	}
	
	private void configPushEventHandler() {
		// Handle the push arrive event
		EventBus eventBus = First.getClientFactory().getEventBus();
		eventBus.addHandler(NotificationArrivedEvent.TYPE, new NotificationArrivedEventHandler() {
			@Override
			public void onNotificationArrived(NotificationArrivedEvent notificationEvent) {

				Map<String, String> notification = notificationEvent.getNotification(); 
				// Check if it is push start
				// If it is not push start, do not add badge because the page will be jump
				if ("true".equalsIgnoreCase(notification.get("pushStart"))) // start from background by push
				{
					return;
				}
				
				refreshBadgeNumber();
			}
		});
		
		// Register the notification read event handler
				eventBus.addHandler(NotificationReadEvent.TYPE, new NotificationReadEventHandler() {
					@Override
					public void onNotificationRead(NotificationReadEvent notificationReadEvent) {
						//load the data and refresh the badge icons
						refreshBadgeNumber();
					}
				});
	}
}
