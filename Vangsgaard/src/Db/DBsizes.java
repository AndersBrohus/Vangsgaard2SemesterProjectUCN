package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBsizes implements IFDBsizes {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBsizes() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<sizes> getAllSizes()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM sizes";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<sizes> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<sizes> list = new ArrayList<sizes>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			sizes sizeObj = new sizes();
			sizeObj = buildSize(results);	
            list.add(sizeObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private sizes buildSize(ResultSet results)
    {  
		sizes sizeObj = new sizes();
        try
        { // the columns from the table ZipCode  are used
        	sizeObj.setId(results.getInt("id"));
        	sizeObj.setSize(results.getString(("size")));
        	sizeObj.setType(results.getString(("type")));
        	sizeObj.setCustomerId(results.getInt("customerId"));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Customer Type object");
        }
        return sizeObj;
    }
	    
	private sizes singleWhere(String wClause)
	{
		ResultSet results;
		sizes sizeObj = new sizes();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			sizeObj = buildSize(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	sizeObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return sizeObj;
	}
	
	public ArrayList<sizes> getAllSizesByCustomer(int phone)
    {
		DBcustomer dbCus = new DBcustomer();
		customer cus = dbCus.findCustomerPhone(phone);
		String wClause = " customerId = " + cus.getId();
        return miscWhere(wClause);
    }
	
	@Override
    public sizes insertSize(sizes size) throws Exception
    {
		 String query="INSERT INTO sizes(size, type, customerId)  VALUES('"+
				 size.getSize() + "','" +
				 size.getType() + "'," +
				 size.getCustomerId()
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
      sizes sizeObj = getLatest();
      return sizeObj;
    }
	
	public sizes getSize(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateSize(sizes size)
	{
		sizes sizeObj  = size;
		int rc=-1;

		String query="UPDATE sizes SET "+
		 	  "size ='"+ sizeObj.getSize()+"', "+
		 	  "type ='"+ sizeObj.getType() + "'," +
		 	  "customerId =" + sizeObj.getCustomerId() +
		          " WHERE id = "+ sizeObj.getId();
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
	
	public sizes getLatest()
	{
		ResultSet results;
		sizes sizeObj = new sizes();
                
	    String query = "SELECT TOP 1 * FROM sizes ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			sizeObj = buildSize(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	sizeObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return sizeObj;
	}
	
}
