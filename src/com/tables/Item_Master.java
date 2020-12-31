package com.tables;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Item_Master")
public class Item_Master {
	private int itemno;
	private String itemdesc;
	private String categories;
	private String unit;
	private float prize;
	private byte[] image;
	private Set<Invoice_Transaction> invtransactions;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "itemno",unique = true)
	public int getItemno() {
		return itemno;
	}
	public void setItemno(int itemno) {
		this.itemno = itemno;
	}
	@Column(name = "itemdesc" , nullable = false)
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	@Column(name = "categories" , nullable = false)
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	@Column(name = "unit" , nullable = false)
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Column(name = "prize" , nullable = false)
	public float getPrize() {
		return prize;
	}
	public void setPrize(float prize) {
		this.prize = prize;
	}
	@Column(name = "image" , nullable = false)
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "item",cascade = CascadeType.ALL)
	public Set<Invoice_Transaction> getInvtransactions() {
		return invtransactions;
	}
	public void setInvtransactions(Set<Invoice_Transaction> invtransactions) {
		this.invtransactions = invtransactions;
	}
	

	@Override
	public String toString() {
		return "Item_Master [itemno=" + itemno + ", itemdesc=" + itemdesc + ", categories=" + categories + ", unit="
				+ unit + ", prize=" + prize + "]";
	}
}
