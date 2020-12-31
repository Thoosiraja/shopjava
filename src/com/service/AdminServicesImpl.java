package com.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dao.InvoiceDAO;
import com.dao.ItemDAO;
import com.dao.UserDAO;
import com.tables.Invoice_Master;
import com.tables.Item_Master;
import com.tables.User_Master;
@Repository
public class AdminServicesImpl implements AdminService {
	@Autowired
	ItemDAO itemdao;
	@Autowired
	InvoiceDAO invoicedao;
	@Autowired
	UserDAO userdao;
	@Override
	public void createItem(String itemdesc,String categories,String unit,float prize,byte[] image) {
		Item_Master item = new Item_Master();
		item.setImage(image);
		item.setCategories(categories);
		item.setItemdesc(itemdesc);
		item.setPrize(prize);
		item.setUnit(unit);
		itemdao.createItem(item);
		System.out.println("Item created");
	}

	@Override
	public List<Invoice_Master> getInvoicesBetweenTwoDates(Date start, Date end) {
		List<Invoice_Master> invoices =  invoicedao.getInvoicesBetweenDates(start, end);
		return invoices;
	}

	@Override
	public void getInvoice(int invoiceno) {
		invoicedao.getInvoice(invoiceno);		
	}
	
	@Override
	public List<Invoice_Master> getInvoicesOfCustomer(int userid) {
		User_Master user = userdao.getUser(userid);
		return invoicedao.getInvoiceByUserid(user);	
	}
	

}
