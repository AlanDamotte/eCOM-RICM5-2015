package ecom.stickers.dao;

import java.util.List;

import javax.ejb.Remote;

import ecom.stickers.beans.ShoppingCart;
import ecom.stickers.entities.Order;
import ecom.stickers.entities.Product;

@Remote
public interface ProductDaoRemote {

	public Product find(long id);

	public void create(Product product);

	public List<Product> list();
	
	public List<Product> listLastProducts();

	public void remove(Product product);
	
	public List<Product> listWithTag(List<String> tags);
	
	public void updateProductQuantity(ShoppingCart shoppingCart);
	
	public boolean checkAvailability(Order order);
}
