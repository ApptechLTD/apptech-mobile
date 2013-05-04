
package nz.govt.studylink.mslapp.client.ui;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;
import com.googlecode.mgwt.dom.client.event.touch.TouchCancelEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchEndEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchHandler;
import com.googlecode.mgwt.dom.client.event.touch.TouchMoveEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.CheckBoxCss;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

/**
 * Custom checkbox widget, is just the font's tick symbol set to be highlighted
 * or not to represent being ticked/unticked respectively.
 * 
 * @author Emanuel Rabina
 */
public class CheckBox extends TouchWidget implements HasValue<Boolean>, IsEditor<LeafValueEditor<Boolean>> {

	private boolean value;
	private CheckBoxCss css;
	private LeafValueEditor<Boolean> editor;

	/**
	 * Default constructor, takes the 'checkbox CSS' CSS and applies it to this
	 * checkbox, constructing the checkbox part of the HTML.
	 */
	public CheckBox() {

		css = MGWTStyle.getTheme().getMGWTClientBundle().getCheckBoxCss();
		css.ensureInjected();

		Element div = DOM.createDiv();
		div.setClassName(css.checkBox() + " " + css.notChecked());
		div.setInnerText("\u2713");
		setElement(div);

		addTouchHandler(new TouchHandler() {
			@Override
			public void onTouchCanceled(TouchCancelEvent event) {
			}
			@Override
			public void onTouchEnd(TouchEndEvent event) {
				/*event.stopPropagation();
				event.preventDefault();*/
				//ValueChangeEvent.fire(CheckBox.this, true);
				setValue(!getValue());
			}
			@Override
			public void onTouchMove(TouchMoveEvent event) {
			}
			@Override
			public void onTouchStart(TouchStartEvent event) {
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Boolean> handler) {

		return addHandler(handler, ValueChangeEvent.getType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LeafValueEditor<Boolean> asEditor() {

		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Boolean getValue() {

		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Boolean value) {

		setValue(value, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setValue(Boolean value, boolean fireEvents) {

		boolean oldValue = this.value;
		this.value = value;

		if (this.value && !oldValue) {
			removeStyleName(css.notChecked());
			addStyleName(css.checked());
		}
		else if (!this.value && oldValue) {
			removeStyleName(css.checked());
			addStyleName(css.notChecked());
		}

		ValueChangeEvent.fireIfNotEqual(this, oldValue, this.value);
	}
}
