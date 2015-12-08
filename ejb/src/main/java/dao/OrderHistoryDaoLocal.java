package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Customer;
import entities.Order;
import entities.OrderHistory;

@Local
public interface OrderHistoryDaoLocal {

	public OrderHistory find(long id);

	public long create(OrderHistory orderH);

	public List<OrderHistory> list();

	public void remove(OrderHistory orderH);
	
	public List<OrderHistory> findHistoryByIdCustomer(long id);
	
//	public void addOrderToList(Customer customer, Order order);

}
