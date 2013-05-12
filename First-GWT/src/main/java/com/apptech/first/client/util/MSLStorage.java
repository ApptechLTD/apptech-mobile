package com.apptech.first.client.util;

public interface MSLStorage {
	// Get data for current login user
	boolean setItem(String key, String data);
	
	// Get data for current login user
    String getItem(String key);
}
