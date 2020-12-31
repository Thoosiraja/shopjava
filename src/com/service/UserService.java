package com.service;

public interface UserService {
	public void createUser(String username,String password,String roll,String city,String street);
	public void createAddress(int userid,String city,String street);
	public void readUserByUid(int userid);
}
