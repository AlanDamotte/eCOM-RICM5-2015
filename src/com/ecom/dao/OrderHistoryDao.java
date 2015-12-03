package com.ecom.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.ecom.entities.Cart;
import com.ecom.entities.Customer;
import com.ecom.entities.Order;
import com.ecom.entities.OrderHistory;

@Stateless(mappedName = "OrderHistoryDao")
@Remote(OrderHistoryDaoRemote.class)
@Local(OrderHistoryDaoLocal.class)
public class OrderHistoryDao implements OrderHistoryDaoLocal, OrderHistoryDaoRemote, Serializable {

	@PersistenceContext(unitName = "ecom_PU")
	private EntityManager em;

	public OrderHistory find(long id) throws DAOException {
		try {
			return em.find(OrderHistory.class, id);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public long create(OrderHistory orderH) throws DAOException {
		try {
			em.persist(orderH);
			em.flush();
			return orderH.getOrder().getId();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<OrderHistory> list() throws DAOException {
		try {
			TypedQuery<OrderHistory> query = em.createQuery("SELECT o FROM OrderHistory o ORDER BY o.id",
					OrderHistory.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void remove(OrderHistory orderH) throws DAOException {
		try {
			em.remove(em.merge(orderH));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public List<OrderHistory> findHistoryByIdCustomer(long id) {
		List<OrderHistory> tempList = new LinkedList<OrderHistory>();
		try {
			TypedQuery<OrderHistory> query = em.createQuery("SELECT o FROM OrderHistory o WHERE :id = o.customer.id", OrderHistory.class);
			tempList = query.setParameter("id", id).getResultList();
			return tempList;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

//	@Override
//	public void addOrderToList(Customer customer, Order order) {
//		List<OrderHistory> tempList = new LinkedList<OrderHistory>();
//		OrderHistory orderH;
//		try {
//			TypedQuery<OrderHistory> query = em.createQuery("SELECT o FROM OrderHistory o WHERE :customer = o.customer", OrderHistory.class);
//			tempList = query.setParameter("customer", customer).getResultList();
//			if (!tempList.isEmpty()){
//				orderH = tempList.get(0);
//				em.merge(orderH);
//				orderH.getOrderList().add(order);
//				em.detach(orderH);
//			}else{
//				orderH = new OrderHistory();
//				orderH.setCustomer(customer);
//				List<Order> orderL = new LinkedList<Order>();
//				orderH.set
//				orderL.add(order);
//				orderH.setOrderList(orderL);
//				em.persist(orderH);
//			}
//		}catch (Exception e) {
//			throw new DAOException(e);
//		}
//	}
	

}
