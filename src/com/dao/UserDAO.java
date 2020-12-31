package com.dao;

import java.util.List;

import com.tables.User_Master;

public interface UserDAO {
	public void addUser(User_Master user);
	public int checkUser(String username,String password);
	public int checkAdmin(String username,String password);
	public void updateUser(User_Master user);
	public void deleteUser(User_Master user);
	public User_Master getUser(int userid);
	public List<User_Master> getAllUsers();
	public int getUserId(String username,String password);
	public List<User_Master> getAllAdmins();
	public List<User_Master> getAllCustomers();
	public int checkFlag(String username,String password);
	public void changeFlag(int userid);
}

