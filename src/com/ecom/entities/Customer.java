package com.ecom.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_customer")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Propriétés du bean */
	private Long id;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String postCode;
	
	@Column(nullable = false)
	private String city;

	private String phonenumber;

	@Column(nullable = false)
	private String email;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
}