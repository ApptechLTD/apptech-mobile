package com.apptech.first.client.util;


import com.apptech.first.client.First;
import com.google.gwt.storage.client.Storage;

// The data Store for each user
// Store each user's data with a key combined by user name and real key
// So it manage each user's data separately
public class MSLStorageImpl implements MSLStorage {
	Storage stockStore;
	
	public MSLStorageImpl()
	{
		stockStore = Storage.getLocalStorageIfSupported();
	}
	
	// Get data for current login user
	public boolean setItem(String key, String data)
	{
		if (stockStore == null)
		{
			return false;
		}
		
		if (First.getActiveUser() == null)
		{
			return false;
		}

		// Combine user name and real key to build the user session key
		String mslKey = First.getActiveUser().getName() + "_" + key;
		stockStore.setItem(mslKey, data);
		
		return true;
	}
	
	// Get data for current login user
	public String getItem(String key)
	{
		if (stockStore == null)
		{
			return null;
		}
		
		if (First.getActiveUser() == null)
		{
			return null;
		}
		
		// Combine user name and real key to build the user session key
		String mslKey = First.getActiveUser().getName() + "_" + key;
		return stockStore.getItem(mslKey);
	}
	
}
