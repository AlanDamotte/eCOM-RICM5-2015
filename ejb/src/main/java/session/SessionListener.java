package session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import beans.ShoppingCart;

public class SessionListener implements HttpSessionListener {

	public static final String CART_PRODUCTS_SESSION = "cart_products";

	private ShoppingCart shoppingCart = null;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		shoppingCart = (ShoppingCart) session.getAttribute(CART_PRODUCTS_SESSION);
		if (shoppingCart != null) {
			shoppingCart.release();
		}
		System.out.println("sessionDestroyed");
	}
}