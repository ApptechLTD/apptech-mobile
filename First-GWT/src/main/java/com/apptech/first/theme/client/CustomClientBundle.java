
package com.apptech.first.theme.client;

import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.DataResource;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;

public interface CustomClientBundle extends MGWTClientBundle {

	@Source("com/apptech/first/client/resources/images/Background_Textured.png")
	DataResource backgroundImage();

	@Source("com/apptech/first/theme/client/css/checkbox.css")
	@Override
	CheckBoxCss getCheckBoxCss();

	@Source("com/apptech/first/client/resources/icons/MenuIcon.png")
	DataResource menuIcon();

	@Source("com/apptech/first/client/resources/images/AppLoading.gif")
	DataResource loadingImage();
}
