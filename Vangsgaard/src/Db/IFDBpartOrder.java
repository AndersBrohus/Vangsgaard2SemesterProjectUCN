package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBpartOrder {
    public ArrayList<partOrder> getAllpartOrders();
    
    public partOrder getPartOrder(int id);
    
    public ArrayList<partOrder> findAllPartOrdersByOrder(int orderId);
    
    //insert a new customer
    public partOrder insertPartOrder(partOrder pOrd) throws Exception;
    //update information about an customer
    public int updatePartOrder(partOrder pOrd);
    public partOrder getLatest();

}
