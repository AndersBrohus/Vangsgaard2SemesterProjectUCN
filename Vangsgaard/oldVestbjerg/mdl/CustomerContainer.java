package mdl;

import java.util.ArrayList;

public class CustomerContainer {
	private static CustomerContainer instance;
	private ArrayList<Customer> customerList;
	
	private CustomerContainer() {
		customerList = new ArrayList<Customer>();
	}
	
	public static CustomerContainer getInstance()
	{
		if(instance == null){
			instance = new CustomerContainer();
		}
		return instance;
	}
	
	public void createCustomer(Customer cus)
	{
		customerList.add(cus);
	}
	
	public Customer findCustomer(String phone)
	{
		int i=0;
        boolean found = false;
        Customer cus = null;
        while(i<customerList.size() && !found){
        	Customer cust = customerList.get(i);
            String number = cust.getPhone();
            if (number.equals(phone)){
                found = true;
                cus = customerList.get(i);
                
            }
            else{
                i++;
            }
        }
        return cus;
	}
	
	public Customer getCustomer(int customerNumber)
	{
		int i=0;
        boolean found = false;
        Customer cus = null;
        while(i<customerList.size() && !found){
        	Customer cust = customerList.get(i);
            int number = cust.getCustomerNumber();
            if (number == customerNumber){
                found = true;
                cus = customerList.get(i);
                
            }
            else{
                i++;
            }
        }
        return cus;
	}
}
