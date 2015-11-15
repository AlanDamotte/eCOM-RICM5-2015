package com.ecom.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.entities.Category;

@Stateless(mappedName = "CategoryDao")
public class CategoryDao implements CategoryDaoLocal {

	@PersistenceContext(unitName = "ecom_PU")
	private EntityManager em;
	Category category;

	public Category find(long id) throws DAOException {
		try {
			return em.find(Category.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void create(Category category) throws DAOException {
		try {
			em.persist(category);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Category> list() throws DAOException {
		try {
			TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c ORDER BY c.id", Category.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void remove(Category category) throws DAOException {
		try {
			em.remove(em.merge(category));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}