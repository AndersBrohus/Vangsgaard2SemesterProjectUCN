package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBdepartment implements IFDBdepartment{
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBdepartment() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<department> getAllDepartments()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM department";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<department> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<department> list = new ArrayList<department>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			department dpartObj = new department();
			dpartObj = buildDepartment(results);	
            list.add(dpartObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private department buildDepartment(ResultSet results)
    {  
		department dpartObj = new department();
        try
        { // the columns from the table ZipCode  are used
        	dpartObj.setId(results.getInt("id"));
        	dpartObj.setDepartmentName(results.getString(("departmentName")));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Department object");
        }
        return dpartObj;
    }
	    
	private department singleWhere(String wClause)
	{
		ResultSet results;
		department dpartObj = new department();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			dpartObj = buildDepartment(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	dpartObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return dpartObj;
	}
	
	@Override
    public department insertDepartment(department dpart) throws Exception
    {
		 String query="INSERT INTO department (departmentName)  VALUES('"+
				 dpart.getDepartmentName() + "'"
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
      department dpartObj = getLatest();
      return dpartObj;
    }
	
	public department getDepartment(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateDepartment(department dpart)
	{
		department dpartObj  = dpart;
		int rc=-1;

		String query="UPDATE department SET "+
		 	  "departmentName ='"+ dpartObj.getDepartmentName()+"'"+
		          " WHERE id = "+ dpartObj.getId();
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
	
	public department getLatest()
	{
		ResultSet results;
		department dpart = new department();
                
	    String query = "SELECT TOP 1 * FROM department ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			dpart = buildDepartment(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	dpart = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return dpart;
	}
}
