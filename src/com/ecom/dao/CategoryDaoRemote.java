package com.ecom.dao;

import java.util.List;

import javax.ejb.Remote;

import com.ecom.entities.Category;

@Remote
public interface CategoryDaoRemote {
	
	public Category find(long id);

	public void create(Category category);

	public List<Category> list();

	public void remove(Category category);
}
