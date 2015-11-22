package com.ecom.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.beans.ShoppingCart;
import com.ecom.entities.Cart;
import com.ecom.entities.Product;

@Stateless(mappedName = "CartDao")
@Remote(CartDaoRemote.class)
@Local(CartDaoLocal.class)
public class CartDao implements CartDaoLocal, CartDaoRemote, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public void merge(Cart cart) throws DAOException {
		try {
			em.merge(cart);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Cart findCartByIdCustomer(long id) {
		List<Cart> tempList = new LinkedList<Cart>();
		try {
			TypedQuery<Cart> query = em.createQuery("SELECT o FROM Cart o WHERE :id = o.id", Cart.class);
			tempList = query.setParameter("id", id).getResultList();
			for (Cart cart : tempList) {
				return cart;
			}
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return null;
	}
}
