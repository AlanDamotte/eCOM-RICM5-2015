package com.ecom.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.entities.Order;

@Stateless
public class OrderDao {

	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext(unitName = "ecom_PU")
	private EntityManager em;

	public Order find(long id) throws DAOException {
		try {
			return em.find(Order.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void create(Order order) throws DAOException {
		try {
			em.persist(order);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Order> list() throws DAOException {
		try {
			TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o ORDER BY o.id", Order.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void remove(Order order) throws DAOException {
		try {
			em.remove(em.merge(order));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}