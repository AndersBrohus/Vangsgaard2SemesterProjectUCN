package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBcustomer implements IFDBcustomer {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBcustomer() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<customer> getAllCustomers()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM customer";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<customer> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<customer> list = new ArrayList<customer>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			customer cusObj = new customer();
			cusObj = buildCustomer(results);	
            list.add(cusObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private customer buildCustomer(ResultSet results)
    {  
		customer cusObj = new customer();
        try
        { // the columns from the table ZipCode  are used
        	cusObj.setId(results.getInt("id"));
        	cusObj.setName(results.getString("name"));
        	cusObj.setAddress(results.getString("address"));
        	cusObj.setPhone(results.getInt("phone"));
        	cusObj.setEmail(results.getString("email"));
        	cusObj.setCustomerType(results.getInt("customerType"));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the product object");
        }
        return cusObj;
    }
	    
	private customer singleWhere(String wClause)
	{
		ResultSet results;
		customer cusObj = new customer();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			cusObj = buildCustomer(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	cusObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return cusObj;
	}
	
	@Override
    public customer insertCustomer(customer cus) throws Exception
    {
		 String query="INSERT INTO customer(name, address, phone,email,customerType)  VALUES('"+
				 cus.getName() + "','" +
				 cus.getAddress() + "'," +
				 cus.getPhone() + ",'" +
				 cus.getEmail() + "'," +
				 cus.getCustomerType()
				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println("Customer ikke oprettet");
          throw new Exception ("Customer is not inserted correct");
       }
      customer cusObj = getLatest();
      return cusObj;
    }
	
	public customer getCustomer(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public customer findCustomerPhone(int phone)
    {   String wClause = "  phone = " + phone;
        return singleWhere(wClause);
    }
	
	public int updateCustomer(customer cus)
	{
		customer cusObj  = cus;
		int rc=-1;

		String query="UPDATE customer SET "+
		 	  "name ='"+ cusObj.getName()+"', "+
		 	  "address ='"+ cusObj.getAddress() + "', " +
                          "phone ='"+ cusObj.getPhone() + "', " +
                          "email ='"+ cusObj.getEmail() + ", " +
                          "customerType ='"+ cusObj.getCustomerType() + "' " +
		          " WHERE id = '"+ cusObj.getId() + "'";
                System.out.println("Update query:" + query);
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
	
	public customer getLatest()
	{
		ResultSet results;
		customer cusObj = new customer();
                
	    String query = "SELECT TOP 1 * FROM customer ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			cusObj = buildCustomer(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	cusObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return cusObj;
	}
	
}
