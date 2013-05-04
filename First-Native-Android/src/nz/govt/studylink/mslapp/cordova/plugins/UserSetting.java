package nz.govt.studylink.mslapp.cordova.plugins;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserSetting extends CordovaPlugin{

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("read")) {
            this.read(callbackContext);
            return true;
        }
        return false;
    }

    private void read(CallbackContext callbackContext) throws JSONException
    {
    	 // Create the default host url
         JSONObject setting = new JSONObject();
         setting.put("serverUrl", "");
         callbackContext.success(setting);
    }
}
