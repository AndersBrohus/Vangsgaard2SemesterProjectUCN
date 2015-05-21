package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBinvoice {
    public ArrayList<invoice> getAllInvoices();
    
    public invoice getInvoice(int id);
        
    //insert a new customer
    public invoice insertInvoice(invoice inv) throws Exception;
    //update information about an customer
    public int updateInvoice(invoice inv);
    public invoice getLatest();

}
