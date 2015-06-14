package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBsalesOrder implements IFDBsalesOrder {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBsalesOrder() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<salesOrder> getAllSalesOrders()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM salesOrder";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<salesOrder> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<salesOrder> list = new ArrayList<salesOrder>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			salesOrder sOrdObj = new salesOrder();
			sOrdObj = buildSalesOrder(results);	
            list.add(sOrdObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private salesOrder buildSalesOrder(ResultSet results)
    {  
		salesOrder sOrdObj = new salesOrder();
        try
        { // the columns from the table ZipCode  are used
        	sOrdObj.setId(results.getInt("id"));
        	sOrdObj.setAmount(results.getInt("amount"));
        	sOrdObj.setCustomerId(results.getInt("customerId"));
        	sOrdObj.setDate(results.getString("date"));
        	sOrdObj.setDeliveryDate(results.getString("deliveryDate"));
        	sOrdObj.setDeliveryStatus(results.getBoolean("deliveryStatus"));
        	sOrdObj.setInvoiceId(results.getInt("invoiceNo"));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Sales Order object");
        }
        return sOrdObj;
    }
	    
	private salesOrder singleWhere(String wClause)
	{
		ResultSet results;
		salesOrder sOrdObj = new salesOrder();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			sOrdObj = buildSalesOrder(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	sOrdObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return sOrdObj;
	}
	
	public ArrayList<salesOrder> findAllSalesOrdersByCustomer(int phone)
    {
		DBcustomer dbCus = new DBcustomer();
		customer cus = dbCus.findCustomerPhone(phone);
		String wClause = " customerId = " + cus.getId();
        return miscWhere(wClause);
    }
	
	@Override
    public salesOrder insertSalesOrder(salesOrder sOrd) throws Exception
    {
		int deliveryStatus = (sOrd.isDeliveryStatus()) ? 1 : 0;
		 String query="INSERT INTO salesOrder(date, amount,deliveryStatus,deliveryDate,customerId,invoiceNo)  VALUES('"+
				 sOrd.getDate() + "'," +
				 sOrd.getAmount() + "," +
				 deliveryStatus + ",'" + 
				 sOrd.getDeliveryDate() + "'," +
				 sOrd.getCustomerId() + "," +
				 sOrd.getInvoiceId()
 				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          //System.out.println("Supplier ikke oprettet");
          throw new Exception ("SalesOrder is not inserted correct");
       }
      salesOrder sOrdObj = getLatest();
      return sOrdObj;
    }
	
    public int insertSalesOrderWithId(salesOrder sOrd) throws Exception
    {
		int deliveryStatus = (sOrd.isDeliveryStatus()) ? 1 : 0;
		 String query="INSERT INTO salesOrder(date, amount,deliveryStatus,deliveryDate,customerId,invoiceNo)  VALUES('"+
				 sOrd.getDate() + "'," +
				 sOrd.getAmount() + "," +
				 deliveryStatus + ",'" + 
				 sOrd.getDeliveryDate() + "'," +
				 sOrd.getCustomerId() + "," +
				 sOrd.getInvoiceId()
 				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          //System.out.println("Supplier ikke oprettet");
          throw new Exception ("SalesOrder is not inserted correct");
       }
      int sOrdId = getLatest().getId();
      return sOrdId;
    }
	
	public salesOrder getSalesOrder(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateSalesOrder(salesOrder sOrd)
	{
		salesOrder sOrdObj  = sOrd;
		int rc=-1;
		int deliveryStatus = (sOrd.isDeliveryStatus()) ? 1 : 0;
		String query="UPDATE salesOrder SET "+
		 	  "date ='"+ sOrdObj.getDate()+"', "+
		 	  "amount ="+ sOrdObj.getAmount() + ", " +
		 	  "deliveryStatus ="+ deliveryStatus + ", " +
		 	  "deliveryDate ='"+ sOrdObj.getDeliveryDate()+"', "+
		 	  "customerId ="+ sOrdObj.getCustomerId() + ", " +
		 	  "invoiceNo ="+ sOrdObj.getInvoiceId() +
		          " WHERE id = "+ sOrdObj.getId();
  		try{ // update employee
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	 	rc = stmt.executeUpdate(query);

	 	 	stmt.close();
		}//slut try
	 	catch(Exception ex){
	 		System.out.println(query);
	 	 	System.out.println("Update exception in employee db: "+ex);
	  	}
		return(rc);
	}
	
	public salesOrder getLatest()
	{
		ResultSet results;
		salesOrder sOrdObj = new salesOrder();
                
	    String query = "SELECT TOP 1 * FROM salesOrder ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			sOrdObj = buildSalesOrder(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	sOrdObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return sOrdObj;
	}
	
}
