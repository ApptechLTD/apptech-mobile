package com.apptech.first.client.ui;



import com.apptech.first.shared.model.AddressModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.MTextBox;
import com.googlecode.mgwt.ui.client.widget.WidgetList;

public class AddressWidget extends Composite {

	private static AddressWidgetUiBinder uiBinder = GWT
			.create(AddressWidgetUiBinder.class);

	interface AddressWidgetUiBinder extends UiBinder<Widget, AddressWidget> {
	}
	
	interface PageStyle extends CssResource {
		String mandatoryContainerHighlightIOS();
		String mandatoryContainerHighlightAndroid();
	}

	String mandatoryContainerHighlight = "";
	String mandatoryContainerHighlightClassName = "";

	@UiField
	Label lblAddressLine1, lblAddressLine2, lblAddressSuburb, lblAddressCity;
	
	@UiField
	MTextBox txtBoxAddressLine1, txtBoxAddressLine2, txtBoxAddressSuburb, txtBoxAddressCity;
	
	@UiField
	HTML addressType;
	
	@UiField
	PageStyle style;
	
	@UiField
	WidgetList addressWidget;
	
	/*@UiField
	HTMLPanel mandatoryPanel;*/

	public AddressWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void setAddressModelForView(AddressModel addressModel) {
		lblAddressLine1.setText(addressModel.getStreetLine1Address());
		lblAddressLine2.setText(addressModel.getStreetLine2Address());
		lblAddressSuburb.setText(addressModel.getSuburb());
		lblAddressCity.setText(addressModel.getCity());
	}
	
	public void setAddressModelForUpdate(AddressModel addressModel) {
		txtBoxAddressLine1.setText(addressModel.getStreetLine1Address());
		txtBoxAddressLine2.setText(addressModel.getStreetLine2Address());
		txtBoxAddressSuburb.setText(addressModel.getSuburb());
		txtBoxAddressCity.setText(addressModel.getCity());
	}
	
	public void setAddressType(String type){
		addressType.setText(type);
	}
	
	public void setTextTxtBoxAddressLine1(String text){
		txtBoxAddressLine1.setText(text);
	}
	
	public void setTextTxtBoxAddressLine2(String text){
		txtBoxAddressLine2.setText(text);
	}
	
	public void setTextTxtBoxAddressSuburb(String text){
		txtBoxAddressSuburb.setText(text);
	}
	
	public void setTextTxtBoxAddressCity(String text){
		txtBoxAddressCity.setText(text);
	}

	public MTextBox getTxtBoxAddressLine1() {
		return txtBoxAddressLine1;
	}

	public MTextBox getTxtBoxAddressLine2() {
		return txtBoxAddressLine2;
	}

	public MTextBox getTxtBoxAddressSuburb() {
		return txtBoxAddressSuburb;
	}

	public MTextBox getTxtBoxAddressCity() {
		return txtBoxAddressCity;
	}
	
	public HTML getAddressType(){
		return addressType;
	}
	/*public HTMLPanel getMandatoryPanel() {
		return mandatoryPanel;
	}*/
	
	public void clear(){
		lblAddressLine1.setText("");
		lblAddressLine2.setText("");
		lblAddressSuburb.setText("");
		lblAddressCity.setText("");
		
		txtBoxAddressLine1.setText("");
		txtBoxAddressLine2.setText("");
		txtBoxAddressSuburb.setText("");
		txtBoxAddressCity.setText("");
	}
	
	public void hightLightAddressWidget(Boolean bool){
		
		if(MGWT.getOsDetection().isAndroid()){
			mandatoryContainerHighlight = style.mandatoryContainerHighlightAndroid();
			//mandatoryContainerHighlightClassName = "mandatoryContainerHighlightAndroid";
		}
		else if (MGWT.getOsDetection().isIOs()){
			mandatoryContainerHighlight = style.mandatoryContainerHighlightIOS();
			//mandatoryContainerHighlightClassName = "mandatoryContainerHighlightIOS";
		}
		
		if (bool)
		
			addressWidget.getElement().addClassName(mandatoryContainerHighlight);
		else
			addressWidget.getElement().removeClassName(mandatoryContainerHighlight);
		
	}
	
}
