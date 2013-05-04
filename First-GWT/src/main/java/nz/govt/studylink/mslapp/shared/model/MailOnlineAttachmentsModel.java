package nz.govt.studylink.mslapp.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MailOnlineAttachmentsModel implements IsSerializable{

	private String attachmentDescription;
	private String attachmentPDFPath;
	
	public MailOnlineAttachmentsModel(){
		
	}
	
	public MailOnlineAttachmentsModel(String  attachmentDescription, String attachmentPDFPath) {
		
		this.attachmentDescription = attachmentDescription;
		this.attachmentPDFPath = attachmentPDFPath;
	}

	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

	public String getAttachmentPDFPath() {
		return attachmentPDFPath;
	}

	public void setAttachmentPDFPath(String attachmentPDFPath) {
		this.attachmentPDFPath = attachmentPDFPath;
	}

}
