package com.ecom.bank;

public class Account {

	private int securityCode;
	private double balance;

	
	public int getSecurityCode(){
		return this.securityCode;
	}
	
	public void setSecurityCode(int securityCode){
		this.securityCode = securityCode;
	}
	
	public Double getBalance(){
		return this.balance;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
}
