package mdl;

import java.util.ArrayList;

public class Product {
	//Counter for making unique product numbers.
		private static int counter = 1;
		
	private int productNumber;
	private String name;
	private String type;
	private double price;
	private ArrayList<Stock> stockList;
	
	public Product(String name, String type, double price) {
		this.productNumber = counter++;
		this.name = name;
		this.type = type;
		this.price = price;
		stockList = new ArrayList<Stock>();
	}

	public int getProductNumber() {
		return productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
<<<<<<< .mine
	
	public String toString() {
		String pro = name + " - " + price + " - " + type; 
	    return pro;
	}
=======
	
	public void addStock(int quantity, Department department) {
		if(stockList.contains(department))
		{
			for(Stock stock : stockList)
			{
				if (stock.getDepartment() == department)
				{
					int sq = stock.getQuantity();
					stock.setQuantity(sq += quantity);
				}
			}
		}
		else
		{
			Stock stock = new Stock(quantity,department);
			stockList.add(stock);
		}
	}
	
	public void listProductStock()
	{
		for(Stock stock : stockList)
		{
			System.out.println(stock.getDepartment() + " - " + stock.getQuantity());
		}
	}
	
	public void printDepartmentStock(Department department)
	{
		if(stockList.contains(department))
		{
			for(Stock stock : stockList)
			{
				if (stock.getDepartment() == department)
				{
					System.out.println(productNumber + " - " + name + " - " + stock.getQuantity());
				}
			}
		}
		else
		{
			System.out.println("No departments with that name exist in the system");
		}
	}
	
	public void deleteStockDepartment(Department department)
	{
		int i=0;
        boolean found = false;
        while(i<stockList.size() && !found){
        	Stock stock = stockList.get(i);
            Department stockDepartment = stock.getDepartment();
            if (stockDepartment == department){
                found = true;
            }
            else{
                i++;
            }
        }
        if (found){
        	stockList.remove(i);
        }
	}
	
	public void RemoveStock(int quantity, Department department)
	{
		int i=0;
        boolean found = false;
        while(i<stockList.size() && !found){
        	Stock stock = stockList.get(i);
            Department stockDepartment = stock.getDepartment();
        	int stockQuantity = stock.getQuantity();
            if (stockDepartment == department){
            	int q = stockQuantity -= quantity;
                stock.setQuantity(q);
                found = true;
            }
            else{
                i++;
            }
        }
	}
	
	public String toString() {
		String pro = name + " - " + price + " - " + type; 
	    return pro;
	}
>>>>>>> .r27
}
