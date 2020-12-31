package com.dao;

import java.util.Date;
import java.util.List;

import com.tables.Invoice_Master;
import com.tables.User_Master;

public interface InvoiceDAO {
	public void addInvoice(int userid,Invoice_Master invoice);
	public void updateInvoice(User_Master user,Invoice_Master invoice);
	public Invoice_Master getInvoice(int invoiceno);
	public List<Invoice_Master> getAllInvoices();
	public List<Invoice_Master> getInvoicesBetweenDates(Date start, Date end);
	public void deleteInvoiceByInvoiceNumber(int invoiceno);
	public List<Invoice_Master> getInvoiceByUserid(User_Master user);
}
