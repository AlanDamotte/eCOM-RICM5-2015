package com.ecom.beans;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.ejb.Stateful;

import com.ecom.dao.CartDaoLocal;
import com.ecom.dao.CustomerDaoLocal;
import com.ecom.dao.ProductDaoLocal;
import com.ecom.entities.Cart;
import com.ecom.entities.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Stateful(mappedName = "ShoppingCartBean")
@EJB(name = "ShoppingCartBean", beanInterface = ShoppingCart.class)
public class ShoppingCartBean implements ShoppingCartLocal, ShoppingCart {

	private Map<Long, Integer> items = new HashMap<Long, Integer>();
	private boolean initialised = false;
	private double total;
	
	@EJB 
	ProductDaoLocal productDao;
	
	@EJB 
	CartDaoLocal cartDao;
	
	@EJB 
	CustomerDaoLocal customerDao;

	@Override
	public void initialize() {
		initialised = true;
	}
	
	@Override
	public double getTotal(){
		return this.total;
	}

	@Override
	public void addProduct(Product product, int quantity) {
		if (items.isEmpty()){
			this.total = 0;
		}
		int oldQuantity = getQuantity(product.getId());
		items.put(product.getId(), oldQuantity + quantity);
		double subtotal = this.total;
		subtotal = subtotal + product.getPrice() * quantity;
		this.total = subtotal;
	}

	@Override
	public void addProduct(Long id, int quantity) {
		if (items.isEmpty()){
			this.total = 0;
		}
		int oldQuantity = getQuantity(id);
		items.put(id, oldQuantity + quantity);
		double subtotal = this.total;
		subtotal = subtotal + productDao.find(id).getPrice() * quantity;
		this.total = subtotal;
	}
	
	@Override
	public void updateQuantity(Long id, int quantity){
		double subtotal1 = productDao.find(id).getPrice() * getQuantity(id);
		double subtotal = this.total - subtotal1;
		subtotal = subtotal + productDao.find(id).getPrice() * quantity;
		items.replace(id, quantity);
		this.total = subtotal;
	}

	@Override
	public void removeProduct(Product product, int quantity) {
		int initialQuantity = getQuantity(product.getId());
		if(initialQuantity - quantity > 0){
			items.replace(product.getId(), initialQuantity - quantity);
		}
		else{
			items.remove(product.getId());
		}
		double subtotal = this.total;
		subtotal = subtotal - product.getPrice() * quantity;
		this.total=subtotal;
	}

	@Override
	public void removeProduct(Long id, int quantity) {
		int initialQuantity = getQuantity(id);		
		if(initialQuantity - quantity > 0){
			items.replace(id, initialQuantity - quantity);
		}else{
			items.remove(id);
		}
		double subtotal = this.total;
		subtotal = subtotal - productDao.find(id).getPrice() * quantity;
		this.total=subtotal;
	}

	@Override
	public List<Long> getProductsById() {
		List<Long> list = new ArrayList<Long>(items.keySet());
		return list;
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> list = null ;
		Set<Long> keys = items.keySet();
		Iterator<Long> it = keys.iterator();
		while (it.hasNext()){
		   Long key = (Long) it.next();
		   list.add(productDao.find(key));
		}
		return list;
	}

	/*@Override
	public int getQuantity(Product product) {
		int quantity = 0;
		if (items.containsKey(product.getId())){
			quantity = items.get(product.getId());
		}
		return quantity;
	}*/

	@Override
	public int getQuantity(Long id) {
		int quantity = 0;
		if (items.containsKey(id)){
			quantity = items.get(id);
		}
		return quantity;
	}

	@Remove
	public void release() {
		items.clear();
	}

	@Override
	public Map<Long, Product> getMapProducts() {
		Map<Long, Product> cart = new HashMap<Long, Product>();
		Set<Long> keys = items.keySet();
		Iterator<Long> it = keys.iterator();
		while (it.hasNext()){
		   Long key = (Long) it.next();
		   cart.put(key, productDao.find(key));
		}
		return cart;
	}

	@Override
	public void updateQuantity(Product product, int quantity) {
		double subtotal1 = product.getPrice() * getQuantity(product.getId());
		double subtotal = this.total - subtotal1;
		subtotal = subtotal + product.getPrice() * quantity;
		items.replace(product.getId(), quantity);
		this.total = subtotal;		
	}
	
	//Méthode de sauvegarde du panier associé à l'utilisateur
	@Remove
	public void saveCart(){
		//TODO
//		Cart cart = new Cart();
//		cart.setCustomer(customerDao.find(customerId));
//		cart.setTotalPrice(total);
//		cart.setCart(items);
//		cartDao.create(cart);
	}
}
