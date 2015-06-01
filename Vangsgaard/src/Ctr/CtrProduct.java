package Ctr;

import java.util.ArrayList;

import Db.*;
import Mdl.*;

public class CtrProduct {
    public CtrProduct() {
        
    }
    public ArrayList<product> findAllProducts()
    {
      IFDBproduct dbPro= new DBproduct();
      ArrayList<product> allPro = new ArrayList<product>();
      allPro = dbPro.getAllProducts();
      return allPro;
    }
    public product getProduct(int id)
    {
        IFDBproduct dbPro= new DBproduct();
        return dbPro.getProduct(id);
    }

    public product getLatest()
    {
        IFDBproduct dbPro= new DBproduct();
        return dbPro.getLatest();
    }
	  public int updateProduct(int id,int purchasePrice, int stockId, int supplierId,int clothingColor ,String name,String type, String clothingSize)
	  {
	      IFDBproduct dbPro= new DBproduct();
	      product pro = dbPro.getProduct(id);
	      product proObj = new product();
	      proObj.setId(pro.getId());
	      proObj.setColorId(clothingColor);
	      proObj.setName(name);
	      proObj.setPrice(purchasePrice);
	      proObj.setSize(clothingSize);
	      proObj.setStockId(stockId);
	      proObj.setSupplierId(supplierId);
	      proObj.setType(type);
	      
	      return  dbPro.updateProduct(proObj);
	  }
	  
	  public int updateProduct(int id,int purchasePrice, int supplierId,int clothingColor ,String name,String type, String clothingSize)
	  {
	      IFDBproduct dbPro= new DBproduct();
	      product pro = dbPro.getProduct(id);
	      product proObj = new product();
	      proObj.setId(pro.getId());
	      proObj.setColorId(clothingColor);
	      proObj.setName(name);
	      proObj.setPrice(purchasePrice);
	      proObj.setSize(clothingSize);
	      proObj.setSupplierId(supplierId);
	      proObj.setType(type);
	      
	      return  dbPro.updateProduct(proObj);
	  }
      
      
      public void insertNew(int purchasePrice, int stockId, int supplierId,int clothingColor ,String name,String type, String clothingSize) throws Exception
      {    
	      product proObj = new product();
	      proObj.setColorId(clothingColor);
	      proObj.setName(name);
	      proObj.setPrice(purchasePrice);
	      proObj.setSize(clothingSize);
	      proObj.setStockId(stockId);
	      proObj.setSupplierId(supplierId);
	      proObj.setType(type);
           try{
            DbConnection.startTransaction();
  	      	IFDBproduct dbPro= new DBproduct();
            dbPro.insertProduct(proObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("product not inserted");
               
           }
      }
      
      public void insertNew(int purchasePrice, int supplierId,int clothingColor ,String name,String type, String clothingSize) throws Exception
      {    
	      product proObj = new product();
	      proObj.setColorId(clothingColor);
	      proObj.setName(name);
	      proObj.setPrice(purchasePrice);
	      proObj.setSize(clothingSize);
	      proObj.setSupplierId(supplierId);
	      proObj.setType(type);
           try{
            DbConnection.startTransaction();
  	      	IFDBproduct dbPro= new DBproduct();
            dbPro.insertProduct(proObj);
            DbConnection.commitTransaction();
           }
           catch(Exception e)
           {
               DbConnection.rollbackTransaction();
               throw new Exception("product not inserted");
               
           }
      }
}
