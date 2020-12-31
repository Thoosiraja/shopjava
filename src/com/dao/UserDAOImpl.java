package com.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.HibernateUtility;
import com.tables.Invoice_Master;
import com.tables.User_Address;
import com.tables.User_Master;

@Repository
public class UserDAOImpl implements UserDAO{
	@Autowired
	private HibernateUtility hibernateutility;
	
	public HibernateUtility getHibernateutility() {
		return hibernateutility;
	}

	public void setHibernateutility(HibernateUtility hibernateutility) {
		System.out.println("set hibernateutility called.....");
		this.hibernateutility = hibernateutility;
	}

	@Override
	public void addUser(User_Master user) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		Set<Invoice_Master> invoices = new HashSet<Invoice_Master>();
		user.setInvoices(invoices);
		try {
			session.save(user);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			session.close();
		}
	}
	@Override
	public int getUserId(String username,String password)
	{
		Session session = getHibernateutility().currentSession();
		int useridpass = 0;
		Criteria criteria = session.createCriteria(User_Master.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("password", password));
		User_Master user = (User_Master)criteria.uniqueResult();
		if(user != null)
			useridpass = user.getUserid();
		return useridpass;
	}
	
	@Override
	public void updateUser(User_Master user) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			session.close();
		}
	}

	@Override
	public void deleteUser(User_Master user) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(user);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			session.close();
		}
	}

	@Override
	public User_Master getUser(int userid) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("userid", userid));
		User_Master user = (User_Master)crit.uniqueResult();
		return user;
	}

	@Override
	public List<User_Master> getAllUsers() {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(User_Master.class);
		return crit.list();
	}
	
	@Override
	public List<User_Master> getAllAdmins() {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("roll", "admin"));
		return crit.list();
	}
	
	@Override
	public List<User_Master> getAllCustomers() {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("roll", "user"));
		return crit.list();
	}

	@Override
	public int checkUser(String username, String password) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
		crit.add(Restrictions.eq("roll", "user"));
		User_Master user = (User_Master)crit.uniqueResult();
		if(user != null && user.getFlag() != 1)
		{
			user.setFlag(1);
			try {
				session.saveOrUpdate(user);
				tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
				session.close();
			}
			return 1;
		}
		else if(user != null && user.getFlag() == 1)
		{
			return 2;
		}
		return 0;
	}

	@Override
	public int checkAdmin(String username, String password) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
		crit.add(Restrictions.eq("roll", "admin"));
		User_Master user = (User_Master)crit.uniqueResult();
		if(user != null && user.getFlag() != 1)
		{
			user.setFlag(1);
			try {
				session.saveOrUpdate(user);
				tx.commit();
			}catch (Exception e) {
				e.printStackTrace();
				tx.rollback();
				session.close();
			}
			return 1;
		}
		else if(user != null && user.getFlag() == 1)
		{
			return 2;
		}
		return 0;
	}

	@Override
	public int checkFlag(String username, String password) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("username", username));
		crit.add(Restrictions.eq("password", password));
		User_Master user = (User_Master)crit.uniqueResult();
		if(user != null)
		{
			return user.getFlag();
		}
		return -1;
	}

	@Override
	public void changeFlag(int userid) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(User_Master.class);
		crit.add(Restrictions.eq("userid", userid));
		User_Master user = (User_Master)crit.uniqueResult();
		if(user.getFlag() == 0)
		{
			System.out.println("Flag of : " + user.getUserid() + "Flag variable : "+user.getFlag());
			user.setFlag(1);
		}
		else {
			System.out.println("Flag of : " + user.getUserid() + "Flag variable : "+user.getFlag());
			user.setFlag(0);
		}
		try {
			session.saveOrUpdate(user);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			session.close();
		}
	}
	
}
