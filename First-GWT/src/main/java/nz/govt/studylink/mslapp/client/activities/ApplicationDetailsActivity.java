package nz.govt.studylink.mslapp.client.activities;

import java.util.logging.Level;
import java.util.logging.Logger;

import nz.govt.studylink.mslapp.client.ClientFactory;
import nz.govt.studylink.mslapp.client.First;
import nz.govt.studylink.mslapp.client.places.ApplicationDetailsPlace;
import nz.govt.studylink.mslapp.client.places.DashboardPlace;
import nz.govt.studylink.mslapp.client.views.ApplicationDetailsView;
import nz.govt.studylink.mslapp.shared.model.ApplicationsModel;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;

public class ApplicationDetailsActivity extends BaseActivity implements ApplicationDetailsView.Presenter {

	Logger logger = Logger.getLogger("MSLApp.ApplicationDetailsActivity");
	
	public ApplicationDetailsActivity(ApplicationDetailsPlace place, ClientFactory clientFactory){
		super(clientFactory);
		this.place = place;
	}
	private static AlertDialog alert;
	private ApplicationDetailsPlace place;

	/**
     * Invoked by the ActivityManager to start a new Activity
     */
	@Override
	protected void startImpl(AcceptsOneWidget panel, EventBus eventBus) {
		final ApplicationDetailsView view = clientFactory.getApplicationDetailsView();
		
		//set parameters to the view
		//set the presenter
		view.setPresenter(this);
		
		view.clear();
		
		if(place.getApplicationsModel() == null){
			//TODO: display loading
			service.getApplications(First.getActiveUser().getAuthToken(), place.getId(), new AsyncCallback<ApplicationsModel>() {
				@Override
				public void onSuccess(ApplicationsModel model) {
					if(model == null){
						//no application found
						//go to dashboard
						goBack();
						
						// cambirra!!! Because the method is invoked for 2 times, please remove this as soon as we find out the reason
						if (alert == null)
						{
							alert = new AlertDialog("Application Not Found",
									"Application Details not found.");
						}
						alert.show();
						
					}
					else{
						view.populate(model);
					}
				}
				@Override
				public void onFailure(Throwable caught) {
					logger.log(Level.SEVERE, "Could not display application. applicationID: " + place.getId(), caught);
					
					AlertDialog alert = new AlertDialog("Error", "Could not display application : " + caught.getMessage());
					alert.show();
				}
			});
		}
		else{
			//load with the local data
			view.populate(place.getApplicationsModel());
		}
		//add it to the dom
		panel.setWidget(view.asWidget());
	}	
	
	@Override
	public void goBack() {
		goTo(new DashboardPlace());
	}
}
