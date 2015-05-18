package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBsupplier {
    public ArrayList<supplier> getAllSuppliers();
    
    public supplier getSupplier(int id);
    
    public supplier findSupplierByPhone(int phone);
    
    //insert a new customer
    public supplier insertSupplier(supplier sup) throws Exception;
    //update information about an customer
    public int updateSupplier(supplier sup);
    public supplier getLatest();

}
