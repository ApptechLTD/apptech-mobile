/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package nz.govt.studylink.mslapp;

import org.apache.cordova.CordovaWebViewClient;
import org.apache.cordova.DroidGap;

import android.webkit.WebView;

public class GWTCordovaWebViewClient extends CordovaWebViewClient {

	public GWTCordovaWebViewClient(DroidGap ctx) {
		super(ctx);
	}

	@Override
	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if (url.startsWith("file://")) {
			return false;
		}

		return super.shouldOverrideUrlLoading(view, url);

	}

}
