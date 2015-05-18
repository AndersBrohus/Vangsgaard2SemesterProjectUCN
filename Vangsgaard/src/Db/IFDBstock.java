package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBstock {
    public ArrayList<stock> getAllStocks();
    
    public stock getStock(int id);
        
    //insert a new customer
    public stock insertStock(stock sto) throws Exception;
    //update information about an customer
    public int updateStock(stock sto);
    public stock getLatest();

}
