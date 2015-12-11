package ecom.stickers.dao;

import java.util.List;

import javax.ejb.Remote;

import ecom.stickers.entities.Customer;

@Remote
public interface CustomerDaoRemote {
	public Customer find(long id);

	public void create(Customer customer);

	public List<Customer> list();
	
	public Customer checkPassword(String email, String password);

	public void remove(Customer customer);
	
	boolean emailExists(String email);
}
