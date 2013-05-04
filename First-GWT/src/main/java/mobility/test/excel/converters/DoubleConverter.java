package mobility.test.excel.converters;


public class DoubleConverter implements Converter<Double> {

	private static String trueString = "TRUE";
	private static String falseString = "FALSE";

	public Double fromString(String value) {
		if(value==null || value.equalsIgnoreCase("")) return 0.0;
		return new Double(value);
	}

	@Override
	public Double from(Object input) throws UnparsbleException {
		if (input instanceof String)
			return this.fromString((String) input);
		if (input instanceof Double)
			return (Double) input;

		throw new UnparsbleException("Unable to convert from " + input + "to Boolean");

	}

}
