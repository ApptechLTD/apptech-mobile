package nz.govt.studylink.mslapp.client.cordova.plugins;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gwt.core.client.JavaScriptObject;
import com.googlecode.mgwt.ui.client.dialog.AlertDialog;

public abstract class BasePDFPlugin implements PDFPlugin {

	private Logger logger = Logger.getLogger("BasePDFPlugin");
	public void handleDocumentSuccess(JavaScriptObject status) {
		//goTo(new AppMenuPlace());
		logger.info("handleDocumentSuccess() ++++++");
	}
	
	public void handleDocumentFail(JavaScriptObject status) {
		//goTo(new MailOnlinePlace());
	
		Map<String, String> statusObj = new HashMap<String, String>();
		CordovaPluginUtil.parseJavascriptMap(statusObj, status);
		AlertDialog alert = new AlertDialog("Error",
				statusObj.get("status"));
		alert.show();
		logger.info("handleDocumentFail() ++++++");
		
	}

}
