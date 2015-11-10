package com.ecom.dao;

import java.util.List;

import javax.ejb.Local;

import com.ecom.entities.Order;

@Local
public interface OrderDaoLocal {

	public Order find(long id);

	public void create(Order order);

	public List<Order> list();

	public void remove(Order order);
}
