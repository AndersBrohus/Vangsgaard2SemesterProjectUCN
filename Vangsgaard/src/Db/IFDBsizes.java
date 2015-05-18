package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBsizes {
    public ArrayList<sizes> getAllSizes();
    
    public ArrayList<sizes> getAllSizesByCustomer(int phone);

    public sizes getSize(int id);
        
    //insert a new customer
    public sizes insertSize(sizes size) throws Exception;
    //update information about an customer
    public int updateSize(sizes size);
    public sizes getLatest();

}
