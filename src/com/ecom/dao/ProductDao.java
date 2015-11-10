package com.ecom.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.entities.Product;

@Stateless(mappedName = "ProductDao")
public class ProductDao implements ProductDaoLocal{

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

	public void remove(Product product) throws DAOException {
		try {
			em.remove(em.merge(product));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}