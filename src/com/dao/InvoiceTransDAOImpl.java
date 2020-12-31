package com.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.HibernateUtility;
import com.tables.Invoice_Transaction;
@Repository
@Controller
@RequestMapping("/inv")
public class InvoiceTransDAOImpl implements InvoiceTransDAO {
	
	@Autowired
	private HibernateUtility hibernateutility;
	
	public HibernateUtility getHibernateutility() {
		return hibernateutility;
	}

	public void setHibernateutility(HibernateUtility hibernateutility) {
		this.hibernateutility = hibernateutility;
	}

	@Override
	public void createInvoiceEntry(Invoice_Transaction invtransaction) {
		Session session = getHibernateutility().currentSession();
		Transaction tx = session.beginTransaction();
//		Criteria crit = session.createCriteria(Invoice_Master.class);
//		crit.add(Restrictions.eq("invno", invoiceno));
//		Invoice_Master invoice = (Invoice_Master) crit.uniqueResult();
//		Criteria crit1 = session.createCriteria(Item_Master.class);
//		crit.add(Restrictions.eq("itemno", itemno));
//		Item_Master item = (Item_Master) crit1.uniqueResult();
//		Set<Invoice_Transaction> invtransactions = invoice.getInvtranactions();
//		invtransactions.add(invtransaction);
//		invoice.setInvtranactions(invtransactions);
//		invtransactions = item.getInvtransactions();
//		invtransactions.add(invtransaction);
//		item.setInvtransactions(invtransactions);
//		invtransaction.setInvoice(invoice);
//		invtransaction.setItem(item);
		try {
//			session.saveOrUpdate(item);
//			session.saveOrUpdate(invoice);
			session.save(invtransaction);
			tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}

	@Override
	public Invoice_Transaction getInvoiceTransaction(int invoiceno, int itemno) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Invoice_Transaction.class);
		crit.add(Restrictions.eq("invoiceno", invoiceno));
		crit.add(Restrictions.eq("itemno", itemno));
		Invoice_Transaction transactions = (Invoice_Transaction) crit.uniqueResult();
		return transactions;
	}

	@Override
	public List<Invoice_Transaction> getByInvoiceNumber(int invoiceno) {
		Session session = getHibernateutility().currentSession();
		Criteria crit = session.createCriteria(Invoice_Transaction.class);
		crit.add(Restrictions.eq("invoiceno", invoiceno));
		List<Invoice_Transaction> transactions = crit.list();
		return transactions;
	}

	@RequestMapping(method = RequestMethod.GET)
	public void add() {
		Invoice_Transaction inv = new Invoice_Transaction();
		inv.setQty(5);
		//createInvoiceEntry(1, 1, inv);
	}
}
