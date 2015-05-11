package ctr;

import java.util.ArrayList;

import mdl.*;

public class ProductCtrl {
	private ProductContainer pCon;
	private ArrayList<Product> pList;
	
	public ProductCtrl() {
		pCon = ProductContainer.getInstance();
		pList = new ArrayList<Product>();
	}
	
	public void createProduct(String name, String type, double price)
	{
		Product pro = new Product(name,type,price);
		pCon.createProduct(pro);
	}
	
	public void findProduct(String name)
	{
		pCon.findProduct(name);
	}
	
	public ArrayList<Product> findProductGUI(String name)
	{
		pList = pCon.findProductGUI(name);
		return pList;
	}
	
	public Product getProduct(int productNumber)
	{
		Product pro = pCon.getProduct(productNumber);
		return pro;
	}
	
	public void updateProduct(Product pro, String name, String type, double price)
	{
		pro.setName(name);
		pro.setType(type);
		pro.setPrice(price);
	}
	
	public void deleteProduct(int productNumber)
	{
		pCon.deleteProduct(productNumber);
	}
	
	public void printProductList()
	{
		pCon.printProductList();
	}
	
	public void printDepartmentStock(Department department)
	{
		pCon.printDepartmentStock(department);
	}
	
	public void addStock(Product pro, int quantity, Department department)
	{
		pro.addStock(quantity, department);
	}
	
	public void deleteStockDepartment(Product pro, Department department)
	{
		pro.deleteStockDepartment(department);
	}
	
	public void RemoveStock(Product pro, int quantity, Department department)
	{
		pro.RemoveStock(quantity, department);
	}
	
	public ArrayList<Product> findProductGUI(String name)
	{
		pList = pCon.findProductGUI(name);
		return pList;
	}
}
