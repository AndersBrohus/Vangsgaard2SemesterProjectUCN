package Db;

import java.util.ArrayList;

import Mdl.*;

public interface IFDBdepartment {
    public ArrayList<department> getAllDepartments();
    
    public department getDepartment(int id);
    
    //insert a new customer
    public department insertDepartment(department dpart) throws Exception;
    //update information about an customer
    public int updateDepartment(department dpart);
    public department getLatest();

}
