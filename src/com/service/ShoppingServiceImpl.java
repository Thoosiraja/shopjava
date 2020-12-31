package com.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dao.InvoiceDAO;
import com.dao.InvoiceTransDAO;
import com.dao.ItemDAO;
import com.dao.UserDAO;
import com.tables.Invoice_Master;
import com.tables.Invoice_Transaction;
import com.tables.Item_Master;
import com.tables.User_Master;
@Repository
public class ShoppingServiceImpl implements ShopingService {
	@Autowired
	UserDAO userdao;
	@Autowired
	ItemDAO itemdao;
	@Autowired
	InvoiceDAO invoicedao;
	@Autowired
	InvoiceTransDAO invdao;
	@Override
	public int createInvoice(int userid,int len,int shopcart[][]) throws Exception {
		float total =0.0f;
		User_Master user = userdao.getUser(userid);
		Invoice_Master invoice =new Invoice_Master();
		invoice.setDate(new Date());
		invoice.setUser(user);
		invoicedao.addInvoice(userid, invoice);
		Set<Invoice_Transaction> invoices = invoice.getInvtranactions();
		for(int i=0;i<len;i++)
		{
			if(shopcart[i][1] != 0 )
			{
				Item_Master item = itemdao.getItemByItemNumber(shopcart[i][0]);
				Invoice_Transaction inv = new Invoice_Transaction();
				inv.setInvoice(invoice);
				inv.setItem(item);
				inv.setQty(shopcart[i][1]);
				total = total +item.getPrize()*inv.getQty();
				invoices.add(inv);
				Set<Invoice_Transaction> iteminv = item.getInvtransactions();
				iteminv.add(inv);
				item.setInvtransactions(iteminv);
				invdao.createInvoiceEntry(inv);
				itemdao.updateItem(item);
			}	
		}
		invoice.setInvtranactions(invoices);
		System.out.println("Size : "+invoices.size());
		invoice.setTotal(total);
		invoicedao.updateInvoice(user, invoice);
		return invoice.getInvno();
	}
	@Override
	public void getInvoice(int invoiceno) throws Exception {
		//generatePDF(invoiceno);
	}
	@Override
	public List<Item_Master> getAllItem() {
		return itemdao.getAllItems();	
	}
}
