package com.ecom.service;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.EJBContext;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import com.ecom.bank.Bank;
import com.ecom.beans.MailSenderBean;
import com.ecom.beans.ShoppingCart;
import com.ecom.dao.OrderDaoRemote;
import com.ecom.dao.OrderHistoryDaoRemote;
import com.ecom.dao.ProductDaoRemote;
import com.ecom.entities.Customer;
import com.ecom.entities.Order;
import com.ecom.entities.OrderHistory;

@Stateless
public class UserServiceImpl {

	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String ATT_SESSION_CUSTOMER = "customerSession";
	public static final String ATT_PAYMENT_STATUS = "paymentStatus";

	public static final String FIELD_CARD_NUMBER = "cardNumber";
	public static final String FIELD_CARD_SECURITYCODE = "securityCode";
	
	@Resource static SessionContext context;
	
	@Asynchronous
	public static void sendEmailOrderComplete(Customer customer, Order order, MailSenderBean mailSender){
		String fromEmail = "alan.damotte@gmail.com";
		String username = "alan.damotte";
		String password = "XXX";

		String toEmail = customer.getEmail();
		//TODO : modifier le sujet et le corps de message
		String subject = "Confirmation de commande" + order.getId().toString();
		String message = "Bonjour M/Mme" + customer.getLastname() + customer.getFirstname() + ":\n"
						+ "Nous vous confirmation votre commande numéro " + order.getId().toString() + "d'un montant de " + order.getAmount();

		// Call to mail sender bean
		mailSender.sendEmail(fromEmail, username, password, toEmail, subject, message);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public synchronized static void proceedPayment(HttpServletRequest request, OrderDaoRemote orderDao, 
			ProductDaoRemote productDao, OrderHistoryDaoRemote orderHistory, MailSenderBean mailSender) throws Exception {
		
		HttpSession session = request.getSession();
		
		Customer customer;

		Order order;

		Bank bank = new Bank();
		
		try {
			System.out.println("Preparing payment");
			
			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(CART_PRODUCTS_SESSION);
			customer = (Customer) session.getAttribute(ATT_SESSION_CUSTOMER);

			Long cardNumber = Long.parseLong((String) session.getAttribute(FIELD_CARD_NUMBER));
			int securityCode = Integer.parseInt((String) session.getAttribute(FIELD_CARD_SECURITYCODE));

			boolean validate = bank.paymentProcess(cardNumber, shoppingCart.getTotal(), securityCode);
			if (validate) {
				System.out.println("Proceed to payment");
				/*
				 * Récupération et conversion de la date en String selon le
				 * format choisi.
				 */
				DateTime dt = new DateTime();

				order = new Order();
				order.setAmount(shoppingCart.getTotal());
				order.setCart(shoppingCart.getProductsMap());
				order.setCustomer(customer);
				order.setDate(dt);
				// TODO
				order.setDeliveryStatus("");
				order.setPaymentStatus("");
				
				boolean availability = productDao.checkAvailability(order);
				if(!availability){
					throw new Exception();
				}

				// orderDao.create(order);

				// Mise à jour des quantités de produits restantes
				productDao.updateProductQuantity(shoppingCart);

				shoppingCart.clear();
				request.getSession().setAttribute(CART_PRODUCTS_SESSION, shoppingCart);

				OrderHistory orderH = new OrderHistory();
				orderH.setCustomer(customer);
				orderH.setOrder(order);
				orderHistory.create(orderH);
				System.out.println("Payment finished");
				
				//----------------------------------------
				
				System.out.println("Sending mail");
				
				sendEmailOrderComplete(customer, order, mailSender);
				
				System.out.println("Mail sent");
				
			}else{
				throw new Exception();
			}
		} catch (Exception up) {
			System.out.println("Exception catched");
			((EJBContext) context).setRollbackOnly();
			throw up;
		}
	}
}
