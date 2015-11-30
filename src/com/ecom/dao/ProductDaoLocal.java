package com.ecom.dao;

import java.util.List;

import javax.ejb.Local;

import com.ecom.beans.ShoppingCart;
import com.ecom.entities.Order;
import com.ecom.entities.Product;

@Local
public interface ProductDaoLocal {

	public Product find(long id);

	public void create(Product product);

	public List<Product> list();
	
	public List<Product> listLastProducts();

	public void remove(Product product);
	
	public List<Product> listWithTag(List<String> tags);
	
	public void updateProductQuantity(ShoppingCart shoppingCart);
	
	public boolean checkAvailability(Order order);
}
