package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Order;

@Local
public interface OrderDaoLocal {

	public Order find(long id);

	public void create(Order order);

	public List<Order> list();

	public void remove(Order order);
}
