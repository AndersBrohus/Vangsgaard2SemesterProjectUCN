package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrSalesOrder {
    public CtrSalesOrder() {
        
    }
    public ArrayList<salesOrder> findAllSalesOrders()
    {
      IFDBsalesOrder dbSOrd= new DBsalesOrder();
      ArrayList<salesOrder> allSOrd = new ArrayList<salesOrder>();
      allSOrd = dbSOrd.getAllSalesOrders();
      return allSOrd;
    }
    public salesOrder getSalesOrder(int id)
    {
        IFDBsalesOrder dbSOrd= new DBsalesOrder();
        return dbSOrd.getSalesOrder(id);
    }
    public ArrayList<salesOrder> findAllSalesOrdersByCustomer(int phone)
    {
      IFDBsalesOrder dbSOrd= new DBsalesOrder();
      ArrayList<salesOrder> allSOrd = new ArrayList<salesOrder>();
      allSOrd = dbSOrd.findAllSalesOrdersByCustomer(phone);
      return allSOrd;
    }
    public salesOrder getLatest()
    {
        IFDBsalesOrder dbSOrd= new DBsalesOrder();
        return dbSOrd.getLatest();
    }
	  public int updateSalesOrder(int id,double amount, int customerId, int invoiceNo, String date,String deliveryDate, boolean deliveryStatus)
	  {
	      IFDBsalesOrder dbSOrd= new DBsalesOrder();
	      salesOrder sOrd = dbSOrd.getSalesOrder(id);
	      salesOrder sOrdObj = new salesOrder();
	      sOrdObj.setId(sOrd.getId());
	      sOrdObj.setAmount(amount);
	      sOrdObj.setCustomerId(customerId);
	      sOrdObj.setInvoiceId(invoiceNo);
	      sOrdObj.setDate(date);
	      sOrdObj.setDeliveryDate(deliveryDate);
	      sOrdObj.setDeliveryStatus(deliveryStatus);
	      return  dbSOrd.updateSalesOrder(sOrdObj);
	  }
      
      public void insertNew(int amount, int customerId, int invoiceNo, String date,String deliveryDate, boolean deliveryStatus) throws Exception
      {    
	      salesOrder sOrdObj = new salesOrder();
          sOrdObj.setAmount(amount);
 	      sOrdObj.setCustomerId(customerId);
 	      sOrdObj.setInvoiceId(invoiceNo);
 	      sOrdObj.setDate(date);
 	      sOrdObj.setDeliveryDate(deliveryDate);
 	      sOrdObj.setDeliveryStatus(deliveryStatus);
    
           try{
            DbConnection.startTransaction();
            IFDBsalesOrder dbSOrd= new DBsalesOrder();
            dbSOrd.insertSalesOrder(sOrdObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("Customer not inserted");
           }
      }
      
      public int insertOrder(int customerId, String date,String deliveryDate, boolean deliveryStatus) throws Exception
      {    
	      salesOrder sOrdObj = new salesOrder();
 	      sOrdObj.setCustomerId(customerId);
 	      sOrdObj.setDate(date);
 	      sOrdObj.setDeliveryDate(deliveryDate);
 	      sOrdObj.setDeliveryStatus(deliveryStatus);
 	      sOrdObj.setInvoiceId(1);
 	      int test = 0;
           try{
            DbConnection.startTransaction();
            IFDBsalesOrder dbSOrd= new DBsalesOrder();
            test = dbSOrd.insertSalesOrderWithId(sOrdObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("Customer not inserted");
           }
           
           return test;
      }

}
