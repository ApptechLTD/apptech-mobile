package nz.govt.studylink.mslapp.server.camel;

import java.util.Map;
import java.util.logging.Logger;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.component.apns.ApnsComponent;
import org.apache.camel.component.apns.ApnsEndpoint;
import org.apache.camel.util.ObjectHelper;

import com.notnoop.apns.ApnsService;

/**
 * Represents the component that manages {@link ApnsEndpoint}. It holds the list
 * of named apns endpoints.
 */
public class CustomApnsComponent extends ApnsComponent {

	private static final Logger log = Logger.getLogger(CustomApnsComponent.class.getName());
	
	private ApnsService apnsService;
    private Endpoint apnsEndpoint;
    
	public CustomApnsComponent() {
	}

	public CustomApnsComponent(ApnsService apnsService) {
		log.info("Using custom version of APNS component.");
		
		ObjectHelper.notNull(apnsService, "apnsService");
		this.apnsService = apnsService;
	}

	public CustomApnsComponent(CamelContext context) {
		super(context);
		
		log.info("Using custom version of APNS component.");
	}

	protected Endpoint createEndpoint(String uri, String remaining,
			Map<String, Object> parameters) throws Exception {
		ApnsEndpoint endpoint = new ApnsEndpoint(uri, this);
		setProperties(endpoint, parameters);
		
		apnsEndpoint = endpoint;
		
		return endpoint;
	}

	public ApnsService getApnsService() {
		return apnsService;
	}

	public void setApnsService(ApnsService apnsService) {
		this.apnsService = apnsService;
	}

	@Override
	public void stop() throws Exception {
		log.info("Stoping APNS component and endPoint !");
		
		super.stop();
		
		apnsEndpoint.stop();
		getApnsService().stop();

	}
}