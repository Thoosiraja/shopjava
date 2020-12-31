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
@Table(name = "User_Master")
public class User_Master {
	private int userid;
	private String username;
	private String password;
	private String roll;
	private int flag;
	private Set<User_Address> addresses;
	private Set<Invoice_Master> invoices;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid",unique = true,nullable = false)
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	@Column(name = "username",nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "password",nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "flag",nullable = false)
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	@Column(name = "roll",nullable = false)
	public String getRoll() {
		return roll;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
	public Set<User_Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(Set<User_Address> addresses) {
		this.addresses = addresses;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user",cascade = CascadeType.ALL)
	public Set<Invoice_Master> getInvoices() {
		return invoices;
	}
	public void setInvoices(Set<Invoice_Master> invoices) {
		this.invoices = invoices;
	}
	
}
