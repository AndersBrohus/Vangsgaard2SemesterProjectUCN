package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBcustomerType {
    public ArrayList<customerType> getAllCustomerTypes();
    
    public customerType getCustomerType(int id);
    
    //insert a new customer
    public customerType insertCustomerType(customerType cType) throws Exception;
    //update information about an customer
    public int updateCustomerType(customerType cType);
    public customerType getLatest();

}
