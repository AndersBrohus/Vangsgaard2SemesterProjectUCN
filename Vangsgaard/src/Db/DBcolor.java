package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBcolor implements IFDBcolor{
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBcolor() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<color> getAllColors()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM color";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<color> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<color> list = new ArrayList<color>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			color colObj = new color();
			colObj = buildColor(results);	
            list.add(colObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private color buildColor(ResultSet results)
    {  
		color colObj = new color();
        try
        { // the columns from the table ZipCode  are used
        	colObj.setId(results.getInt("id"));
        	colObj.setColor(results.getString(("color")));
        }
        catch(Exception e)
        {
        	System.out.println("error in building the Department object");
        }
        return colObj;
    }
	    
	private color singleWhere(String wClause)
	{
		ResultSet results;
		color colObj = new color();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			colObj = buildColor(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	colObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return colObj;
	}
	
	@Override
    public color insertColor(color col) throws Exception
    {
		 String query="INSERT INTO color (color)  VALUES('"+
				 col.getColor() + "'"
				 + ")";
       //System.out.println("insert : " + query);
      try{ // insert new employee +  dependent
          Statement stmt = con.createStatement();
          stmt.setQueryTimeout(5);
     	  stmt.executeUpdate(query);
          stmt.close();
      }//end try
       catch(SQLException ex){
          System.out.println("Color ikke oprettet");
          throw new Exception ("Color is not inserted correct");
       }
      color coltObj = getLatest();
      return coltObj;
    }
	
	public color getColor(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateColor(color col)
	{
		color colObj  = col;
		int rc=-1;

		String query="UPDATE color SET "+
		 	  "color ='"+ colObj.getColor()+"'"+
		          " WHERE id = "+ colObj.getId();
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
	
	public color getLatest()
	{
		ResultSet results;
		color col = new color();
                
	    String query = "SELECT TOP 1 * FROM color ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			col = buildColor(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	col = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return col;
	}
}
