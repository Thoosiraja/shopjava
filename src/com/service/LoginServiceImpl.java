package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.UserDAO;

@Repository
public class LoginServiceImpl implements LoginService {
	@Autowired
	UserDAO userdao;
	@Override
	public int adminlogin(String username, String password) {
		return userdao.checkAdmin(username, password);
	}

	@Override
	public int userlogin(String username, String password) {
		return userdao.checkUser(username, password);
	}
	
	@Override
	public int checkFlag(String username, String password) {
		
		return userdao.checkFlag(username, password);
	}

	@Override
	public void changeFlag(int userid) {
		userdao.changeFlag(userid);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public void show(){
		System.out.println(adminlogin("raja", "thoosi"));
	}

	@Override
	public int getUserid(String username, String password) {
		return userdao.getUserId(username, password);
	}


}
