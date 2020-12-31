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
@Table(name = "User_Address")
public class User_Address {
	private int aid;
	private String street;
	private String city;
	private User_Master user;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id" , unique = true)
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	@Column(name = "street" , nullable = false)
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name = "city" , nullable = false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid" , nullable = false)
	public User_Master getUser() {
		return user;
	}
	public void setUser(User_Master user) {
		this.user = user;
	}
	
}
