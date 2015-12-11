package ecom.stickers.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import ecom.stickers.beans.ShoppingCart;
import ecom.stickers.entities.Order;
import ecom.stickers.entities.Product;

@Stateless(mappedName = "ProductDao")
public class ProductDao implements ProductDaoLocal, ProductDaoRemote, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext
	private EntityManager em;
	

	public ProductDao() {
		
	}

	public Product find(long id) throws DAOException {
		try {
			return em.find(Product.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void create(Product product) throws DAOException {
		try {
			em.persist(product);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Product> list() throws DAOException {
		try {
			TypedQuery<Product> query = em.createQuery("SELECT o FROM Product o ORDER BY o.id", Product.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<Product> listLastProducts() throws DAOException {
		try {
			TypedQuery<Product> query2 = em.createQuery("SELECT o FROM Product o ORDER BY o.id DESC", Product.class);
			return query2.setMaxResults(10).getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public List<Product> listWithTag(List<String> tags) throws DAOException {
		List<Product> searchList = new LinkedList<Product>();
		List<Product> tempList = new LinkedList<Product>();
		for (String tag : tags) {
			TypedQuery<Product> query3 = em.createQuery("SELECT o FROM Product o WHERE :tag IN (o.tags) ORDER BY o.id", Product.class);
			tempList = query3.setParameter("tag", tag).getResultList();
			for (Product product : tempList) {
				if(!searchList.contains(product)){
					searchList.add(product);
				}
			}
		}
		return searchList;
		
	}

	public void remove(Product product) throws DAOException {
		try {
			em.remove(em.merge(product));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void updateProductQuantity(ShoppingCart shoppingCart) {
		Map<Long, Integer> mapProducts = shoppingCart.getCart();
		Set<Long> keys = mapProducts.keySet();
		Iterator<Long> it = keys.iterator();
		while (it.hasNext()) {
			Long key = (Long) it.next();
			Product product = this.find(key);
			product.setQuantity(product.getQuantity() - mapProducts.get(key));
			em.merge(product);	
		}
	}

	public boolean checkAvailability(Order order) {
		Map<Product,Integer> cart = order.getCart();
		Set<Product> keys = cart.keySet();
		Iterator<Product> it = keys.iterator();
		while (it.hasNext()) {
			Product product = (Product) it.next();
			Product bdd_product = this.find(product.getId());
			if(cart.get(product)>bdd_product.getQuantity()){
				return false;
			}
		}
		return true;
	}
}