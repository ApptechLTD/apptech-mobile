package com.apptech.first.shared.model;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class AddressModel implements IsSerializable{

	private Date startDate;
	private Date endDate;
	private String streetLine1Address;
	private String streetLine2Address;
	private String suburb;
	private String city;
	private String postCode;
	
	public String getStreetLine1Address() {
		return streetLine1Address;
	}
	public void setStreetLine1Address(String streetLine1Address) {
		this.streetLine1Address = streetLine1Address;
	}
	public String getStreetLine2Address() {
		return streetLine2Address;
	}
	public void setStreetLine2Address(String streetLine2Address) {
		this.streetLine2Address = streetLine2Address;
	}
	public String getSuburb() {
		return suburb;
	}
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
