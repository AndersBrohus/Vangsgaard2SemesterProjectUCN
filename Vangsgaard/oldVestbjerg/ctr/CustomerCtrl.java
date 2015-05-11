package ctr;
import mdl.*;

public class CustomerCtrl {
	private CustomerContainer cCon;
	
	public CustomerCtrl()
	{
		cCon = CustomerContainer.getInstance();
	}
	
	public void createCustomer(String name, String address, String phone, String email)
	{
		Customer cus = new Customer(name,address,phone,email);
		cCon.createCustomer(cus);
	}
	
	public Customer findCustomer(String phone)
	{
		Customer cus = cCon.findCustomer(phone);
		return cus;
	}
	
	public Customer getCustomer(int customerNumber)
	{
		Customer cus = cCon.getCustomer(customerNumber);
		return cus;
	}
	
	public void updateCustomer(Customer cus, String name, String address, String phone, String email)
	{
		cus.setName(name);
		cus.setAddress(address);
		cus.setPhone(phone);
		cus.setEmail(email);
	}
}
