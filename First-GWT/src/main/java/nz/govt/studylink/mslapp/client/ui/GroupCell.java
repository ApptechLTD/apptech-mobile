package nz.govt.studylink.mslapp.client.ui;

import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

/**
 * A Cell represents a small amount of html when rendered
 * 
 * @author Julio Helden
 */

public interface GroupCell<T> extends Cell<T>{
	
	/**
	 * has this cell a group (has it more elements)? 
	 * 
	 * @param model the model of this cell
	 * @return true if the cell has more elements (more details), otherwise false
	 */
	public boolean isGroupCell(T model);

}
