package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;


public class CtrCustomerType {
	public CtrCustomerType(){
		
	}
	
	public ArrayList<customerType> findAllCustomerType()
    {
      IFDBcustomerType dbCType= new DBcustomerType();
      ArrayList<customerType> allCTypes = new ArrayList<customerType>();
      allCTypes = dbCType.getAllCustomerTypes();
      return allCTypes;
    }
	
	public customerType getCustomerType(int id)
    {
		IFDBcustomerType dbCType= new DBcustomerType();
        return dbCType.getCustomerType(id);
    }
	
	public customerType getLatest()
    {
		IFDBcustomerType dbCType= new DBcustomerType();
        return dbCType.getLatest();
    }
	
	public void insertNew(String title, int discount) throws Exception
    {    
		customerType cTypeObj = new customerType();
		cTypeObj.setTitle(title);
		cTypeObj.setDiscount(discount);
  
		try{
	        DbConnection.startTransaction();
		  	IFDBcustomerType dbCType= new DBcustomerType();
		  	dbCType.insertCustomerType(cTypeObj);
	        DbConnection.commitTransaction();
        }
        catch(Exception e)
        {
        	DbConnection.rollbackTransaction();
            throw new Exception("CustomerType not inserted");
        }
    }
	
	public int updateCustomerType(int id,String title, int discount)
	{
	  	IFDBcustomerType dbCType= new DBcustomerType();
		customerType cType = dbCType.getCustomerType(id);
		customerType cTypeObj = new customerType();
		cTypeObj.setId(cType.getId());
		cTypeObj.setTitle(title);
		cTypeObj.setDiscount(discount);
		return  dbCType.updateCustomerType(cTypeObj);
	}
}
