package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBstock implements IFDBstock {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBstock() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<stock> getAllStocks()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM stock";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<stock> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<stock> list = new ArrayList<stock>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			stock stoObj = new stock();
			stoObj = buildStock(results);	
            list.add(stoObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private stock buildStock(ResultSet results)
    {  
		stock stoObj = new stock();
        try
        { // the columns from the table ZipCode  are used
        	stoObj.setId(results.getInt("id"));
        	stoObj.setAmount(results.getInt("amount"));
        	stoObj.setDepartmentId(results.getInt("departmentId"));
        	stoObj.setProductId(results.getInt("productId"));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the stock object");
        }
        return stoObj;
    }
	    
	private stock singleWhere(String wClause)
	{
		ResultSet results;
		stock stoObj = new stock();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			stoObj = buildStock(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	stoObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return stoObj;
	}
	
	@Override
    public stock insertStock(stock sto) throws Exception
    {
		 String query="INSERT INTO stock(productId, amount, departmentId)  VALUES("+
				 sto.getProductId() + "," +
				 sto.getAmount() + "," +
				 sto.getDepartmentId()
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
      stock stoObj = getLatest();
      return stoObj;
    }
	
	public stock getStock(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateStock(stock sto)
	{
		stock stoObj  = sto;
		int rc=-1;

		String query="UPDATE stock SET "+
		 	  "productId ="+ stoObj.getProductId()+", "+
		 	  "amount ='"+ stoObj.getAmount() + "," +
		 	  "departmentId =" + stoObj.getDepartmentId() +
		          " WHERE id = "+ stoObj.getId();
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
	
	public stock getLatest()
	{
		ResultSet results;
		stock stoObj = new stock();
                
	    String query = "SELECT TOP 1 * FROM stock ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			stoObj = buildStock(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	stoObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return stoObj;
	}
	
}
