package com.ecom.beans;

import javax.ejb.Local;
import com.ecom.entities.Product;
import java.util.List;
import java.util.Map;

@Local
public interface ShoppingCartLocal extends ShoppingCart {
	
	void initialize();

	void addProduct(Product product, int quantity);

	void removeProduct(Product product, int quantity);

	public void updateQuantity(Product product, int quantity);

	public double getTotal();

	Map<Long, Product> getMapProducts();

	List<Product> getProducts();

	public void release();
}
