package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBsalesOrder {
    public ArrayList<salesOrder> getAllSalesOrders();
    
    public salesOrder getSalesOrder(int id);
    
    public ArrayList<salesOrder> findAllSalesOrdersByCustomer(int phone);
    
    //insert a new customer
    public salesOrder insertSalesOrder(salesOrder sOrd) throws Exception;
    //update information about an customer
    public int updateSalesOrder(salesOrder sOrd);
    public salesOrder getLatest();

	public int insertSalesOrderWithId(salesOrder sOrdObj) throws Exception;

}
