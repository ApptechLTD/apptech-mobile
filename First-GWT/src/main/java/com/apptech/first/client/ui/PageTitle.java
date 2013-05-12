package com.apptech.first.client.ui;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.ui.client.widget.touch.TouchWidget;

public class PageTitle extends TouchWidget implements HasText {

	
	public PageTitle() {
		
		setElement(DOM.createDiv());
		
		/*addTapHandler(new TapHandler() {
			@Override
			public void onTap(TapEvent event) {
				EventBus eventBus = MSLApp.getClientFactory().getEventBus();
				eventBus.fireEvent(new MenuEvent(MenuAction.DISPLAY_MENU));
			}
		});*/
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#getText()
	 */
	/** {@inheritDoc} */
	@Override
	public String getText() {
		return getElement().getInnerText();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasText#setText(java.lang.String)
	 */
	/** {@inheritDoc} */
	@Override
	public void setText(String text) {
		getElement().setInnerText(text);

	}

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.mgwt.ui.client.widget.touch.TouchWidget#addTapHandler(com.googlecode.mgwt.dom.client.event.tap.TapHandler)
	 */
	/** {@inheritDoc} */
	@Override
	public HandlerRegistration addTapHandler(TapHandler handler) {
		return super.addTapHandler(handler);
	}
}
