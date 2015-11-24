package com.ecom.dao;

import java.util.List;

import javax.ejb.Remote;

import com.ecom.entities.OrderHistory;

@Remote
public interface OrderHistoryDaoRemote {

	public OrderHistory find(long id);

	public void create(OrderHistory orderH);

	public List<OrderHistory> list();

	public void remove(OrderHistory orderH);
	
	public List<OrderHistory> findHistoryByIdCustomer(long id);
	
//	public void addOrderToList(Customer customer, Order order);

}
