package ecom.stickers.bank;

public class Account {

	private int securityCode;
	private double balance;

	
	public int getSecurityCode(){
		return this.securityCode;
	}
	
	public void setSecurityCode(int securityCode){
		this.securityCode = securityCode;
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public void setBalance(double balance){
		this.balance = balance;
	}
	
}
