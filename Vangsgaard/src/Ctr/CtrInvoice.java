package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;


public class CtrInvoice {
	public CtrInvoice(){
		
	}
	
	public ArrayList<invoice> getAllInvoices()
    {
      IFDBinvoice dbInv = new DBinvoice();
      ArrayList<invoice> allInvoices = new ArrayList<invoice>();
      allInvoices = dbInv.getAllInvoices();
      return allInvoices;
    }
	
	public invoice getInvoice(int id)
    {
	    IFDBinvoice dbInv = new DBinvoice();
        return dbInv.getInvoice(id);
    }
	
	public invoice getLatest()
    {
		IFDBinvoice dbInv = new DBinvoice();
        return dbInv.getLatest();
    }
	
	public void insertNew(String date, int amount) throws Exception
    {    
		invoice invObj = new invoice();
		invObj.setAmount(amount);
		invObj.setPaymentDate(date);
  
		try{
	        DbConnection.startTransaction();
		    IFDBinvoice dbInv = new DBinvoice();
	        dbInv.insertInvoice(invObj);
	        DbConnection.commitTransaction();
        }
        catch(Exception e)
        {
        	DbConnection.rollbackTransaction();
            throw new Exception("CustomerType not inserted");
        }
    }
	
	public int updateSize(int invoiceNo, int amount, String date)
	{
	    IFDBinvoice dbInv = new DBinvoice();
        invoice invObj = new invoice();
		invObj.setAmount(amount);
		invObj.setPaymentDate(date);
		invObj.setInvoiceNo(invoiceNo);
		return  dbInv.updateInvoice(invObj);
	}
}
