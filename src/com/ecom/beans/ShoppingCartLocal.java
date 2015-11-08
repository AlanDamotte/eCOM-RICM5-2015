package com.ecom.beans;

import javax.ejb.Local;
import com.ecom.entities.Product;
import java.util.List;
 
@Local
public interface ShoppingCartLocal /* extends ShoppingCart */ {
    void initialize();
    void addProduct(Product product, int quantity);
    void removeProduct(Product product, int quantity);
 
    List<Product> getProducts();
    int getQuantity(Product product);
 
    public void release();
}
