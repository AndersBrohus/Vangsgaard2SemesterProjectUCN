package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrCustomer {
    public CtrCustomer() {
        
    }
    public ArrayList<customer> findAllCustomers()
    {
      IFDBcustomer dbCus= new DBcustomer();
      ArrayList<customer> allCus = new ArrayList<customer>();
      allCus = dbCus.getAllCustomers();
      return allCus;
    }
    public customer getCustomer(int id)
    {
        IFDBcustomer dbCus= new DBcustomer();
        return dbCus.getCustomer(id);
    }
     public customer findCustomerPhone(int phone)
    {
         IFDBcustomer dbCus= new DBcustomer();
        return dbCus.findCustomerPhone(phone);
    }
    public customer getLatest()
    {
          IFDBcustomer dbCus= new DBcustomer();
        return dbCus.getLatest();
    }
	  public int updateCustomer(int oldPhone,String name, String address, int newPhone, String email,int ct)
	  {
	      IFDBcustomer dbCus= new DBcustomer();
	      customer cus = dbCus.findCustomerPhone(oldPhone);
	      customer cusObj = new customer();
	      cusObj.setId(cus.getId());
	      cusObj.setName(name);
	      cusObj.setAddress(address);
	      cusObj.setPhone(newPhone);
	      cusObj.setEmail(email);
	      cusObj.setCustomerType(ct);
	      return  dbCus.updateCustomer(cusObj);
	  }
      
      public void insertNew(String name, String address, int phone, String email,int ct) throws Exception
      {    
           customer cusObj = new customer();
           cusObj.setName(name);
           cusObj.setAddress(address);
           cusObj.setPhone(phone);
           cusObj.setEmail(email);
           cusObj.setCustomerType(ct);
    
           try{
            DbConnection.startTransaction();
            IFDBcustomer dbCus= new DBcustomer();
            dbCus.insertCustomer(cusObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("Customer not inserted");
           }
      }
}
