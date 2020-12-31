package com.dao;

import java.util.List;

import com.tables.Item_Master;

public interface ItemDAO {
	public void createItem ( Item_Master item );
	public Item_Master getItemByItemNumber ( int itemnumber );
	public List<Item_Master> getAllItems();
	public void updateItem( Item_Master item );
	public void deleteItem( Item_Master item );
	public float getPrice(int itemnumber);
}
