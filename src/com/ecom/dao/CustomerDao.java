package com.ecom.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.ecom.entities.Customer;

@Stateless
public class CustomerDao {

    // Injection du manager, qui s'occupe de la connexion avec la BDD
    @PersistenceContext( unitName = "ecom_PU")
    private EntityManager em;

    public Customer find( long id ) throws DAOException {
        try {
            return em.find( Customer.class, id );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void create( Customer customer ) throws DAOException {
        try {
            em.persist( customer );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public List<Customer> list() throws DAOException {
        try {
            TypedQuery<Customer> query = em.createQuery( "SELECT c FROM Customer c ORDER BY c.id", Customer.class );
            return query.getResultList();
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }

    public void remove( Customer customer ) throws DAOException {
        try {
            em.remove( em.merge( customer ) );
        } catch ( Exception e ) {
            throw new DAOException( e );
        }
    }
}