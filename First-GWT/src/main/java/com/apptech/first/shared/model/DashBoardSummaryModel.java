package com.apptech.first.shared.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gwt.user.client.rpc.IsSerializable;

@XmlRootElement(name="root")
public class DashBoardSummaryModel implements IsSerializable{

	private List<PaymentsModel> payments;
	private List<ApplicationsModel> applications;

	@XmlElementWrapper(name="paymentList")
	@XmlElement
	public List<PaymentsModel> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentsModel> payments) {
		this.payments = payments;
	}

	@XmlElementWrapper(name="applicationList")
	@XmlElement
	public List<ApplicationsModel> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationsModel> applications) {
		this.applications = applications;
	}

}
