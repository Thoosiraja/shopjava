package com.dao;

import java.util.List;

import com.tables.Invoice_Transaction;

public interface InvoiceTransDAO {

	public void createInvoiceEntry (Invoice_Transaction invtransaction);
	public Invoice_Transaction getInvoiceTransaction(int invoiceno ,int itemno);
	public List<Invoice_Transaction> getByInvoiceNumber(int invoicenumber );
}
