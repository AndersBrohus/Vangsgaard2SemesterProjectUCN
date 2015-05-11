package ctr;
import mdl.*;
import ctr.*;

import org.junit.Test;

public class TestCustomer {

	@Test
	public void test() {
		CustomerCtrl cCtr = new CustomerCtrl();
		
		cCtr.createCustomer("Anders", "test", "1", "test");
		cCtr.createCustomer("Anders", "test", "2", "test");
		cCtr.createCustomer("Anders", "Hest 2, 9000", "12345678", "blabla@test.dk");

		Customer cus = cCtr.findCustomer("12345678");
		
		System.out.println("The Customers name is " + cus.getName());
		System.out.println("The Customers address is " + cus.getAddress());
		System.out.println("The Customers phone is " + cus.getPhone());
		System.out.println("The Customers email is " + cus.getEmail());
		System.out.println("The Customers customer number is " + cus.getCustomerNumber());
		System.out.println("------------------------------------------");

		cCtr.updateCustomer(cus, "Karl", "Hest 3, 8000", "23456789", "test@bla.dk");
		Customer cus2 = cCtr.findCustomer("23456789");
		System.out.println("The Customers name is " + cus2.getName());
		System.out.println("The Customers address is " + cus2.getAddress());
		System.out.println("The Customers phone is " + cus2.getPhone());
		System.out.println("The Customers email is " + cus2.getEmail());
		System.out.println("The Customers customer number is " + cus2.getCustomerNumber());
		System.out.println("------------------------------------------");
	}

}
