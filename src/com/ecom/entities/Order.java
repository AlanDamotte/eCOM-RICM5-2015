package com.ecom.entities;

import java.io.Serializable;
import java.util.Map;

import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.Converter;
import org.joda.time.DateTime;

import com.ecom.tools.JodaDateTimeConverter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "OrderT")
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_order")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;
	@Column(columnDefinition = "TIMESTAMP")
	@Convert("dateTimeConverter")
	private DateTime date;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "payment_status")
	private String paymentStatus;
	@Column(name = "delivery_status")
	private String deliveryStatus;

	private Map<Long, Integer> cart;
	
    @OneToOne(mappedBy="order" ,cascade = CascadeType.PERSIST)
	private OrderHistory orderHistory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Map<Long, Integer> getCart() {
		return this.cart;
	}

	public void setCart(Map<Long, Integer> map) {
		this.cart = map;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
}