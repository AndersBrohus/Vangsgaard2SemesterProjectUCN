package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrPartOrder {
    public CtrPartOrder() {
        
    }
    public ArrayList<partOrder> getAllpartOrders()
    {
      IFDBpartOrder dbPOrd= new DBpartOrder();
      ArrayList<partOrder> allPOrd = new ArrayList<partOrder>();
      allPOrd = dbPOrd.getAllpartOrders();
      return allPOrd;
    }
    public partOrder getPartOrder(int id)
    {
        IFDBpartOrder dbPOrd= new DBpartOrder();
        return dbPOrd.getPartOrder(id);
    }
    public ArrayList<partOrder> findAllPartOrdersByOrder(int orderId)
    {
      IFDBpartOrder dbPOrd= new DBpartOrder();
      ArrayList<partOrder> allPOrd = new ArrayList<partOrder>();
      allPOrd = dbPOrd.findAllPartOrdersByOrder(orderId);
      return allPOrd;
    }
    public partOrder getLatest()
    {
        IFDBpartOrder dbPOrd= new DBpartOrder();
        return dbPOrd.getLatest();
    }
	  public int updateSalesOrder(int id,int productId,int salesOrderId,int amount,int departmentId)
	  {
          IFDBpartOrder dbPOrd= new DBpartOrder();
          partOrder pOrd = dbPOrd.getPartOrder(id);
          partOrder pOrdObj = new partOrder();
          pOrdObj.setId(pOrd.getId());
          pOrdObj.setAmount(amount);
    	  pOrdObj.setSalesOrderId(salesOrderId);
    	  pOrdObj.setDepartmentId(departmentId);
    	  pOrdObj.setProductId(productId);
    	  
	      return  dbPOrd.updatePartOrder(pOrdObj);
	  }
      
      public void insertNew(int productId,int salesOrderId,int amount,int departmentId) throws Exception
      {    
    	  partOrder pOrdObj = new partOrder();
    	  pOrdObj.setAmount(amount);
    	  pOrdObj.setSalesOrderId(salesOrderId);
    	  pOrdObj.setDepartmentId(departmentId);
    	  pOrdObj.setProductId(productId);
    
           try{
            DbConnection.startTransaction();
            IFDBpartOrder dbPOrd= new DBpartOrder();
            dbPOrd.insertPartOrder(pOrdObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("Customer not inserted");
           }
      }
}
