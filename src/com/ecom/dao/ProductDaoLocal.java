package com.ecom.dao;

import java.util.List;

import javax.ejb.Local;

import com.ecom.entities.Product;

@Local
public interface ProductDaoLocal {

	public Product find(long id);

	public void create(Product product);

	public List<Product> list();

	public void remove(Product product);
}
