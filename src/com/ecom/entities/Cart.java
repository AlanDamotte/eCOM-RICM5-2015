package com.ecom.entities;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/* Propriétés du bean */
	private Integer id;

	@OneToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;

	private Map<Long, Integer> cart;

	private double totalPrice;

	public void setId(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public Map<Long, Integer> getCart() {
		return this.cart;
	}

	public void setCart(Map<Long, Integer> cart) {
		this.cart = cart;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
