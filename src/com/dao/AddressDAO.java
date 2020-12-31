package com.dao;

import com.tables.User_Address;

public interface AddressDAO {
	public void addAddress(int userid,User_Address address);
	public void deleteAddress(int userid,User_Address address);
}
