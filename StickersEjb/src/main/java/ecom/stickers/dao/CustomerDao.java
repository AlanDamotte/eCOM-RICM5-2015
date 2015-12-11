package ecom.stickers.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import ecom.stickers.entities.Customer;

@Stateless(mappedName = "CustomerDao")
public class CustomerDao implements CustomerDaoLocal, CustomerDaoRemote, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String ENCRYPTION = "SHA-256";

	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext
	private EntityManager em;
	
	public CustomerDao() {
		
	}


	public Customer find(long id) throws DAOException {
		try {
			return em.find(Customer.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void create(Customer customer) throws DAOException {
		try {
			em.persist(customer);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<Customer> list() throws DAOException {
		try {
			TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c ORDER BY c.id", Customer.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void remove(Customer customer) throws DAOException {
		try {
			em.remove(em.merge(customer));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public Customer checkPassword(String email, String password) {
		List<Customer> customerList;
		Customer customer = null;
		boolean goodPassword = false;
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ENCRYPTION);
		passwordEncryptor.setPlainDigest(false);
		try {
			TypedQuery<Customer> queryPW = em.createQuery("SELECT c FROM Customer c WHERE :email = c.email", Customer.class);
			customerList = queryPW.setParameter("email", email).getResultList();
			Iterator<Customer> it = customerList.iterator();
			while (it.hasNext()) {
				customer = (Customer) it.next();
				goodPassword = passwordEncryptor.checkPassword(password, customer.getPassword());
			}
			if (!goodPassword) {
				return null;
			}
			return customer;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	public boolean emailExists(String email) {
		try {
			TypedQuery<Customer> queryPW = em.createQuery("SELECT c FROM Customer c WHERE :email = c.email",
					Customer.class);
			List<Customer> customerList = queryPW.setParameter("email", email).getResultList();
			return (!customerList.isEmpty());
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
}