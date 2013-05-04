package nz.govt.supergold.mockbackend.service;

import nz.govt.supergold.mockbackend.model.ProviderList;
import nz.govt.supergold.mockbackend.model.UserData;

public interface SuperGoldService {
	ProviderList searchProviders(UserData user);
}
