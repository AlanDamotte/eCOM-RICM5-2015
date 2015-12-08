package dao;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import entities.Category;

@Remote
public interface CategoryDaoRemote {
	
	public Category find(long id);

	public void create(Category category);

	public List<Category> list();

	public void remove(Category category);
	
	public Map<String,List<Dimension>> getCategories();
}
