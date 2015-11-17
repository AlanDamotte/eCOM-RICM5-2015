package com.ecom.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.ecom.entities.Customer;

@Stateless(mappedName = "CustomerDao")
@EJB(name = "CustomerDao", beanInterface = CustomerDaoRemote.class)
public class CustomerDao implements CustomerDaoLocal, CustomerDaoRemote {

	private static final String ENCRYPTION = "SHA-256";

	// Injection du manager, qui s'occupe de la connexion avec la BDD
	@PersistenceContext(unitName = "ecom_PU")
	private EntityManager em;

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

	@Override
	public Customer checkPassword(String email, String password) {
		System.out.println("ON PASSE LA");
		List<Customer> customerList;
		Customer customer = null;
		boolean goodPassword = false;
		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm(ENCRYPTION);
		passwordEncryptor.setPlainDigest(false);
		try {
			TypedQuery<Customer> queryPW = em.createQuery("SELECT c FROM Customer c WHERE :email = c.email",
					Customer.class);
			customerList = queryPW.setParameter("email", email).getResultList();
			System.out.println("TOTO  = " + customerList.get(0).getFirstname() + customerList.get(1).getFirstname());
			Iterator<Customer> it = customerList.iterator();
			while (it.hasNext()) {
				customer = (Customer) it.next();
				goodPassword = passwordEncryptor.checkPassword(password, customer.getPassword());
				System.out.println(goodPassword);
			}
			if (!goodPassword) {
				return null;
			}
			return customer;
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public boolean emailAlreadyExists(String email) {
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