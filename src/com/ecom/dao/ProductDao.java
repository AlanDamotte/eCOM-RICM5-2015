package com.ecom.dao;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.entities.Product;

@Stateless(mappedName = "ProductDao")
@EJB(name = "ProductDao", beanInterface = ProductDaoRemote.class)
public class ProductDao implements ProductDaoLocal, ProductDaoRemote{
	
	private static final String SQL_SELECT_TAGS  = "SELECT * FROM PRODUCT WHERE id IN ((SELECT ID FROM ecom.TAGS WHERE TAGS = ?) ORDER BY id";

	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext(unitName = "ecom_PU")
	private EntityManager em;

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
	
	public List<Product> listWithTag(List<String> tags) throws DAOException {
		List<Product> searchList = new LinkedList<Product>();
		List<Product> tempList = new LinkedList<Product>();
		for (String tag : tags) {
			TypedQuery<Product> query = em.createQuery("SELECT o FROM Product o WHERE :tag IN (o.tags) ORDER BY o.id", Product.class);
			tempList = query.setParameter("tag", tag).getResultList();
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
}