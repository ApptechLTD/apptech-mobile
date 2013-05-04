package nz.govt.supergold.mockbackend.service;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import mobility.test.excel.ExcelParser;
import nz.govt.supergold.mockbackend.model.ProviderDetails;
import nz.govt.supergold.mockbackend.model.ProviderList;
import nz.govt.supergold.mockbackend.model.UserData;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

@WebService(targetNamespace = "http://nz.govt.supergold.mockbackend.service", name = "SuperGoldServiceWS")
@SOAPBinding(style = Style.RPC)
public class SuperGoldServiceImpl implements SuperGoldService {

	@WebMethod(operationName = "searchProviders")
	@Override
	public ProviderList searchProviders(@WebParam(name = "userData")UserData user) {
		// TODO Auto-generated method stub
		ProviderList providerList = new ProviderList();
		providerList.setProviders(getMockSuperGoldProviders());
		return providerList;
	}


	static List<ProviderDetails> getMockSuperGoldProviders() {
		String fileName = getFilepath("supergold.xls");
		try {
			FileInputStream fis = new FileInputStream(fileName);

			final HSSFWorkbook wb = new HSSFWorkbook(fis);

			final ExcelParser<ProviderDetails> parser = new ExcelParser<ProviderDetails>(
					1);

			final Map<Integer, String> map = new HashMap<Integer, String>();
			map.put(new Integer(1), "id");
			map.put(new Integer(2), "name");
			map.put(new Integer(3), "category");
			map.put(new Integer(4), "address");
			map.put(new Integer(5), "openHours");
			map.put(new Integer(6), "latitude");
			map.put(new Integer(7), "longitude");
			
			final List<ProviderDetails> list = parser.getObjects(
					wb.getSheet("Providers"), ProviderDetails.class, map);

			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getFilepath(String name) {
		String userHome = System.getProperty("user.home");
		String path = userHome + "/MSL-Test-Data/" + name;

		System.out.println("Excel file path: " + path);

		return path;
	}
}

