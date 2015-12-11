package ecom.stickers.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ecom.stickers.dao.CartDaoLocal;
import ecom.stickers.dao.CustomerDaoLocal;
import ecom.stickers.dao.ProductDaoLocal;
import ecom.stickers.entities.Cart;
import ecom.stickers.entities.Product;

@Stateful(mappedName = "ShoppingCartBean")
@Remote(ShoppingCart.class)
@Local(ShoppingCartLocal.class)
public class ShoppingCartBean implements ShoppingCartLocal, ShoppingCart, Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @PersistenceContext(unitName = "ecom_PU")
  private EntityManager em;

  private Map<Long, Integer> items  = new HashMap<Long, Integer>();

  private Long id = null;

  private double total;

  @EJB
  ProductDaoLocal productDao;

  @EJB
  CartDaoLocal cartDao;

  @EJB
  CustomerDaoLocal customerDao;

  @PostConstruct
  public void initialize() {
    System.out.println("Initialize ShoppingCart");
  }
  
  public void initializeClientCart(){
    try{
      Cart cart = cartDao.findCartByIdCustomer(id);
      this.items = cart.getCart();
      this.total = cart.getTotalPrice();
    }catch(Exception e){
      
    }
  }
  
  /**
   * Method for merging session cart and client cart
   */
  public void mergeClientCart(){
    try{
      Cart cart = cartDao.findCartByIdCustomer(id);
      Map<Long,Integer> clientCart = cart.getCart();
      Set<Long> keys = clientCart.keySet();
      Iterator<Long> it = keys.iterator();
      while (it.hasNext()) {
        Long key = (Long) it.next();
        if(!items.containsKey(key)){
          this.addProduct(key, clientCart.get(key));
        }
      }
    }catch(Exception e){
      
    }
  }

  /**
   * Set cart id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get cart id
   */
  public Long getId() {
    return this.id;
  }

  /**
   * Return cart total
   */
  public double getTotal() {
    return this.total;
  }

  /**
   * Add product to a cart
   */
  public void addProduct(Product product, int quantity) {
    if (items.isEmpty()) {
      this.total = 0;
    }
    if(quantity!=0){
      int quantityToPut = 0;
      int oldQuantity = getQuantity(product.getId());
      if(oldQuantity + quantity > productDao.find(product.getId()).getQuantity()){
        quantityToPut = productDao.find(product.getId()).getQuantity();
      }else{
        quantityToPut = oldQuantity + quantity;
      }
      double subtotal1 = productDao.find(product.getId()).getPrice() * getQuantity(product.getId());
      double subtotal = this.total - subtotal1;
      subtotal = subtotal + productDao.find(product.getId()).getPrice() * quantityToPut;
      this.total = subtotal;
      items.put(product.getId(), quantityToPut);
    }
  }

  /**
   * Add product to a cart
   */
  public void addProduct(Long id, int quantity) {
    if (items.isEmpty()) {
      this.total = 0;
    }
    if(quantity!=0){
      int quantityToPut = 0;
      int oldQuantity = getQuantity(id);
      if(oldQuantity + quantity > productDao.find(id).getQuantity()){
        quantityToPut = productDao.find(id).getQuantity();
      }else{
        quantityToPut = oldQuantity + quantity;
      }
      double subtotal1 = productDao.find(id).getPrice() * getQuantity(id);
      double subtotal = this.total - subtotal1;
      subtotal = subtotal + productDao.find(id).getPrice() * quantityToPut;
      this.total = subtotal;
      items.put(id, quantityToPut);
    }

  }

  /**
   * Update quantity of a product and update total
   */
  public void updateQuantity(Long id, int quantity) {
    double subtotal1 = productDao.find(id).getPrice() * getQuantity(id);
    double subtotal = this.total - subtotal1;

    if(quantity == 0){
      items.remove(id);
    }else{
      if(quantity > productDao.find(id).getQuantity()){
        items.replace(id,productDao.find(id).getQuantity());
        subtotal = subtotal + productDao.find(id).getPrice() * productDao.find(id).getQuantity();
      }else{
        items.replace(id, quantity);
        subtotal = subtotal + productDao.find(id).getPrice() * quantity;
      }
    }
    this.total = subtotal;
  }

  /**
   * Remove product from a cart and update total
   */
  public void removeProduct(Product product, int quantity) {
    int initialQuantity = getQuantity(product.getId());
    if (initialQuantity - quantity > 0) {
      items.replace(product.getId(), initialQuantity - quantity);
    } else {
      items.remove(product.getId());
    }
    double subtotal = this.total;
    subtotal = subtotal - product.getPrice() * quantity;
    this.total = subtotal;
  }

  /**
   * Remove product from the cart and update total
   */
  public void removeProduct(Long id, int quantity) {
    int initialQuantity = getQuantity(id);
    if (initialQuantity - quantity > 0) {
      items.replace(id, initialQuantity - quantity);
    } else {
      items.remove(id);
    }
    double subtotal = this.total;
    subtotal = subtotal - productDao.find(id).getPrice() * quantity;
    this.total = subtotal;
  }

  /**
   * Return the list of the products in the cart
   */
  public List<Long> getProductsById() {
    List<Long> list = new ArrayList<Long>(items.keySet());
    return list;
  }

  public List<Product> getProducts() {
    List<Product> list = null;
    Set<Long> keys = items.keySet();
    Iterator<Long> it = keys.iterator();
    while (it.hasNext()) {
      Long key = (Long) it.next();
      list.add(productDao.find(key));
    }
    return list;
  }

  /*
   * @Override public int getQuantity(Product product) { int quantity = 0; if
   * (items.containsKey(product.getId())){ quantity =
   * items.get(product.getId()); } return quantity; }
   */

  /**
   * Return the quantity of a product
   */
  public int getQuantity(Long id) {
    int quantity = 0;
    if (items.containsKey(id)) {
      quantity = items.get(id);
    }
    return quantity;
  }

  /**
   * Return the map of products => id of the product associated to the product
   */
  public Map<Long, Product> getMapProducts() {
    Map<Long, Product> cart = new HashMap<Long, Product>();
    Set<Long> keys = items.keySet();
    Iterator<Long> it = keys.iterator();
    while (it.hasNext()) {
      Long key = (Long) it.next();
      cart.put(key, productDao.find(key));
    }
    return cart;
  }
  
  /**
   * Return the cart => Product associated to the quantity
   */
  public Map<Product, Integer> getProductsMap() {
     Map<Product, Integer> cart = new HashMap<Product, Integer>();
    Set<Long> keys = items.keySet();
    Iterator<Long> it = keys.iterator();
    while (it.hasNext()) {
      Long key = (Long) it.next();
      cart.put(productDao.find(key), this.getQuantity(key));
    }
    return cart;
  }
  
  /**
   * Return the cart
   */
  public Map<Long, Integer> getCart() {
    return this.items;
  }

  /**
   * Update a quantity of a product
   */
  public void updateQuantity(Product product, int quantity) {
    double subtotal1 = product.getPrice() * getQuantity(product.getId());
    double subtotal = this.total - subtotal1;
    subtotal = subtotal + product.getPrice() * quantity;
    items.replace(product.getId(), quantity);
    this.total = subtotal;
  }
  
  
  /**
   * Method called when the session time
   */
  // Méthode de sauvegarde du panier associé à l'utilisateur lors de la déconnexion
  public void saveCart() {
    if (this.id != null) {
      if(cartDao.findCartByIdCustomer(id)!=null){
        Cart cart = cartDao.findCartByIdCustomer(id);
        cart.setCart(items);
        cart.setTotalPrice(total);
        cartDao.merge(cart);
      }else{
        Cart cart = new Cart();
        cart.setCustomer(customerDao.find(id));
        cart.setTotalPrice(total);
        cart.setCart(items);
        cartDao.create(cart);
      }
    }
  }

  // Méthode de sauvegarde du panier associé à l'utilisateur
  @Remove
  public void release() {
    System.out.println("End of stateful container");
    if (this.id != null) {
      if(cartDao.findCartByIdCustomer(id)!=null){
        Cart cart = cartDao.findCartByIdCustomer(id);
        cart.setCart(items);
        cart.setTotalPrice(total);
        cartDao.merge(cart);
      }else{
        Cart cart = new Cart();
        cart.setCustomer(customerDao.find(id));
        cart.setTotalPrice(total);
        cart.setCart(items);
        cartDao.create(cart);
      }
    }
  }
  
  public void clear(){
    this.items.clear();
    this.total = 0;
  }
}
