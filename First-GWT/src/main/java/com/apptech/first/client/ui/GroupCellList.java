package com.apptech.first.client.ui;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.CellList;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

public class GroupCellList<T> extends CellList<T> {
	
	public GroupCellList(Cell<T> cell) {
		super(cell);
	}

	/**
	 * Render a List of models in this cell list
	 * 
	 * @param models the list of models to render
	 */
	public void render(List<T> models) {
		
		GroupCell<T> cell = (GroupCell<T>) super.cell;  

		SafeHtmlBuilder sb = new SafeHtmlBuilder();

		for (int i = 0; i < models.size(); i++) {

			T model = models.get(i);

			SafeHtmlBuilder cellBuilder = new SafeHtmlBuilder();

			String clazz = "";
			if (cell.canBeSelected(model)) {
				clazz = css.canbeSelected() + " ";
			}

			if (cell.isGroupCell(model)) {
				clazz += css.group() + " ";
			}

			if (i == 0) {
				clazz += css.first() + " ";
			}

			if (models.size() - 1 == i) {
				clazz += css.last() + " ";
			}

			cell.render(cellBuilder, model);

			sb.append(LI_TEMPLATE.li(i, clazz, cellBuilder.toSafeHtml()));
		}

		final String html = sb.toSafeHtml().asString();

		getElement().setInnerHTML(html);

		if (models.size() > 0) {
			String innerHTML = getElement().getInnerHTML();
			if ("".equals(innerHTML.trim())) {
				fixBug(html);
			}
		}
	}
}
