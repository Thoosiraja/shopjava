package com.service;

public interface LoginService {
	public int adminlogin(String username,String password);
	public int userlogin(String username,String password);
	public int getUserid(String username,String password);
	public int checkFlag(String username,String password);
	public void changeFlag(int userid);
}
