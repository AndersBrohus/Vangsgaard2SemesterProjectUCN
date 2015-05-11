package ui;
import java.util.Scanner;

import ctr.*;
import mdl.*;

public class ProductUI {
	private ProductCtrl pCtr;
	
	public ProductUI() {
		pCtr = new ProductCtrl();
	}
	
	public void start()
	{
		productMenu();
	}
	
	public void productMenu() {
        boolean exit = false;
        while(!exit) { //that is: while exit is false

            int choise = writeProductMenu(); 
            if(choise == 1) {
            	createProduct();
            }
            else if(choise == 2) {
            	deleteProduct();
            }
            else if(choise == 3) {
            	getProduct();
            }
            else if(choise == 4) {
            	findProduct();
            }
            else if(choise == 5) {
            	updateProduct();
            }
            else if(choise == 6) {
            	printAllProducts();
            }
            else
            {exit = true;}
        }//end else    
    }//end while

    public int writeProductMenu() { // makes an object keyboard to read input from the screen
        Scanner keyboard = new Scanner(System.in);
        System.out.println("*** Product Menu ***");
        System.out.println(" (1) Create Product");
        System.out.println(" (2) Delete Product");
        System.out.println(" (3) Get Product");
        System.out.println(" (4) Find Products");
        System.out.println(" (5) Update Product");
        System.out.println(" (6) Print all products");
        System.out.println(" (7) Back");
        System.out.print("\n Make your choice: ");

        int choise = keyboard.nextInt();
        if(choise > 7 || choise == 0)
        {
            System.out.println("WTF.. You choose " + choise +" this is not a valid number.......");
            return writeProductMenu();
        }
        else {
            return choise;
        }
    }
    
    public String inputProductName()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the product name: ");
        String proName = keyboard.nextLine();
        return proName;
    }
    
    public String inputType()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the product type: ");
        String type = keyboard.nextLine();
        return type;
    }
    
    public double inputPrice()
    {
        boolean ok = false;
        double price = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter the price :");
            try{
            	price = keyboard.nextDouble();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return price;
    }
    
    public int inputProductNumber()
    {
        boolean ok = false;
        int productNumber = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter the product number :");
            try{
            	productNumber = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return productNumber;
    }
    
    public void createProduct() {
    	String proName = inputProductName();
        String type = inputType();
        double price = inputPrice();
        
        pCtr.createProduct(proName, type, price);
	}
    
    public void deleteProduct()
    {
    	int proNumber = inputProductNumber();
        Product pro = pCtr.getProduct(proNumber);
        if (pro !=null){
            System.out.println(pro.getName() + " has been removed for the system.");
            pCtr.deleteProduct(proNumber);
        }
        else{
            System.out.println("No product with that product number");
        }
    }
    
    public void findProduct()
    {
    	String proName = inputProductName();
    	try
    	{
        	pCtr.findProduct(proName);
    	}
    	catch(Exception e)
    	{
            System.out.println("Error! No Products is found."+"\n");
    	}
    }
    
    public Product getProduct()
    {
    	int proNumber = inputProductNumber();
        Product pro = pCtr.getProduct(proNumber);

        try
        {
    		System.out.println("------------------------------------------");
        	System.out.println("The product name is " + pro.getName());
    		System.out.println("The product type is " + pro.getType());
    		System.out.println("The product price is " + pro.getPrice());
    		System.out.println("------------------------------------------");
            return pro;
        }
        catch(Exception e)
        {
            System.out.println("Error! Product is not registered in system."+"\n");
        }        
        return pro;
    }
    
    public void updateProduct(){
        int proNumber = inputProductNumber();
        Product pro = pCtr.getProduct(proNumber);
        if (pro != null){
    		System.out.println("------------------------------------------");
        	System.out.println("The product name is " + pro.getName());
    		System.out.println("The product type is " + pro.getType());
    		System.out.println("The product price is " + pro.getPrice());
    		System.out.println("------------------------------------------");
            System.out.println("Enter new info");           
            String newName = inputProductName();
            String newType = inputType();
            double newPrice = inputPrice();
            pCtr.updateProduct(pro, newName, newType, newPrice);
        }
        else{
            System.out.println("Error! Product is not registered in system."+"\n");
        }
    }
    
    public void printAllProducts()
    {
    	pCtr.printProductList();
    }
}
