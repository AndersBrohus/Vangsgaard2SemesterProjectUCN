package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBsupplier implements IFDBsupplier {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBsupplier() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<supplier> getAllSuppliers()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM supplier";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<supplier> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<supplier> list = new ArrayList<supplier>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			supplier supObj = new supplier();
			supObj = buildSupplier(results);	
            list.add(supObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private supplier buildSupplier(ResultSet results)
    {  
		supplier supObj = new supplier();
        try
        { // the columns from the table ZipCode  are used
        	supObj.setId(results.getInt("id"));
        	supObj.setName(results.getString(("name")));
        	supObj.setPhone(results.getInt("phone"));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Customer Type object");
        }
        return supObj;
    }
	    
	private supplier singleWhere(String wClause)
	{
		ResultSet results;
		supplier supObj = new supplier();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			supObj = buildSupplier(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	supObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return supObj;
	}
	
	public supplier findSupplierByPhone(int phone)
    {   String wClause = " phone = " + phone;
        return singleWhere(wClause);
    }
	
	@Override
    public supplier insertSupplier(supplier sup) throws Exception
    {
		 String query="INSERT INTO supplier(name, phone)  VALUES('"+
				 sup.getName() + "'," +
				 sup.getPhone()
				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println("Supplier ikke oprettet");
          throw new Exception ("Supplier is not inserted correct");
       }
      supplier supObj = getLatest();
      return supObj;
    }
	
	public supplier getSupplier(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateSupplier(supplier sup)
	{
		supplier supObj  = sup;
		int rc=-1;

		String query="UPDATE supplier SET "+
		 	  "name ='"+ supObj.getName()+"', "+
		 	  "phone ="+ supObj.getPhone() +
		          " WHERE id = "+ supObj.getId();
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
	
	public supplier getLatest()
	{
		ResultSet results;
		supplier supObj = new supplier();
                
	    String query = "SELECT TOP 1 * FROM supplier ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			supObj = buildSupplier(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	supObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return supObj;
	}
	
}
