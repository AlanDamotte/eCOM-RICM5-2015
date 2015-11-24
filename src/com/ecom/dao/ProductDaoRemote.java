package com.ecom.dao;

import java.util.List;

import javax.ejb.Remote;

import com.ecom.beans.ShoppingCart;
import com.ecom.entities.Product;

@Remote
public interface ProductDaoRemote {

	public Product find(long id);

	public void create(Product product);

	public List<Product> list();

	public void remove(Product product);
	
	public List<Product> listWithTag(List<String> tags);
	
	public void updateProductQuantity(ShoppingCart shoppingCart);
}
