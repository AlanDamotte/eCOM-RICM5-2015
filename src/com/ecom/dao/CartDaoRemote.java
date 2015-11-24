package com.ecom.dao;

import java.util.List;

import javax.ejb.Remote;

import com.ecom.entities.Cart;

@Remote
public interface CartDaoRemote {

	public Cart find(long id);

	public void create(Cart cart);

	public List<Cart> list();
	
	public Cart findCartByIdCustomer(long id);

	public void remove(Cart cart);
	
	public void merge(Cart cart);
}