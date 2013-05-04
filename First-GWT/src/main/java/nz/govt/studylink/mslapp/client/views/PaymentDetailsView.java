package nz.govt.studylink.mslapp.client.views;

import nz.govt.studylink.mslapp.shared.model.PaymentsModel;

import com.google.gwt.user.client.ui.IsWidget;

public interface PaymentDetailsView  extends IsWidget{

	public interface Presenter{

		void goBack();
		
	}
	
	void setPresenter(Presenter presenter);

	void populate(PaymentsModel paymentsModel);

	void clear();

}
