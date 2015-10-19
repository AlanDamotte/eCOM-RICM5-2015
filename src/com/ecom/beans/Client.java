package com.ecom.beans;

public class Client {
	/* Propriétés du bean */
	private String lastname;
	private String firstname;
	private String address;
	private String phonenumber;
	private String email;
	private String image;
	

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
	
	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}
}
