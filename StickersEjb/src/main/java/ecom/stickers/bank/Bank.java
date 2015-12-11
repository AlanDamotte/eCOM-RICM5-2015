package ecom.stickers.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank{
	
	Map<Long, Account> listAccount = new HashMap<Long, Account>();

	public Bank(){
		Account account = new Account();
		account.setSecurityCode(123);
		account.setBalance(200);
		
		Account account2 = new Account();
		account2.setSecurityCode(123);
		account2.setBalance(20);
		
		this.listAccount.put((long) 123456, account);
		this.listAccount.put((long) 123, account2);
	}
	
	public boolean paymentProcess(Long accountNumber, double amount, int securityCode){
		if(!listAccount.containsKey(accountNumber)){
			return false;
		}
		if(amount > ((Account) listAccount.get(accountNumber)).getBalance()){
			return false;
		}
		if(((Account) listAccount.get(accountNumber)).getSecurityCode() != securityCode){
			return false;
		}
		debitCustomerAccount(accountNumber,amount);
		return true;
	}
	
	public synchronized void debitCustomerAccount(Long accountNumber, double amount){
		double actualBalance = ((Account) listAccount.get(accountNumber)).getBalance();
		((Account) listAccount.get(accountNumber)).setBalance(actualBalance - amount);
	}
	
	public Map<Long, Account> getAccount(){
		return this.listAccount;
	}
}
