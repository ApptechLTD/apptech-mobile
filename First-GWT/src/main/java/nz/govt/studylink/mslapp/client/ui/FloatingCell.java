package nz.govt.studylink.mslapp.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * 
 * 
 * @author rafaelalmeida
 * 
 * @param <T>
 */
public abstract class FloatingCell<T> implements GroupCell<T> {

	private static Template TEMPLATE = GWT.create(Template.class);

	private String styleName;

	public FloatingCell() {
		styleName = "";
	}

	public interface Template extends SafeHtmlTemplates {
		@SafeHtmlTemplates.Template("<div class=\"{0}\">{1}</div>")
		SafeHtml content(String classes, SafeHtml cellContents);

	}

	@Override
	public void render(SafeHtmlBuilder safeHtmlBuilder, final T model) {
		safeHtmlBuilder.append(TEMPLATE.content(styleName,
				getCellContents(model)));

	}

	protected abstract SafeHtml getCellContents(T model);

	public void setStylename(String name) {
		if (name == null) {
			name = "";
		}
		styleName = name;
	}
	
}
