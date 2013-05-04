
package nz.govt.studylink.mslapp.theme.client;

import com.google.gwt.resources.client.ClientBundle.Source;
import com.google.gwt.resources.client.DataResource;
import com.googlecode.mgwt.ui.client.theme.MGWTClientBundle;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;

public interface CustomClientBundle extends MGWTClientBundle {

	@Source("nz/govt/studylink/mslapp/client/resources/images/Background_Textured.png")
	DataResource backgroundImage();

	@Source("nz/govt/studylink/mslapp/theme/client/css/checkbox.css")
	@Override
	CheckBoxCss getCheckBoxCss();

	@Source("nz/govt/studylink/mslapp/client/resources/icons/MenuIcon.png")
	DataResource menuIcon();

	@Source("nz/govt/studylink/mslapp/client/resources/images/AppLoading.gif")
	DataResource loadingImage();
}
