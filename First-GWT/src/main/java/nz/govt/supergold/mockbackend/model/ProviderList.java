package nz.govt.supergold.mockbackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProviderList {

	private List<ProviderDetails> providers;
	
	public ProviderList()
	{
		providers = new ArrayList<ProviderDetails>();
	}

	@XmlElement
	public List<ProviderDetails> getProviders() {
		return providers;
	}

	public void setProviders(List<ProviderDetails> providers) {
		this.providers = providers;
	}
	
	

}
