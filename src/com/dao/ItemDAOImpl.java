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
import com.tables.Invoice_Transaction;
import com.tables.Item_Master;
@Repository
public class ItemDAOImpl implements ItemDAO {

	@Autowired
	private HibernateUtility hibernateutility;
	
	public HibernateUtility getHibernateutility() {
		return hibernateutility;
	}

	public void setHibernateutility(HibernateUtility hibernateutility) {
		this.hibernateutility = hibernateutility;
	}
	
	@Override
	public void createItem(Item_Master item) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		Set<Invoice_Transaction> invtransactions = new HashSet<Invoice_Transaction>();
		item.setInvtransactions(invtransactions);
		try {
			session.save(item);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Override
	public Item_Master getItemByItemNumber(int itemnumber) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Item_Master.class);
		crit.add(Restrictions.eq("itemno", itemnumber));
		Item_Master item = (Item_Master) crit.uniqueResult();
		return item;
	}

	@Override
	public void updateItem(Item_Master item) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(item);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Override
	public void deleteItem(Item_Master item) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.delete(item);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Override
	public List<Item_Master> getAllItems() {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Item_Master.class);
		return crit.list();
	}

	@Override
	public float getPrice(int itemnumber) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Item_Master.class);
		crit.add(Restrictions.eq("itemno", itemnumber));
		Item_Master item = (Item_Master) crit.uniqueResult();
		return item.getPrize();
	}
}
