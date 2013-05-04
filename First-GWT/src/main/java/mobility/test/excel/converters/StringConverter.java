package mobility.test.excel.converters;


public class StringConverter implements Converter<String> {

	public String fromString(String value) {
		return value;
	}

	@Override
	public String from(Object input) throws UnparsbleException {
		if (input instanceof String){
			return this.fromString((String) input);
		}
		if (input != null){
			return input.toString();
		}
		return null;
	}

}
