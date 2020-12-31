package com.service;

import java.util.List;

import com.tables.Item_Master;

public interface ShopingService {
	public int createInvoice(int userid,int len,int shopcart[][]) throws Exception;
	public void getInvoice(int invoiceno) throws Exception;
	public List<Item_Master> getAllItem();
}
