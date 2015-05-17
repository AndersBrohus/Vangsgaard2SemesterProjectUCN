package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBcustomerType implements IFDBcustomerType {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBcustomerType() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<customerType> getAllCustomerTypes()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM customerType";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<customerType> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<customerType> list = new ArrayList<customerType>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			customerType cTypeObj = new customerType();
			cTypeObj = buildCustomerType(results);	
            list.add(cTypeObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private customerType buildCustomerType(ResultSet results)
    {  
		customerType cTypeObj = new customerType();
        try
        { // the columns from the table ZipCode  are used
        	cTypeObj.setId(results.getInt("id"));
        	cTypeObj.setDiscount(results.getInt("discount"));
        	cTypeObj.setTitle(results.getString(("title")));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Customer Type object");
        }
        return cTypeObj;
    }
	    
	private customerType singleWhere(String wClause)
	{
		ResultSet results;
		customerType cTypeObj = new customerType();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			cTypeObj = buildCustomerType(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	cTypeObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return cTypeObj;
	}
	
	@Override
    public customerType insertCustomerType(customerType cType) throws Exception
    {
		 String query="INSERT INTO customerType(title, discount)  VALUES('"+
				 cType.getTitle() + "'," +
				 cType.getDiscount()
				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println("customerType ikke oprettet");
          throw new Exception ("customerType is not inserted correct");
       }
      customerType cTypeObj = getLatest();
      return cTypeObj;
    }
	
	public customerType getCustomerType(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateCustomerType(customerType cType)
	{
		customerType cTypeObj  = cType;
		int rc=-1;

		String query="UPDATE customerType SET "+
		 	  "title ='"+ cTypeObj.getTitle()+"', "+
		 	  "discount ="+ cTypeObj.getDiscount() +
		          " WHERE id = "+ cTypeObj.getId();
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
	
	public customerType getLatest()
	{
		ResultSet results;
		customerType cTypeObj = new customerType();
                
	    String query = "SELECT TOP 1 * FROM customerType ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			cTypeObj = buildCustomerType(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	cTypeObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return cTypeObj;
	}
	
}
