package com.ecom.dao;

import java.util.List;

import javax.ejb.Local;

import com.ecom.entities.Category;

@Local
public interface CategoryDaoLocal {
	
	public Category find(long id);

	public void create(Category category);

	public List<Category> list();

	public void remove(Category category);
}
