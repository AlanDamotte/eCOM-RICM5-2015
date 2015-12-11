package ecom.stickers.dao;

import java.util.List;

import javax.ejb.Local;

import ecom.stickers.entities.Customer;

@Local
public interface CustomerDaoLocal {
	public Customer find(long id);

	public void create(Customer customer);

	public List<Customer> list();
	
	public Customer checkPassword(String email, String password);

	public void remove(Customer customer);
	
	boolean emailExists(String email);
}
