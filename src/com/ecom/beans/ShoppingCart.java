package com.ecom.beans;

import javax.ejb.Remote;

import com.ecom.entities.Product;

import java.util.List;
import java.util.Map;

@Remote
public interface ShoppingCart {

	void initialize();

	void addProduct(Long id, int quantity);

	public void updateQuantity(Long id, int quantity);

	public double getTotal();

	void removeProduct(Long id, int quantity);

	List<Long> getProductsById();

	Map<Long, Product> getMapProducts();

	int getQuantity(Long id);

	public void release();
}
