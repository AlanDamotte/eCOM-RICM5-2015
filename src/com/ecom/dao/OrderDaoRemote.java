package com.ecom.dao;

import java.util.List;

import javax.ejb.Remote;

import com.ecom.entities.Order;

@Remote
public interface OrderDaoRemote {

	public Order find(long id);

	public void create(Order order);

	public List<Order> list();

	public void remove(Order order);
}
