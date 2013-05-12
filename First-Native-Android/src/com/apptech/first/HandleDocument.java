package com.apptech.first;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;


public class HandleDocument extends CordovaPlugin{
	public interface AndroidPDFActivityHandler
	{
		void onReadPdfSuccessfully();
		void onReadPdfFail(String message);
	}
	
	public static AndroidPDFActivityHandler androidPDFActivityHandler;
	
	private Intent pdfIntent;
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		
		androidPDFActivityHandler = new AndroidPDFActivityHandler()
				{

					@Override
					public void onReadPdfSuccessfully() {
						callbackContext.success();
						
					}

					@Override
					public void onReadPdfFail(String message) {
						JSONObject status = new JSONObject();
						try {
							status.put("status", message);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						callbackContext.error(status);
					}
					
				};
		
		try {
			if ("showDocument".equals(action)) {
				JSONObject jsonObject = args.getJSONObject(0);
				String URL = (String) jsonObject.get("url");
		        this.showPDFPage(URL);
		        return true;
			}
			callbackContext.error("Invalid action");
		    return false;    
		} 
		catch (JSONException e) {
		  callbackContext.error("Invalid JSON object");
		  return true;
	    }
	}
	
	private void showPDFPage(String URL) {
		
		pdfIntent = new Intent(super.cordova.getActivity(), AndroidPDFActivity.class);
		pdfIntent.setAction(Intent.ACTION_VIEW);
		pdfIntent.setDataAndType(Uri.parse(URL), "application/pdf");
		super.cordova.getActivity().startActivity(pdfIntent);
	}

}
