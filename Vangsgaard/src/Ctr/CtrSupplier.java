package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;


public class CtrSupplier {
	public CtrSupplier(){
		
	}
	
	public ArrayList<supplier> findAllSuppliers()
    {
      IFDBsupplier dbSup = new DBsupplier();
      ArrayList<supplier> allSup = new ArrayList<supplier>();
      allSup = dbSup.getAllSuppliers();
      return allSup;
    }
	
	public supplier getSupplier(int id)
    {
	    IFDBsupplier dbSup = new DBsupplier();
        return dbSup.getSupplier(id);
    }
	
	public supplier findSupplierByPhone(int phone)
    {
	    IFDBsupplier dbSup = new DBsupplier();
        return dbSup.findSupplierByPhone(phone);
    }
	
	public supplier getLatest()
    {
	    IFDBsupplier dbSup = new DBsupplier();
        return dbSup.getLatest();
    }
	
	public void insertNew(String name, int phone) throws Exception
    {    
		supplier supObj = new supplier();
		supObj.setName(name);
		supObj.setPhone(phone);
  
		try{
	        DbConnection.startTransaction();
	        IFDBsupplier dbSup = new DBsupplier();
	        dbSup.insertSupplier(supObj);
	        DbConnection.commitTransaction();
        }
        catch(Exception e)
        {
        	DbConnection.rollbackTransaction();
            throw new Exception("CustomerType not inserted");
        }
    }
	
	public int updateSupplier(int oldPhone,String name, int newPhone)
	{
		IFDBsupplier dbSup = new DBsupplier();
		supplier sup = dbSup.findSupplierByPhone(oldPhone);
		supplier supObj = new supplier();
		supObj.setId(sup.getId());
		supObj.setName(name);
		supObj.setPhone(newPhone);
		return  dbSup.updateSupplier(supObj);
	}
}
