package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBinvoice implements IFDBinvoice {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBinvoice() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<invoice> getAllInvoices()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM invoice";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<invoice> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<invoice> list = new ArrayList<invoice>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			invoice invObj = new invoice();
			invObj = buildInvoice(results);	
            list.add(invObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private invoice buildInvoice(ResultSet results)
    {  
		invoice invObj = new invoice();
        try
        { // the columns from the table ZipCode  are used
        	invObj.setInvoiceNo(results.getInt("id"));
        	invObj.setAmount(results.getInt("amount"));
        	invObj.setPaymentDate(results.getString("paymentDate"));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Customer Type object");
        }
        return invObj;
    }
	    
	private invoice singleWhere(String wClause)
	{
		ResultSet results;
		invoice invObj = new invoice();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			invObj = buildInvoice(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	invObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return invObj;
	}
	
	@Override
    public int insertInvoice(invoice inv) throws Exception
    {
		 String query="INSERT INTO invoice(paymentDate, amount)  VALUES('"+
				 inv.getPaymentDate() + "'," +
				 inv.getAmount()
				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println("Size ikke oprettet");
          throw new Exception ("Size is not inserted correct");
       }
      int sOrdId = getLatest().getInvoiceNo();
      return sOrdId;
    }
	
	public invoice getInvoice(int id)
    {   String wClause = "  invoiceNo = " + id;
        return singleWhere(wClause);
    }
	
	public int updateInvoice(invoice inv)
	{
	      invoice invObj =inv;

		int rc=-1;

		String query="UPDATE invoice SET "+
		 	  "paymentDate ='"+ invObj.getPaymentDate() +"', "+
		 	  "amount =" + invObj.getAmount() +
		          " WHERE id = "+ invObj.getInvoiceNo();
  		try{ // update employee
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 	 	rc = stmt.executeUpdate(query);

	 	 	stmt.close();
		}//slut try
	 	catch(Exception ex){
	 	 	System.out.println("Update exception in employee db: "+ex);
	  	}
		return(rc);
	}
	
	public invoice getLatest()
	{
		ResultSet results;
		invoice invObj = new invoice();
                
	    String query = "SELECT TOP 1 * FROM invoice ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			invObj = buildInvoice(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	invObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return invObj;
	}
	
}
