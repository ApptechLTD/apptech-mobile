package nz.govt.studylink.mslapp.client.cordova.plugins;


public class PDFPluginCordovaImpl extends BasePDFPlugin {

	public native void showDocument(String URL)/*-{
	
	var that = this;
	var cordova = $wnd.cordova || $wnd.Cordova || $wnd.PhoneGap;
	
	cordova.exec(function (status) {
			that.@nz.govt.studylink.mslapp.client.cordova.plugins.PDFPluginCordovaImpl::handleDocumentSuccess(Lcom/google/gwt/core/client/JavaScriptObject;)(status);
		}, 
		function (status) {
			that.@nz.govt.studylink.mslapp.client.cordova.plugins.PDFPluginCordovaImpl::handleDocumentFail(Lcom/google/gwt/core/client/JavaScriptObject;)(status);
		}, "HandleDocument", "showDocument", [{"url":URL}]);
	
	}-*/;
	
}
