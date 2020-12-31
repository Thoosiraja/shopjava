package com.dao;

import java.util.Date;
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
import com.tables.Invoice_Transaction;
import com.tables.User_Master;
@Repository
public class InvoiceDAOImpl implements InvoiceDAO {
	
	@Autowired
	private HibernateUtility hibernateutility;
	
	public HibernateUtility getHibernateutility() {
		return hibernateutility;
	}

	public void setHibernateutility(HibernateUtility hibernateutility) {
		this.hibernateutility = hibernateutility;
	}
	
	
	@Override
	public void addInvoice(int userid,Invoice_Master invoice) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User_Master.class);
		criteria.add(Restrictions.eq("userid", userid));
		User_Master user = (User_Master) criteria.uniqueResult();
		Set<Invoice_Master> invoices = user.getInvoices();
		Set<Invoice_Transaction> invtransactions = new HashSet<Invoice_Transaction>();
		invoice.setInvtranactions(invtransactions);
		invoice.setUser(user);
		invoices.add(invoice);
		user.setInvoices(invoices);
		try {
			session.saveOrUpdate(user);
			session.saveOrUpdate(invoice);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}	
	}
	@Override
	public void updateInvoice(User_Master user,Invoice_Master invoice) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.saveOrUpdate(user);
			session.saveOrUpdate(invoice);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}	
	}

	@Override
	public Invoice_Master getInvoice(int invoiceno) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Invoice_Master.class);
		crit.add(Restrictions.eq("invno", invoiceno));
		Invoice_Master invoices = (Invoice_Master) crit.uniqueResult();
		return invoices;
	}

	@Override
	public List<Invoice_Master> getAllInvoices() {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Invoice_Master.class);
		List<Invoice_Master> invoices = crit.list();
		return invoices;
	}

	@Override
	public List<Invoice_Master> getInvoicesBetweenDates(Date start, Date end) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Invoice_Master.class);
		crit.add(Restrictions.between("date", start, end));
		List<Invoice_Master> invoices = crit.list();
		return invoices;
	}

	@Override
	public void deleteInvoiceByInvoiceNumber(int invoiceno) {
		Session session = getHibernateutility().currentSession();
		Transaction tx= session.beginTransaction();
		Criteria criteria = session.createCriteria(Invoice_Master.class);
		criteria.add(Restrictions.eq("invno", invoiceno));
		Invoice_Master invoice = (Invoice_Master)criteria.uniqueResult();
		User_Master user = invoice.getUser();
		Set<Invoice_Master> invoices = user.getInvoices();
		invoices.remove(invoice);
		user.setInvoices(invoices);
		try {
			session.saveOrUpdate(user);;
			session.delete(invoice);
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			tx.rollback();  
		}
	}

	@Override
	public List<Invoice_Master> getInvoiceByUserid(User_Master user) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Invoice_Master.class);
		crit.add(Restrictions.eq("user", user));
		List<Invoice_Master> invoices = crit.list();
		return invoices;
	}
}
