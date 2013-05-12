package com.apptech.first.client.activities;


import com.apptech.first.client.ClientFactory;
import com.apptech.first.client.places.DashboardPlace;
import com.apptech.first.client.places.PaymentDetailsPlace;
import com.apptech.first.client.views.PaymentDetailsView;
import com.apptech.first.shared.model.PaymentsModel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class PaymentDetailsActivity extends BaseActivity implements PaymentDetailsView.Presenter {

	public PaymentDetailsActivity(PaymentDetailsPlace place, ClientFactory clientFactory){
		super(clientFactory);
		this.place = place;
	}
	
	private PaymentDetailsPlace place;
	
	/**
     * Invoked by the ActivityManager to start a new Activity
     */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		final PaymentDetailsView view = clientFactory.getPaymentDetailsView();
		
		//set parameters to the view
		//set the presenter
		view.setPresenter(this);
		
		view.clear();
		
		if(place.getPaymentsModel() == null){
			//TODO: display loading .. since the data will come from the server
			
			service.getPayment(place.getId(), new AsyncCallback<PaymentsModel>() {
				@Override
				public void onSuccess(PaymentsModel model) {
					//populate screen
					view.populate(model);
				}
				@Override
				public void onFailure(Throwable caught) {
					// TODO implement error handling
				}
			});
		}
		else{
			//load with the local data
			view.populate(place.getPaymentsModel());
		}
		
		//add it to the dom
		panel.setWidget(view.asWidget());
	}	
	
	@Override
	public void goBack() {
		goTo(new DashboardPlace());
	}
}
