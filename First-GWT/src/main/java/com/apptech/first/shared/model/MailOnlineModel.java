package com.apptech.first.shared.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.google.gwt.user.client.rpc.IsSerializable;

public class MailOnlineModel implements IsSerializable{

	private Long id;
	private String mailType;
	private String mailTitle;
	private Date mailOnlineDate;
	private String mailStatusType;
	private String mailOnlinePDFPath;
	
	private List<MailOnlineAttachmentsModel> attachments = new ArrayList<MailOnlineAttachmentsModel>();
	
	public MailOnlineModel(){
		
	}
	
	public MailOnlineModel(Long id, String mailType, String mailTitle, 
						   Date mailOnlineDate, String mailStatusType, 
						   String mailOnlinePDFPath, 
						   List<MailOnlineAttachmentsModel> attachments) {
		
		this.id = id;
		this.mailType = mailType;
		this.mailTitle = mailTitle;
		this.mailOnlineDate = mailOnlineDate;
		this.mailStatusType = mailStatusType;
		this.mailOnlinePDFPath = mailOnlinePDFPath;
		this.attachments = attachments;
	}

	public String getMailType() {
		return mailType;
	}

	public void setMailType(String mailType) {
		this.mailType = mailType;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailStatusType() {
		return mailStatusType;
	}

	public void setMailStatusType(String mailStatusType) {
		this.mailStatusType = mailStatusType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMailOnlineDate() {
		return mailOnlineDate;
	}

	public void setMailOnlineDate(Date mailOnlineDate) {
		this.mailOnlineDate = mailOnlineDate;
	}

	public String getMailOnlinePDFPath() {
		return mailOnlinePDFPath;
	}

	public void setMailOnlinePDFPath(String mailOnlinePDFPath) {
		this.mailOnlinePDFPath = mailOnlinePDFPath;
	}

	@XmlElementWrapper(name="attachmentList")
	@XmlElement
	public List<MailOnlineAttachmentsModel> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<MailOnlineAttachmentsModel> attachments) {
		this.attachments = attachments;
	}

}
