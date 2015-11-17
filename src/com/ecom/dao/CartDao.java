package com.ecom.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.beans.ShoppingCart;
import com.ecom.entities.Cart;

@Stateless(mappedName = "CartDao")
@EJB(name = "CartDao", beanInterface = CartDaoRemote.class)
public class CartDao implements CartDaoLocal, CartDaoRemote{
	
	@PersistenceContext(unitName = "ecom_PU")
	private EntityManager em;

	public Cart find(long id) throws DAOException {
		try {
			return em.find(Cart.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void create(Cart cart) throws DAOException {
		try {
			em.persist(cart);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Cart> list() throws DAOException {
		try {
			TypedQuery<Cart> query = em.createQuery("SELECT o FROM Cart o ORDER BY o.id", Cart.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void remove(Cart cart) throws DAOException {
		try {
			em.remove(em.merge(cart));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
