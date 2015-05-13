package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrCustomer {
    public CtrCustomer() {
        
    }
    public ArrayList<customer> findAllEmployee()
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
     /* public int updateEmp(String fname, String lname, String ssn, double salary, String superssn)
      {
          IFDBcustomer dbCus= new DBcustomer();
          customer cusObj = new customer();
          emp.setSsn(ssn);
          emp.setFname(fname);
          emp.setLname(lname);
          emp.setSupervisor(new Employee(superssn));
          emp.setSalary(salary);
          return  dbEmp.updateEmployee(emp);
          
          
      }*/
      
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
