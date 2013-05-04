
package nz.govt.studylink.mslapp;

import org.apache.cordova.CordovaChromeClient;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.DroidGap;

import android.os.Bundle;

public class MainActivity extends DroidGap {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setIntegerProperty("loadUrlTimeoutValue", 300000);
		super.loadUrl("file:///android_asset/www/MSLApp.html");
	}
	
	@Override
	public void init() {
	    super.init(new CordovaWebView(this), new GWTCordovaWebViewClient(this), new CordovaChromeClient(this));
	    System.out.println("MSLApp initiated!");
	}

}
