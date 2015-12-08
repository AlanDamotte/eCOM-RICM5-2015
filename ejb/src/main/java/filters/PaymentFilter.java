package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ShoppingCart;

@WebFilter(urlPatterns = { "/createOrder", "/paymentManagement" })
public class PaymentFilter implements Filter {
	
	public static final String CART_PRODUCTS_SESSION = "cart_products";
	public static final String INDEX = "/index";
	
	ShoppingCart shoppingCart;

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response  = (HttpServletResponse) res;

		shoppingCart = (ShoppingCart) request.getSession().getAttribute(CART_PRODUCTS_SESSION);
		if(shoppingCart.getCart().isEmpty()){
			/* Pour terminer, poursuite de la requête en cours */
			response.sendRedirect(request.getContextPath() + INDEX);
		}else{
			/* Pour terminer, poursuite de la requête en cours */
			chain.doFilter(request, res);
		}

	}

	public void destroy() {
	}
}