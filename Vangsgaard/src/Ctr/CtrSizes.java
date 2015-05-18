package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;


public class CtrSizes {
	public CtrSizes(){
		
	}
	
	public ArrayList<sizes> findAllSizes()
    {
      IFDBsizes dbSize = new DBsizes();
      ArrayList<sizes> allSizes = new ArrayList<sizes>();
      allSizes = dbSize.getAllSizes();
      return allSizes;
    }
	
	public ArrayList<sizes> findAllSizesByCustomer(int phone)
    {
      IFDBsizes dbSize = new DBsizes();
      ArrayList<sizes> allSizes = new ArrayList<sizes>();
      allSizes = dbSize.getAllSizesByCustomer(phone);
      return allSizes;
    }
	
	public sizes getSize(int id)
    {
	      IFDBsizes dbSize = new DBsizes();
        return dbSize.getSize(id);
    }
	
	public sizes getLatest()
    {
	    IFDBsizes dbSize = new DBsizes();
        return dbSize.getLatest();
    }
	
	public void insertNew(String size, String type, int customerPhone) throws Exception
    {    
		CtrCustomer ctrCus = new CtrCustomer();
		sizes sizeObj = new sizes();
		customer cus = ctrCus.findCustomerPhone(customerPhone);
		sizeObj.setSize(size);
		sizeObj.setType(type);
		sizeObj.setCustomerId(cus.getId());
  
		try{
	        DbConnection.startTransaction();
		    IFDBsizes dbSize = new DBsizes();
	        dbSize.insertSize(sizeObj);
	        DbConnection.commitTransaction();
        }
        catch(Exception e)
        {
        	DbConnection.rollbackTransaction();
            throw new Exception("CustomerType not inserted");
        }
    }
	
	public int updateSize(int id,String size, String type, int customerPhone)
	{
	    IFDBsizes dbSize = new DBsizes();
		CtrCustomer ctrCus = new CtrCustomer();
		sizes sizeId = dbSize.getSize(id);
		customer cus = ctrCus.findCustomerPhone(customerPhone);
		sizes sizeObj = new sizes();
		sizeObj.setId(sizeId.getId());
		sizeObj.setSize(size);
		sizeObj.setType(type);
		sizeObj.setCustomerId(cus.getId());
		return  dbSize.updateSize(sizeObj);
	}
}
