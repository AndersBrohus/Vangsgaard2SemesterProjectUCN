package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrDepartment {
	public CtrDepartment() {
        
    }
    public ArrayList<department> findAllDepartments()
    {
      IFDBdepartment dbDpart= new DBdepartment();
      ArrayList<department> allDpart = new ArrayList<department>();
      allDpart = dbDpart.getAllDepartments();
      return allDpart;
    }
    public department getDepartment(int id)
    {
        IFDBdepartment dbDpart= new DBdepartment();
        return dbDpart.getDepartment(id);
    }
    
    public department getLatest()
    {
    	IFDBdepartment dbDpart= new DBdepartment();
        return dbDpart.getLatest();
    }
	  public int updateDepartment (int id,String name)
	  {
		  IFDBdepartment dbDpart= new DBdepartment();
	      department dpart = dbDpart.getDepartment(id);
	      department dpartObj = new department();
	      dpartObj.setId(dpart.getId());
	      dpartObj.setDepartmentName(name);
	      return  dbDpart.updateDepartment(dpartObj);
	  }
      
      public void insertNew(String name) throws Exception
      {    
           department dpartObj = new department();
           dpartObj.setDepartmentName(name);
    
           try{
            DbConnection.startTransaction();
  		  	IFDBdepartment dbDpart= new DBdepartment();
  		  	dbDpart.insertDepartment(dpartObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("Department not inserted");
           }
      }
}
