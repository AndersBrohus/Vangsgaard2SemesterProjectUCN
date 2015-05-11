package ui;
import java.util.Scanner;

import ctr.*;
import mdl.*;

public class OrderUI {
	private OrderCtrl oCtr;
	private CustomerCtrl cCtr;
	private ProductCtrl pCtr;
	
	public OrderUI() {
		oCtr = new OrderCtrl();
		cCtr = new CustomerCtrl();
		pCtr = new ProductCtrl();
	}
	
	public void start()
	{
		orderMenu();
	}
	
	public void orderMenu() {
        boolean exit = false;
        while(!exit) { //that is: while exit is false

            int choise = writeOrderMenu(); 
            if(choise == 1) {
            	createOrder();
            }
            else if(choise == 2) {
            	findOrderList();
            }
            else if(choise == 3) {
            	getOrder();
            }
            else
            {exit = true;}
        }//end else    
    }//end while

    public int writeOrderMenu() { // makes an object keyboard to read input from the screen
        Scanner keyboard = new Scanner(System.in);
        System.out.println("*** Order Menu ***");
        System.out.println(" (1) Create Order");
        System.out.println(" (2) Find Order List");
        System.out.println(" (3) Get Order");
        System.out.println(" (4) Back");
        System.out.print("\n Make your choice: ");

        int choise = keyboard.nextInt();
        if(choise > 4 || choise == 0)
        {
            System.out.println("WTF.. You choose " + choise +" this is not a valid number.......");
            return writeOrderMenu();
        }
        else {
            return choise;
        }
    }
    
    public int writePartOrderMenu()
    {
    	Scanner keyboard = new Scanner(System.in);
        System.out.println("*** Part Order Menu ***");
        System.out.println(" (1) Add Part Order");
        System.out.println(" (2) Delete Part Order");
        System.out.println(" (3) Back");
        System.out.print("\n Make your choice: ");

        int choise = keyboard.nextInt();
        if(choise > 3 || choise == 0)
        {
            System.out.println("WTF.. You choose " + choise +" this is not a valid number.......");
            return writePartOrderMenu();
        }
        else {
            return choise;
        }
    }
    
    public String inputPhone()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the customers phone: ");
        String phone = keyboard.nextLine();
        return phone;
    }
    
    public String inputProductName()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the product name: ");
        String proName = keyboard.nextLine();
        return proName;
    }
    
    public int inputOrderNumber()
    {
        boolean ok = false;
        int orderNumber = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter the order number :");
            try{
            	orderNumber = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return orderNumber;
    }
    
    public int inputAmount()
    {
        boolean ok = false;
        int amount = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter amount:");
            try{
            	amount = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return amount;
    }
    
    public int inputPartOrderId()
    {
        boolean ok = false;
        int pOrderId = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter Par tOrder Id:");
            try{
            	pOrderId = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return pOrderId;
    }
    
    public int inputProNumber()
    {
        boolean ok = false;
        int proNumber = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter product number:");
            try{
            	proNumber = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return proNumber;
    }
    
    public void createOrder()
    {
    	String phone = inputPhone();
    	Customer cus = cCtr.findCustomer(phone);
    	Order ord = oCtr.getOrder(oCtr.createOrder(cus));
        System.out.println("Order has been created with order number: " + ord.getOrderNumber());
        boolean exit = false;
        while(!exit)
        {
        	int choise = writePartOrderMenu(); 
            if(choise == 1) {
            	pCtr.printProductList();
            	System.out.println("---------------------");
            	int amount = inputAmount();
            	int proNumber = inputProNumber();
            	try
            	{            	
            		Product pro = pCtr.getProduct(proNumber);
            		ord.addPartOrder(amount, pro);
            	}
            	catch(Exception e)
            	{
                	System.out.println("There are no product with that product number");
            	}
            }
            else if(choise == 2) {
            	ord.listPartOrder();
            	System.out.println("---------------------");
            	int pOrderId = inputPartOrderId();
            	try
            	{
            		ord.deletePartOrder(pOrderId);
            	}
            	catch(Exception e)
            	{
            		System.out.println("The part order with the id " + pOrderId + " is now deleted.");
            	}

            }
            else
            {
            	ord.isFinished();
            	exit = true;
            }
        }
    }
    
    public void findOrderList()
    {
    	String phone = inputPhone();
    	Customer cus = cCtr.findCustomer(phone);
    	oCtr.findOrderList(cus);
    }
    
    public void getOrder()
    {
    	int ordNumber = inputOrderNumber();
    	Order ord = oCtr.getOrder(ordNumber);
    	System.out.println("---------------------");
    	try
    	{
    		ord.listPartOrder();
    		System.out.println("Total price " + ord.getTotalPrice() + "kr");
    		System.out.println("---------------------");
    	}
    	catch(Exception e)
    	{
            System.out.println("Error! Order is not registered in system.");
            System.out.println("---------------------");
    	}
    	
    }
}
