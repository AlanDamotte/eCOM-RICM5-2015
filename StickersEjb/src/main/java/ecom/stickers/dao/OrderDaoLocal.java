package ecom.stickers.dao;

import java.util.List;

import javax.ejb.Local;

import ecom.stickers.entities.Order;

@Local
public interface OrderDaoLocal {

	public Order find(long id);

	public void create(Order order);

	public List<Order> list();

	public void remove(Order order);
}
