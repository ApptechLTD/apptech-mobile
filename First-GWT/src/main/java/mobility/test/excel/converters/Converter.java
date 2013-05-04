package mobility.test.excel.converters;


public interface Converter<T> {

	T from(Object input) throws UnparsbleException;
}
