package mobility.test.excel.converters;


public class LongConverter implements Converter<Long> {

	public Long fromString(String value) {
		if (value == null || value.length() == 0)
			return 0L;
		return Long.parseLong(value);
	}

	@Override
	public Long from(Object input) throws UnparsbleException {
		if (input instanceof String){
			return this.fromString((String) input);
		}
		else if (input instanceof Double){
			return ((Double)input).longValue();
		}
		return null;
	}

}
