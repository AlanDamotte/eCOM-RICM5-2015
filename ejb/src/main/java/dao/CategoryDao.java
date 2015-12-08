package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.awt.Dimension;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.Product;

@Stateless(mappedName = "CategoryDao")
@Remote(CategoryDaoRemote.class)
@Local(CategoryDaoLocal.class)
public class CategoryDao implements CategoryDaoLocal, CategoryDaoRemote, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public Map<String,List<Dimension>> getCategories(){
		Map<String,List<Dimension>> mapCategories = new HashMap<String,List<Dimension>>();
		try {
			TypedQuery<String> query = em.createQuery("SELECT DISTINCT c.shape FROM Category c", String.class);
			List<String> listShape = query.getResultList();
			for (String shape : listShape) {
				List<Dimension> listDim = new LinkedList();
				TypedQuery<Dimension> query2 = em.createQuery("SELECT o.dimension FROM Category o WHERE :shape = o.shape", Dimension.class);
				listDim = query2.setParameter("shape", shape).getResultList();
				mapCategories.put(shape, listDim);
			}
			return mapCategories;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}
