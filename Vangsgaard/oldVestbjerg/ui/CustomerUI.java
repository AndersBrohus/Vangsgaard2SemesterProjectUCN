package ui;
import java.util.Scanner;

import ctr.*;
import mdl.*;

public class CustomerUI {
	private CustomerCtrl cCtr;
	
	public CustomerUI() {
		cCtr = new CustomerCtrl();
	}
	
	public void start()
	{
		customerMenu();
	}
	
	public void customerMenu() {
        boolean exit = false;
        while(!exit) { //that is: while exit is false

            int choise = writeCustomerMenu(); 
            if(choise == 1) {
            	createCustomer();
            }
            else if(choise == 2) {
            	findCustomer();
            }
            else if(choise == 3) {
            	getCustomer();
            } 
            else if(choise == 4) {
            	updateCustomer();
            } 
            else
            {exit = true;}
        }//end else    
    }//end while

    public int writeCustomerMenu() { // makes an object keyboard to read input from the screen
        Scanner keyboard = new Scanner(System.in);
        System.out.println("*** Customer Menu ***");
        System.out.println(" (1) Create Customer");
        System.out.println(" (2) Find Customer");
        System.out.println(" (3) Get Customer");
        System.out.println(" (4) Update Customer");
        System.out.println(" (5) Back");
        System.out.print("\n Make your choice: ");

        int choise = keyboard.nextInt();
        if(choise > 5 || choise == 0)
        {
            System.out.println("WTF.. You choose " + choise +" this is not a valid number.......");
            return writeCustomerMenu();
        }
        else {
            return choise;
        }
    }
    
    public String inputName()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the customers name: ");
        String name = keyboard.nextLine();
        return name;
    }
    
    public String inputAddress()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the customers address: ");
        String address = keyboard.nextLine();
        return address;
    }
    
    public String inputPhone()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the customers phone: ");
        String phone = keyboard.nextLine();
        return phone;
    }
    
    public String inputEmail()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the customers email: ");
        String email = keyboard.nextLine();
        return email;
    }
    
    public int inputCustomerNumber()
    {
        boolean ok = false;
        int customerNumber = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter the customer number :");
            try{
            	customerNumber = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return customerNumber;
    }
    
    public void createCustomer() {
    	String name = inputName();
        String address = inputAddress();
        String email = inputEmail();
        String phone = inputPhone();
        
        cCtr.createCustomer(name, address, phone, email);
	}
    
    public Customer findCustomer()
    {
    	String phone = inputPhone();
        Customer cus = cCtr.findCustomer(phone);

        try
        {
    		System.out.println("------------------------------------------");
        	System.out.println("The Customers name is " + cus.getName());
    		System.out.println("The Customers address is " + cus.getAddress());
    		System.out.println("The Customers phone is " + cus.getPhone());
    		System.out.println("The Customers email is " + cus.getEmail());
    		System.out.println("The Customers customer number is " + cus.getCustomerNumber());
    		System.out.println("------------------------------------------");
            return cus;
        }
        catch(Exception e)
        {
            System.out.println("Error! Customer is not registered in system."+"\n");
        }        
        return cus;
    }
    
    public Customer getCustomer()
    {
    	int cusNumber = inputCustomerNumber();
        Customer cus = cCtr.getCustomer(cusNumber);

        try
        {
    		System.out.println("------------------------------------------");
        	System.out.println("The Customers name is " + cus.getName());
    		System.out.println("The Customers address is " + cus.getAddress());
    		System.out.println("The Customers phone is " + cus.getPhone());
    		System.out.println("The Customers email is " + cus.getEmail());
    		System.out.println("The Customers customer number is " + cus.getCustomerNumber());
    		System.out.println("------------------------------------------");
            return cus;
        }
        catch(Exception e)
        {
            System.out.println("Error! Customer is not registered in system."+"\n");
        }        
        return cus;
    }
    
    public void updateCustomer(){
        String phone = inputPhone();
        Customer cus = cCtr.findCustomer(phone);
        if (cus != null){
        	System.out.println("------------------------------------------");
        	System.out.println("The Customers name is " + cus.getName());
    		System.out.println("The Customers address is " + cus.getAddress());
    		System.out.println("The Customers phone is " + cus.getPhone());
    		System.out.println("The Customers email is " + cus.getEmail());
    		System.out.println("The Customers customer number is " + cus.getCustomerNumber());
    		System.out.println("------------------------------------------");
            System.out.println("Enter new info");           
            String newName = inputName();
            String newAddress = inputAddress();
            String newEmail = inputEmail();
            String newPhone = inputPhone();
            cCtr.updateCustomer(cus, newName, newAddress, newPhone, newEmail);
        }
        else{
            System.out.println("Error! Customer is not registered in system."+"\n");
        }
    }
}
