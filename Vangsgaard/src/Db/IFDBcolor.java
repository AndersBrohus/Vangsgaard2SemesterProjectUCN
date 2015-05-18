package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBcolor {
    public ArrayList<color> getAllColors();
    
    public color getColor(int id);
    
    //insert a new customer
    public color insertColor(color col) throws Exception;
    //update information about an customer
    public int updateColor(color col);
    public color getLatest();

}
