package com.tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Invoice_Transactions")
public class Invoice_Transaction {
	private int invtransid;
	private float qty;
	private Invoice_Master invoice;
	private Item_Master item;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getInvtransid() {
		return invtransid;
	}
	public void setInvtransid(int invtransid) {
		this.invtransid = invtransid;
	}
	@Column(name = "quantity" , nullable = false)
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoiceno")
	public Invoice_Master getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice_Master invoice) {
		this.invoice = invoice;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemno")
	public Item_Master getItem() {
		return item;
	}
	public void setItem(Item_Master item) {
		this.item = item;
	}
	
	

}
