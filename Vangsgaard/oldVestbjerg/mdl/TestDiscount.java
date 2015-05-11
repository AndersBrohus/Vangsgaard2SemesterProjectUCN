package mdl;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDiscount {

	@Test
	public void test() {
		Customer test = new Customer("test","test","test","test"); 
		Customer test2 = new Customer("test","test","test","test"); 
		test2.setDiscount();
		Customer test3 = new Customer("test","test","test","test"); 
		Customer test4 = new Customer("test","test","test","test"); 
		test4.setDiscount();
		Customer test5 = new Customer("test","test","test","test"); 

		System.out.println("Your customernumber is : " + test.getCustomerNumber());
		System.out.println("Your discount : " + test.getDiscount() + "%");
		System.out.println("Your customernumber is : " + test2.getCustomerNumber());
		System.out.println("Your discount : " + test2.getDiscount() + "%");
		System.out.println("Your customernumber is : " + test3.getCustomerNumber());
		System.out.println("Your discount : " + test3.getDiscount() + "%");
		System.out.println("Your customernumber is : " + test4.getCustomerNumber());
		System.out.println("Your discount : " + test4.getDiscount() + "%");
		System.out.println("Your customernumber is : " + test5.getCustomerNumber());
		System.out.println("Your discount : " + test5.getDiscount() + "%");
	}
}
