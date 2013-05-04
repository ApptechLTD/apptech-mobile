package nz.govt.studylink.mslapp.shared.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="root")
public class MailList {
	
	private List<MailOnlineModel> mails;

	public MailList(){
		
	}
	
	public MailList(List<MailOnlineModel> mails){
		this.mails = mails;
	};
	
	@XmlElementWrapper(name="mailList")
	@XmlElement
	public List<MailOnlineModel> getMails() {
		return mails;
	}

	public void setMails(List<MailOnlineModel> mails) {
		this.mails = mails;
	}
	
	
}
