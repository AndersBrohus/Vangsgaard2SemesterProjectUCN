package Db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Mdl.*;

public class DBproduct implements IFDBproduct {
	private  Connection con;
    /** Creates a new instance of DBZipCodes */
    public DBproduct() {
      con = DbConnection.getInstance().getDBcon();
    }
	
    public ArrayList<product> getAllProducts()
    {
        return miscWhere("");
    }
    
	private String buildQuery(String wClause)
	{
	    String query="SELECT * FROM product";
		
		if (wClause.length()>0)
			query=query+" WHERE "+ wClause;
			
		return query;
	}
	
	private ArrayList<product> miscWhere(String wClause)
	{
        ResultSet results;
	    ArrayList<product> list = new ArrayList<product>();	
		
	    String query =  buildQuery(wClause);
  
            try{ // read the employee from the database
		Statement stmt = con.createStatement();
	 	stmt.setQueryTimeout(5);
	 	results = stmt.executeQuery(query);
	 	
	
		while( results.next() ){
			product proObj = new product();
			proObj = buildProduct(results);	
            list.add(proObj);	
		}//end while
                 stmt.close();     			
		}//slut try	
	 	catch(Exception e){
	 		System.out.println("Query exception - select: "+e);
			e.printStackTrace();
	 	}
		return list;
	}
	
	private product buildProduct(ResultSet results)
    {  
		product proObj = new product();
        try
        { // the columns from the table ZipCode  are used
        	proObj.setId(results.getInt("id"));
        	proObj.setPrice(results.getInt("purchasePrice"));
        	proObj.setStockId(results.getInt("stockId"));
        	proObj.setSupplierId(results.getInt("supplierId"));
        	proObj.setColorId(results.getInt("clothingColor"));
        	proObj.setName(results.getString("name"));
        	proObj.setSize(results.getString("clothingSize"));
        	proObj.setType(results.getString("type"));
        	
        	
        }
        catch(Exception e)
        {
        	System.out.println("error in building the product object");
        }
        return proObj;
    }
	    
	private product singleWhere(String wClause)
	{
		ResultSet results;
		product proObj = new product();
                
	    String query = buildQuery(wClause);
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			proObj = buildProduct(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	proObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return proObj;
	}
	
	@Override
    public product insertProduct(product pro) throws Exception
    {
		 String query="INSERT INTO product(name, purchasePrice,stockId,supplierId,type,clothingSize,clothingColor)  VALUES('"+
				 pro.getName() + "'," +
				 pro.getPrice() + "," +
				 pro.getStockId() + "," + 
				 pro.getSupplierId() + ",'" +
				 pro.getType() + "','" +
				 pro.getSize() + "', " +
				 pro.getColorId()
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
      product proObj = getLatest();
      return proObj;
    }
	
	public product getProduct(int id)
    {   String wClause = "  id = " + id;
        return singleWhere(wClause);
    }
	
	public int updateProduct(product pro)
	{
		product proObj  = pro;
		int rc=-1;

		String query="UPDATE product SET "+
		 	  "name ='"+ proObj.getName()+"', "+
		 	  "purchasePrice ="+ proObj.getPrice() + "," +
		 	  "stockId ="+ proObj.getStockId() + "," +
		 	  "supplierId ="+ proObj.getSupplierId()+","+
		 	  "type ='"+ proObj.getType() + "'," +
		 	  "clothingSize ='"+ proObj.getSize() + "'," +
		 	  "clothingColor =" + proObj.getColorId() +
		          " WHERE id = "+ proObj.getId();
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
	
	public product getLatest()
	{
		ResultSet results;
		product proObj = new product();
                
	    String query = "SELECT TOP 1 * FROM product ORDER BY id DESC;";
        //System.out.println(query);
        
		try
		{ 	// read the employee from the database
	 		Statement stmt = con.createStatement();
	 		stmt.setQueryTimeout(5);
	 		results = stmt.executeQuery(query);
	 		
	 		if( results.next() )
	 		{
	 			proObj = buildProduct(results);
	            
	            stmt.close();
			}
            else
            { 	//no employee was found
            	proObj = null;
            }
		}//end try	
	 	catch(Exception e)
		{
	 		System.out.println("Query exception: "+e);
	 	}
		return proObj;
	}
	
}
