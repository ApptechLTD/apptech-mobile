
package nz.govt.studylink.mslapp.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class ImageButton extends TouchWidget {

	ImageResource image;
	SimplePanel panel;
	Image img;
	private int width;
	private int height;

	public ImageButton() {
		panel = new SimplePanel();
		setElement(panel.getElement());
	}

	public ImageResource getImage() {
		return image;
	}

	public void setImage(ImageResource image) {
		this.image = image;
		img = new Image(image);
		setImageWidth();
		setImageHeight();
		panel.add(img);
	}


	private void setBackgroundSize() {
		img.getElement().getStyle().setProperty("backgroundSize", width + "px " + height + "px");
	}

	private void setImageHeight() {
		if (img != null && height != 0) {
			img.getElement().getStyle().setHeight(height, Unit.PX);
			setBackgroundSize();
		}
	}

	public void setImageHeight(int height) {
		this.height = height;
		setImageHeight();
	}

	private void setImageWidth() {
		if (img != null && width != 0) {
			img.getElement().getStyle().setWidth(width, Unit.PX);
			setBackgroundSize();
		}
	}

	public void setImageWidth(int width) {
		this.width = width;
		setImageWidth();
	}
}
