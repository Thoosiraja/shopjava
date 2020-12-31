package com.tables;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name = "Invoice_Master")
public class Invoice_Master {
	private int invno;
	private float total;
	private Date date;
	private User_Master user;
	private Set<Invoice_Transaction> invtranactions;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invoiceno" , unique = true)
	public int getInvno() {
		return invno;
	}
	public void setInvno(int invno) {
		this.invno = invno;
	}
	@Column(name = "total")
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date",nullable = false)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid")
	public User_Master getUser() {
		System.out.println(this.user);
		return user;
	}
	public void setUser(User_Master user) {
		System.out.println("Set"+user);
		this.user = user;
	}
	@OneToMany(fetch = FetchType.LAZY , mappedBy = "invoice",cascade = CascadeType.ALL)
	public Set<Invoice_Transaction> getInvtranactions() {
		return invtranactions;
	}
	public void setInvtranactions(Set<Invoice_Transaction> invtranactions) {
		this.invtranactions = invtranactions;
	}
	@Override
	public String toString() {
		return "Invoice_Master [invno=" + invno + ", total=" + total + ", date=" + date + ", user=" + user
				+ ", invtranactions=" + invtranactions + "]";
	}
	
}
