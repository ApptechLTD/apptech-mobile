
package nz.govt.studylink.mslapp.client.ui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * Generates the HTML to house a menu button image and text.
 * 
 * @author Emanuel Rabina
 */
public class MenuButton extends TouchWidget {

	private HTMLPanel panel;
	private ImageResource image;
	private String label;

	/**
	 * Constructor, builds the HTML code.
	 */
	public MenuButton() {

		panel = new HTMLPanel("");
		panel.getElement().getStyle().setMarginTop(100, Unit.PCT);
		setElement(panel.getElement());
	}

	public ImageResource getImage() {
		return image;
	}

	public String getLabel() {
		return label;
	}

	/**
	 * Sets the image to use in this menu button.
	 * 
	 * @param image
	 */
	public void setImage(ImageResource image) {

		this.image = image;

		Image imageHtml = new Image(image.getSafeUri());
		Style imageStyle = imageHtml.getElement().getStyle();
		imageStyle.setDisplay(Display.BLOCK);
		imageStyle.setProperty("margin", "1.25em auto 0");
		imageStyle.setProperty("maxWidth", "60%");
		imageStyle.setPosition(Position.ABSOLUTE);
		imageStyle.setTop(0, Unit.PX);
		imageStyle.setRight(0, Unit.PX);
		imageStyle.setBottom(0, Unit.PX);
		imageStyle.setLeft(0, Unit.PX);

		panel.add(imageHtml);
	}

	/**
	 * Sets the title to display with this menu button.
	 * 
	 * @param label
	 */
	public void setLabel(String label) {

		this.label = label;

		HTMLPanel labelHtml = new HTMLPanel(label);
		Style labelStyle = labelHtml.getElement().getStyle();
		labelStyle.setColor("white");
		labelStyle.setFontSize(0.8, Unit.EM);
		labelStyle.setFontWeight(FontWeight.BOLD);
		labelStyle.setPosition(Position.ABSOLUTE);
		labelStyle.setTextAlign(TextAlign.CENTER);
		labelStyle.setTop(75, Unit.PCT);
		labelStyle.setWidth(100, Unit.PCT);

		panel.add(labelHtml);
	}
}
