package mobility.test.excel;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobility.test.excel.converters.Converter;
import mobility.test.excel.converters.UnparsbleException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelParser<T> {
	private final static Logger logger = Logger.getLogger(ExcelParser.class);
	private int skipNoOfRows = 0;
	private final Map<String, Converter> converterMap = new HashMap<String, Converter>();

	public ExcelParser(int skipNoOfRows) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.skipNoOfRows = skipNoOfRows;
		this.init();

	}

	public void registerConverter(String type, Converter objInstance) throws InstantiationException, IllegalAccessException {
		this.converterMap.put(type, objInstance);

	}

	@SuppressWarnings("unchecked")
	private void init() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<Converter> obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.IntegerConverter");
		Converter objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Integer", objInstance);

		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.FloatConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Float", objInstance);

		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.BooleanConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Boolean", objInstance);

		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.DateConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.util.Date", objInstance);

		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.StringConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.String", objInstance);

		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.DoubleConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Double", objInstance);
		
		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.LongConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.lang.Long", objInstance);
		
		obj = (Class<Converter>) Class.forName("mobility.test.excel.converters.ListConverter");
		objInstance = obj.newInstance();
		this.converterMap.put("java.util.List", objInstance);
		
	}

	public List<T> getObjects(HSSFSheet sheet, Class<T> className, Map<Integer, String> map) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {
		final List<T> listObjects = new ArrayList<T>();
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			logger.debug("Processing row" + i);
			if (i < this.skipNoOfRows) continue;
			final T obj = this.createObject(className, sheet.getRow(i), map);
			listObjects.add(obj);
		}
		return listObjects;
	}

	@SuppressWarnings("unchecked")
	private T createObject(Class<T> className, HSSFRow row, Map<Integer, String> map) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException, UnparsbleException {

		final Class<T> obj = (Class<T>) Class.forName(className.getName());
		final T objInstance = obj.newInstance();
		logger.debug("Setting properties for class" + className.getName());
		if (row == null) return null;
		for (int i = 0; i < row.getPhysicalNumberOfCells() + 5; i++) {
			logger.debug("Processing cell" + i);

			final String propertyName = map.get(new Integer(i + 1));
			logger.debug("Processing cell propertyName" + propertyName);
			final HSSFCell cell = row.getCell(i);
			if (cell == null) continue;
			if (propertyName == null || propertyName.trim().equalsIgnoreCase("")) {
				continue;
			}
			Object value = null;
			final Object cellValue = this.getCellValue(cell);
			if (cellValue == null) continue;
			logger.debug("Getting descriptor for property propertyName" + propertyName);
			final PropertyDescriptor descriptor = PropertyUtils.getPropertyDescriptor(objInstance, propertyName);
			if (descriptor == null) {
				throw new UnparsbleException("Check if property" + propertyName + "exists");
			}

			final Class<?> type = descriptor.getPropertyType();
			final Converter<?> converter = this.converterMap.get(type.getName());
			if (converter == null) throw new UnparsbleException("No Converters for" + type.getName() + "exists");

			try {
				value = converter.from(cellValue);
			} catch (final Exception e) {
				logger.error(e);
				value = null;
			}
			PropertyUtils.setSimpleProperty(objInstance, propertyName, value);
		}
		return objInstance;
	}

	private Object getCellValue(HSSFCell cell) {
		if (cell == null) return null;
		if (cell.getCellType() == Cell.CELL_TYPE_BLANK) return "";
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) return cell.getNumericCellValue();
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) return cell.getStringCellValue().replaceAll("'", "");
		return null;
	}
}
