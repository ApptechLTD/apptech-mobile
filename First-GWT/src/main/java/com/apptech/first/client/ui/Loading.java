package com.apptech.first.client.ui;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.widget.ProgressIndicator;

public class Loading extends ComplexPanel {

	private FlowPanel indicatorContainer;

	public Loading() {
		super();
		buildIndicator();
	}

	@Override
	public void add(Widget widget) {
		add(widget, getElement());
	}

	private void buildIndicator() {
		indicatorContainer = new FlowPanel();

		indicatorContainer.getElement().getStyle().setMarginTop(20, Unit.PX);
		indicatorContainer.getElement().getStyle().setMarginBottom(20, Unit.PX);

		ProgressIndicator progressIndicator = new ProgressIndicator();
		progressIndicator.getElement().setAttribute("style",
				"margin:auto; margin-top: 5px");

		indicatorContainer.add(progressIndicator);

		setElement(indicatorContainer.getElement());
		
		//add(indicatorContainer);

		// default stop
		stop();
	}

	public void start() {
		indicatorContainer.getElement().getStyle().setDisplay(Display.BLOCK);
	}

	public void stop() {
		indicatorContainer.getElement().getStyle().setDisplay(Display.NONE);
	}

}
