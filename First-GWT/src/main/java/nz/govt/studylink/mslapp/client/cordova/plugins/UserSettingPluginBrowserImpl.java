package nz.govt.studylink.mslapp.client.cordova.plugins;


public class UserSettingPluginBrowserImpl extends UserSettingPluginBase {

	@Override
	protected void readUserSetting()
	{
		logger.info("UserSettingPluginBrowserImpl.readUserSetting invoked");
		readNativeUserSetting();
	}
	
	private  native void readNativeUserSetting()/*-{
		
		var data = {"serverUrl" : "http://192.168.1.108:8080"};
		
		this.@nz.govt.studylink.mslapp.client.cordova.plugins.UserSettingPluginBrowserImpl::readSettingSuccess(Lcom/google/gwt/core/client/JavaScriptObject;)(data);

	}-*/;
}
