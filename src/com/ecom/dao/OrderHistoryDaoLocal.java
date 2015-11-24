package com.ecom.dao;

import java.util.List;

import javax.ejb.Local;

import com.ecom.entities.Customer;
import com.ecom.entities.Order;
import com.ecom.entities.OrderHistory;

@Local
public interface OrderHistoryDaoLocal {

	public OrderHistory find(long id);

	public void create(OrderHistory orderH);

	public List<OrderHistory> list();

	public void remove(OrderHistory orderH);
	
	public List<OrderHistory> findHistoryByIdCustomer(long id);
	
//	public void addOrderToList(Customer customer, Order order);

}
