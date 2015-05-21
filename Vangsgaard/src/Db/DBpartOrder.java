package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBpartOrder implements IFDBpartOrder {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBpartOrder() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<partOrder> getAllpartOrders()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM partOrder";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<partOrder> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<partOrder> list = new ArrayList<partOrder>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			partOrder pOrd = new partOrder();
			pOrd = buildPartOrder(results);	
            list.add(pOrd);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private partOrder buildPartOrder(ResultSet results)
    {  
		partOrder pOrdObj = new partOrder();
        try
        { // the columns from the table ZipCode  are used
        	pOrdObj.setId(results.getInt("id"));
        	pOrdObj.setAmount(results.getInt("amount"));
        	pOrdObj.setDepartmentId(results.getInt("departmentId"));
        	pOrdObj.setProductId(results.getInt("productId"));
        	pOrdObj.setSalesOrderId(results.getInt("salesOrderId"));
        	
        	
        }
        catch(Exception e)
        {
        	System.out.println("error in building the product object");
        }
        return pOrdObj;
    }
	    
	private partOrder singleWhere(String wClause)
	{
		ResultSet results;
		partOrder pOrdObj = new partOrder();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			pOrdObj = buildPartOrder(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	pOrdObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return pOrdObj;
	}
	
	public ArrayList<partOrder> findAllPartOrdersByOrder(int id)
    {
		DBsalesOrder dbSOrd = new DBsalesOrder();
		salesOrder ord = dbSOrd.getSalesOrder(id);
		String wClause = " salesOrder = " + ord.getId();
        return miscWhere(wClause);
    }
	
	@Override
    public partOrder insertPartOrder(partOrder pOrd) throws Exception
    {
		 String query="INSERT INTO partOrder(productId, salesOrderId,amount,departmentId)  VALUES("+
				 pOrd.getProductId() + "," +
				 pOrd.getSalesOrderId() + "," +
				 pOrd.getAmount() + "," + 
				 pOrd.getDepartmentId()
 				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println(query);
          throw new Exception ("SalesOrder is not inserted correct");
       }
      partOrder pOrdObj = getLatest();
      return pOrdObj;
    }
	
	public partOrder getPartOrder(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updatePartOrder(partOrder pOrd)
	{
		partOrder pOrdObj  = pOrd;
		int rc=-1;

		String query="UPDATE partOrder SET "+
		 	  "productId ="+ pOrdObj.getProductId()+", "+
		 	  "salesOrderId ="+ pOrdObj.getSalesOrderId() + "," +
		 	  "amount ="+ pOrdObj.getAmount() + "," +
		 	  "departmentId ="+ pOrdObj.getDepartmentId()+
		          " WHERE id = "+ pOrdObj.getId();
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
	
	public partOrder getLatest()
	{
		ResultSet results;
		partOrder pOrdObj = new partOrder();
                
	    String query = "SELECT TOP 1 * FROM partOrder ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			pOrdObj = buildPartOrder(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	pOrdObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return pOrdObj;
	}
	
}
