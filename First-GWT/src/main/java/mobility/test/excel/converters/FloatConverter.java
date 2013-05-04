package mobility.test.excel.converters;


public class FloatConverter implements Converter<Float> {

	public Float fromString(String value) {
		if (value == null || value.length() == 0)
			return 0F;
		if (value.indexOf(",") != -1)
			return Float.parseFloat(value.replace(",", "."));
		else
			return Float.parseFloat(value);
	}

	@Override
	public Float from(Object input) throws UnparsbleException {
		if (input instanceof String)
			return this.fromString((String) input);
		return null;
	}

}
