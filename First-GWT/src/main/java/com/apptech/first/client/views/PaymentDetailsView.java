package com.apptech.first.client.views;


import com.apptech.first.shared.model.PaymentsModel;
import com.google.gwt.user.client.ui.IsWidget;

public interface PaymentDetailsView  extends IsWidget{

	public interface Presenter{

		void goBack();
		
	}
	
	void setPresenter(Presenter presenter);

	void populate(PaymentsModel paymentsModel);

	void clear();

}
