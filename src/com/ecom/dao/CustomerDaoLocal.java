package com.ecom.dao;

import java.util.List;

import javax.ejb.Local;

import com.ecom.entities.Customer;

@Local
public interface CustomerDaoLocal {
	public Customer find(long id);

	public void create(Customer customer);

	public List<Customer> list();

	public void remove(Customer customer);
}
