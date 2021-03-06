package ecom.stickers.beans;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import ecom.stickers.entities.Product;

@Remote
public interface ShoppingCart {

	void initialize();

	void addProduct(Long id, int quantity);

	public void updateQuantity(Long id, int quantity);

	public double getTotal();

	void removeProduct(Long id, int quantity);

	public List<Long> getProductsById();

	public Map<Long, Product> getMapProducts();
	
	public Map<Product, Integer> getProductsMap();

	int getQuantity(Long id);

	void setId(Long id);

	public Long getId();

	public void initializeClientCart();

	public void release();
	
	public void mergeClientCart();
	
	public Map<Long, Integer> getCart();
	
	public void clear();
	
	public void saveCart();

}
