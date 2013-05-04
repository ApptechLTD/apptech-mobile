package mobility.test.excel.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<Date> {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public DateConverter() {
	}

	public DateConverter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateConverter(String dateFormat) {
		this.dateFormat = new SimpleDateFormat(dateFormat);
	}

	public DateFormat getDateFormat() {
		return this.dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public Date from(Object value) throws UnparsbleException {
		try {
			if (value instanceof String){
				
				String sValue = (String)value;
				
				if(sValue.trim().length() > 0){
					return this.dateFormat.parse((String) value);
				}
			}
		} catch (final ParseException e) {
			throw new UnparsbleException(e);
		}
		return null;
	}

}
