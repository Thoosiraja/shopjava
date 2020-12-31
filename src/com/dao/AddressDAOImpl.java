package com.dao;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.HibernateUtility;
import com.tables.User_Address;
import com.tables.User_Master;
@Repository
public class AddressDAOImpl implements AddressDAO {
	@Autowired
	private HibernateUtility hibernateutility;
	
	public HibernateUtility getHibernateutility() {
		return hibernateutility;
	}

	public void setHibernateutility(HibernateUtility hibernateutility) {
		this.hibernateutility = hibernateutility;
	}
	@Override
	public void addAddress(int userid, User_Address address) {
		Session session = getHibernateutility().currentSession();
		Transaction tx= session.beginTransaction();
		Criteria criteria = session.createCriteria(User_Master.class);
		criteria.add(Restrictions.eq("userid", userid));
		User_Master user = (User_Master)criteria.uniqueResult();
		Set<User_Address> addresses = user.getAddresses();
		address.setUser(user);
		addresses.add(address);
		user.setAddresses(addresses);
		try {
			session.saveOrUpdate(user);;
			session.save(address);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();  
		}
		
	}

	@Override
	public void deleteAddress(int userid, User_Address address) {
		Session session = getHibernateutility().currentSession();
		Transaction tx= session.beginTransaction();
		Criteria criteria = session.createCriteria(User_Master.class);
		criteria.add(Restrictions.eq("userid", userid));
		User_Master user = (User_Master)criteria.uniqueResult();
		Set<User_Address> addresses = user.getAddresses();
		addresses.remove(address);
		user.setAddresses(addresses);
		try {
			session.saveOrUpdate(user);;
			session.delete(address);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();  
		}	
	}

}
