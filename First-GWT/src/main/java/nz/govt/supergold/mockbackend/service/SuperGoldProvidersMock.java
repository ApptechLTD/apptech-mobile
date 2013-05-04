package nz.govt.supergold.mockbackend.service;

import nz.govt.supergold.mockbackend.model.ProviderDetails;
import nz.govt.supergold.mockbackend.model.ProviderList;

public class SuperGoldProvidersMock {

	public static ProviderList getTestProviderList()
	{
		ProviderList providers = new ProviderList();
		
		ProviderDetails p1 = new ProviderDetails();
		p1.setName("provider 1");
		p1.setAddress("address 1");
		p1.setCategory("food");
		p1.setLatitude(17.90223423);
		p1.setLongitude(17.9023452354);
		providers.getProviders().add(p1);
		
		p1 = new ProviderDetails();
		p1.setName("provider 2");
		p1.setAddress("address 2");
		p1.setCategory("cloth");
		p1.setLatitude(17.90223123);
		p1.setLongitude(17.90234521123);
		providers.getProviders().add(p1);
		
		p1 = new ProviderDetails();
		p1.setName("provider 3");
		p1.setAddress("address 3");
		p1.setCategory("food");
		p1.setLatitude(17.90223423);
		p1.setLongitude(17.9023452354);
		providers.getProviders().add(p1);
		
		p1 = new ProviderDetails();
		p1.setName("provider 4");
		p1.setAddress("address 4");
		p1.setCategory("food");
		p1.setLatitude(17.90223423);
		p1.setLongitude(17.9023452123);
		providers.getProviders().add(p1);
		
		p1 = new ProviderDetails();
		p1.setName("provider 5");
		p1.setAddress("address 5");
		p1.setCategory("food");
		p1.setLatitude(17.902231234);
		p1.setLongitude(17.9023452354);
		providers.getProviders().add(p1);
		
		p1 = new ProviderDetails();
		p1.setName("provider 6");
		p1.setAddress("address 6");
		p1.setCategory("food");
		p1.setLatitude(17.90223654);
		p1.setLongitude(17.90234527894);
		providers.getProviders().add(p1);
		
		p1 = new ProviderDetails();
		p1.setName("provider 7");
		p1.setAddress("address 7");
		p1.setCategory("food");
		p1.setLatitude(17.902237890);
		p1.setLongitude(17.90234234);
		providers.getProviders().add(p1);

		
		return providers;
	}

}
