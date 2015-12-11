package ecom.stickers.dao;

import java.util.List;

import javax.ejb.Local;

import ecom.stickers.entities.Cart;

@Local
public interface CartDaoLocal {

	public Cart find(long id);

	public void create(Cart cart);

	public List<Cart> list();
	
	public Cart findCartByIdCustomer(long id);

	public void remove(Cart cart);
	
	public void merge(Cart cart);
}
