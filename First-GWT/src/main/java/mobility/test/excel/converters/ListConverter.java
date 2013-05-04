package mobility.test.excel.converters;

import java.util.ArrayList;
import java.util.List;


public class ListConverter implements Converter<List<String>> {
	@Override
	public List<String> from(Object input) throws UnparsbleException {
		if (input instanceof String){
			String val = (String)input;
			String[] parts = val.split(",");
			List<String> list = new ArrayList<String>();
			for(String p : parts){
				if(p.trim().length() > 0){
					list.add(p.trim());
				}
			}
			return list;
		}
		return null;
	}
}
