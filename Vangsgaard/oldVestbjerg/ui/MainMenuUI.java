package ui;
import java.util.Scanner;


/**
 * Write a description of class MainMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MainMenuUI
{
    public CustomerUI customer;
    public OrderUI order;
    public ProductUI product;
    /**
     * Constructor for objects of class MainMenu
     */
    public MainMenuUI()
    {
    	customer = new CustomerUI();
    	order = new OrderUI();
    	product = new ProductUI();
    }
    
    public static void main(String[] args) {
		MainMenuUI main = new MainMenuUI();
		main.start();
	}
    
    public void start()
    {
        mainMenu();
    }
    public void mainMenu()
    {
       boolean exit = false;
       while(!exit)//that is: while exit is false
       {
          int choise = writeMainMenu(); 
          if(choise == 1)
          {
              customer.start();
          }
          
          else if(choise == 2)
          {
              order.start();
          }
          
          else if(choise == 3)
          {
             product.start();
          }          
          else
          {exit = true;}
         }//end else    
       }//end while
       
   public int writeMainMenu()
   {      // makes an object keyboard to read input from the screen
            Scanner keyboard = new Scanner(System.in);
            System.out.println("*** Main Menu ***");
            System.out.println(" (1) Customer");
            System.out.println(" (2) Order");
            System.out.println(" (3) Product");
            System.out.println(" (4) Close");
            System.out.print("\n Make your choice: ");
            
            int choise = keyboard.nextInt();
            if(choise > 4 || choise == 0)
            {
               System.out.println("WTF.. You choose " + choise +" this is not a valid number.......");
               return writeMainMenu();
            }
            return choise;
   }
}
