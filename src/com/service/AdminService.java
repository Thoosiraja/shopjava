package com.service;

import java.util.Date;
import java.util.List;

import com.tables.Invoice_Master;

public interface AdminService {
	public List<Invoice_Master> getInvoicesBetweenTwoDates(Date start, Date end);
	public List<Invoice_Master> getInvoicesOfCustomer(int userid);
	public void getInvoice(int invoiceno);
	public void createItem(String itemdesc, String categories, String unit, float prize, byte[] image);
}
