package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBproduct {
    public ArrayList<product> getAllProducts();
    
    public product getProduct(int id);
        
    //insert a new customer
    public product insertProduct(product pro) throws Exception;
    //update information about an customer
    public int updateProduct(product pro);
    public product getLatest();

}
