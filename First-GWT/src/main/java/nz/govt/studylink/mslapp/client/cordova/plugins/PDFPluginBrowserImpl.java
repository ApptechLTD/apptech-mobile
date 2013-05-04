package nz.govt.studylink.mslapp.client.cordova.plugins;

import java.util.logging.Logger;


public class PDFPluginBrowserImpl extends BasePDFPlugin {
	
	private Logger logger = Logger.getLogger(PDFPluginBrowserImpl.class.getName());

	public void showDocument(String URL){
		
		logger.info("show PDF document on browser == > " + URL);
	}
	
}
