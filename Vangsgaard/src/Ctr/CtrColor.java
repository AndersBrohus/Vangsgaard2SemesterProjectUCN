package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrColor {
	public CtrColor() {
        
    }
    public ArrayList<color> findAllColors()
    {
      IFDBcolor dbColor= new DBcolor();
      ArrayList<color> allCol = new ArrayList<color>();
      allCol = dbColor.getAllColors();
      return allCol;
    }
    public color getColor(int id)
    {
        IFDBcolor dbColor= new DBcolor();
        return dbColor.getColor(id);
    }
    
    public color getLatest()
    {
        IFDBcolor dbColor= new DBcolor();
        return dbColor.getLatest();
    }
	  public int updateColor (int id,String name)
	  {
	      IFDBcolor dbColor= new DBcolor();
	      color col = dbColor.getColor(id);
	      color colObj = new color();
	      colObj.setId(col.getId());
	      colObj.setColor(name);
	      return  dbColor.updateColor(colObj);
	  }
      
      public void insertNew(String name) throws Exception
      {    
    	  color colObj = new color();
          colObj.setColor(name);
    
           try{
            DbConnection.startTransaction();
  	      	IFDBcolor dbColor= new DBcolor();
  	      	dbColor.insertColor(colObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("Color not inserted");
           }
      }
}
