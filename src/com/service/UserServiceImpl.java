package com.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dao.AddressDAO;
import com.dao.UserDAO;
import com.tables.User_Address;
import com.tables.User_Master;
@Repository
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userdao;
	@Autowired
	AddressDAO addressdao;
	@Override
	public void createUser(String username, String password, String roll,String city,String street) {
		User_Master user = new User_Master();
		user.setFlag(0);
		user.setUsername(username);
		user.setPassword(password);
		user.setRoll(roll);
		User_Address address = new User_Address();
		address.setCity(city);
		address.setStreet(street);
		address.setUser(user);
		Set<User_Address> addresses = new HashSet<User_Address>();
		addresses.add(address);
		user.setAddresses(addresses);
		userdao.addUser(user);
		System.out.println("User created");
	}

	@Override
	public void createAddress(int userid, String city, String street) {
		User_Address address = new User_Address();
		address.setCity(city);
		address.setStreet(street);
		addressdao.addAddress(userid, address);
		System.out.println("Address Added");
	}

	@Override
	public void readUserByUid(int userid) {
		userdao.getUser(userid);
	}
	
}
