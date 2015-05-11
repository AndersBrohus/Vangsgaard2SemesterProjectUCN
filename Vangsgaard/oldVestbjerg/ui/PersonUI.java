package UILag;
import ctrLag.*;
import modelLag.*;
import java.util.Scanner;

/**
 * Write a description of class FriendsUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PersonUI {
    // instance variables - replace the example below with your own
    private PersonCtr pCtr;

    /**
     * Constructor for objects of class FriendsUI
     */
    public PersonUI() {
        // initialise instance variables
        pCtr = new PersonCtr();
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void start() {
        personMenu();
    }

    public void personMenu() {
        boolean exit = false;
        while(!exit) { //that is: while exit is false

            int choise = writePersonMenu(); 
            if(choise == 1) {
                createPerson();
            }
            else if(choise == 2) {
                findPerson();
            }
            else if(choise == 3) {
                deletePerson();
            } 
            else if (choise ==4) {
                updatePerson();
            }
            else
            {exit = true;}
        }//end else    
    }//end while

    public int writePersonMenu() { // makes an object keyboard to read input from the screen
        Scanner keyboard = new Scanner(System.in);
        System.out.println("*** Friends Menu ***");
        System.out.println(" (1) Create Person");
        System.out.println(" (2) Find Person");
        System.out.println(" (3) Remove Person");
        System.out.println(" (4) Change Person info");
        System.out.println(" (5) Close");
        System.out.print("\n Make your choice: ");

        int choise = keyboard.nextInt();
        if(choise > 5 || choise == 0)
        {
            System.out.println("WTF.. You choose " + choise +" this is not a valid number.......");
            return writePersonMenu();
        }
        else {
            return choise;
        }
    }

    public String inputName()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the persons name: ");
        String name = keyboard.nextLine();
        return name;
    }

    public String inputAddress()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the address of the person: ");
        String address = keyboard.nextLine();
        return address;
    }

    public String inputCity()
    {
        // makes an object keyboard to read input from the console
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the city of the person: ");
        String city = keyboard.nextLine();
        return city;
    }

    public int inputPostalCode()
    {
        boolean ok = false;
        int postalCode = 0;
        while(!ok)
        {
            // makes an object keyboard to have input from the console
            Scanner keyboard = new Scanner(System.in);
            System.out.println(" Enter the postal code of the person: ");
            try{
                postalCode = keyboard.nextInt();
                ok = true;
            }
            catch (Exception e)
            {
                System.out.println("Input must be a number, please try again");
                String input = keyboard.nextLine();
            }
        }//end while
        return postalCode;
    }

    public String inputPhone()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println(" Enter the persons phone number: ");
        String phone = keyboard.nextLine();
        return phone;
    }

    private void createPerson()
    {
        String name = inputName();
        String address = inputAddress();
        String city = inputCity();
        int postalCode = inputPostalCode();
        String phone = inputPhone();

        pCtr.addPerson(name, address, city, postalCode, phone);
    }

    private Person findPerson(){
        String phone = inputPhone();
        Person per = pCtr.findPer(phone);

        try
        {
            per.printPersonInfo();
            return per;
        }
        catch(Exception e)
        {
            System.out.println("Error! Person is not registered in system."+"\n");
        }        
        return per;
    }

    public void deletePerson(){
        pCtr.printPersonList();
        System.out.println("Choose the person to delete");
        String phone = inputPhone();
        Person per = pCtr.findPer(phone);
        if (per !=null){
            System.out.println(per.getName()+" has been removed for the system.");
            pCtr.deletePerson(phone);
        }
        else{
            System.out.println("No person with that phonenumber");
        }
    }

    public void updatePerson(){
        pCtr.printPersonList();
        System.out.println("Choose the person to edit");
        String phone = inputPhone();
        Person per = pCtr.findPer(phone);
        if (per != null){
            per.printPersonInfo();
            System.out.println("Enter new info");
            String newName = inputName();
            String newAddress = inputAddress();
            String newCity = inputCity();
            int newPostalCode = inputPostalCode();
            String newPhone = inputPhone();
            pCtr.updatePerson(per,newName, newAddress, newPostalCode, newCity, newPhone);
        }
        else{
            System.out.println("Error! Person is not registered in system."+"\n");
        }
    }
    
}
