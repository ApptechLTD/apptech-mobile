package mobility.test.excel.converters;

import java.text.ParseException;

public class UnparsbleException extends Exception {

	public UnparsbleException(String message) {
		super(message);
	}

	public UnparsbleException(ParseException e) {
		super(e);
	}

}
