package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;


public class CtrStock {
	public CtrStock(){
		
	}
	
	public ArrayList<stock> getAllStocks()
    {
      IFDBstock dbSto = new DBstock();
      ArrayList<stock> allStocks = new ArrayList<stock>();
      allStocks = dbSto.getAllStocks();
      return allStocks;
    }
	
	public stock getSize(int id)
    {
	    IFDBstock dbSto = new DBstock();
        return dbSto.getStock(id);
    }
	
	public stock getLatest()
    {
	    IFDBstock dbSto = new DBstock();
        return dbSto.getLatest();
    }
	
	public void insertNew(int productId, int amount, int departmentId) throws Exception
    {    
		stock stoObj = new stock();
		stoObj.setProductId(productId);
		stoObj.setAmount(amount);
		stoObj.setDepartmentId(departmentId);
  
		try{
	        DbConnection.startTransaction();
	        IFDBstock dbSto = new DBstock();
	        dbSto.insertStock(stoObj);
	        DbConnection.commitTransaction();
        }
        catch(Exception e)
        {
        	DbConnection.rollbackTransaction();
            throw new Exception("CustomerType not inserted");
        }
    }
	
	public int updateSize(int id,int productId, int amount, int departmentId)
	{
        IFDBstock dbSto = new DBstock();
		stock stoObj = new stock();
		stoObj.setId(id);
		stoObj.setProductId(productId);
		stoObj.setAmount(amount);
		stoObj.setDepartmentId(departmentId);
		return  dbSto.updateStock(stoObj);
	}
}
