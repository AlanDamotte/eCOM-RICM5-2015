package beans;

import javax.ejb.Local;
import entities.Product;
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
	
	public Map<Product, Integer> getProductsMap();

	List<Product> getProducts();

	void setId(Long id);

	public Long getId();

	public void initializeClientCart();

	public void mergeClientCart();

	public Map<Long, Integer> getCart();

	public void release();

	public void clear();

	public void saveCart();
}
