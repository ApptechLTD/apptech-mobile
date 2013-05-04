package nz.govt.studylink.mslapp.client.cordova.plugins;

public class UserSettingPluginCordovaImpl extends UserSettingPluginBase {

	@Override
	protected void readUserSetting()
	{
		logger.info("UserSettingPluginCordovaImpl.readUserSetting invoked");
		readNativeUserSetting();
	}
	
	private native void readNativeUserSetting()/*-{
		var that = this;
		var cordova = $wnd.cordova || $wnd.Cordova || $wnd.PhoneGap;
		cordova.exec(
						function(data) {
							that.@nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPluginCordovaImpl::readSettingSuccess(Lcom/google/gwt/core/client/JavaScriptObject;)(data);
						},
						function(message) {
							that.@nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPluginCordovaImpl::readSettingFail(Lcom/google/gwt/core/client/JavaScriptObject;)(status);
						}, 
						"UserSetting", 
						"read", 
						[]);

	}-*/;

}
