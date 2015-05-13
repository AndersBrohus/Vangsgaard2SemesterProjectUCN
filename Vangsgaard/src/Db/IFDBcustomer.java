package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBcustomer {
    public ArrayList<customer> getAllCustomers();
    
    public customer getCustomer(int id);
    //find one customer having the name
    public customer findCustomerPhone(int phone);
    
    //insert a new customer
    public customer insertCustomer(customer cus) throws Exception;
    //update information about an customer
    public int updateCustomer(customer cus);
    public customer getLatest();

}
