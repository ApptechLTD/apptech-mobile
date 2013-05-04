
package nz.govt.studylink.mslapp.client.resources.icons;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

/**
 * Client bundle containing the custom icons we use throughout the app.
 */
public interface Icons extends ClientBundle {

	@Source("DashboardIcon_Application.png")
	ImageResource dashboardIconApplications();

	@Source("DashboardIcon_Payment.png")
	ImageResource dashboardIconPayments();

	@Source("MailOnline_Attachment.png")
	ImageResource mailOnlineAttachmentIcon();
	
	@Source("MailOnline_Unread.png")
	ImageResource mailOnlineUnreadIcon();

	@Source("MailOnline_PDF.png")
	ImageResource mailOnlinePDFIcon();

	@Source("menuButtonNotification.png")
	ImageResource menuButtonNotification();

	@Source("MenuIcon.png")
	ImageResource menuIcon();

	@Source("MenuIcon_Dashboard.png")
	ImageResource menuIconDashboard();

	@Source("MenuIcon_MailOnline.png")
	ImageResource menuIconMailOnline();

	@Source("MenuIcon_PersonalDetails.png")
	ImageResource menuIconPersonalDetails();

	@Source("minusButton.png")
	ImageResource minusButton();
}
