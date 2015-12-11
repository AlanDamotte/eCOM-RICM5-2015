package ecom.stickers.dao;

import java.util.List;

import javax.ejb.Remote;

import ecom.stickers.entities.Order;

@Remote
public interface OrderDaoRemote {

	public Order find(long id);

	public void create(Order order);

	public List<Order> list();

	public void remove(Order order);
}
