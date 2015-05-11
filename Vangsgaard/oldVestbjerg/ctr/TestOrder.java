package ctr;

import mdl.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestOrder {

	@Test
	public void test() {
		ProductCtrl pCtr = new ProductCtrl();
		OrderCtrl oCtr = new OrderCtrl();
		CustomerCtrl cCtr = new CustomerCtrl();
		
		pCtr.createProduct("Hammer", "Værktøj", 10);
		pCtr.createProduct("Lampe", "Belysning", 200);
		pCtr.createProduct("Vask", "Køkken", 2);
		pCtr.createProduct("Nordmandsgran", "Træ", 2000);
		
		cCtr.createCustomer("Hans", "Hansenvej 1", "12345678", "Hans@hansen.dk");
		cCtr.createCustomer("Jens", "Jensengade 3", "87654321", "Jens@jensen.dk");
		
		Customer cus1 = cCtr.getCustomer(1);
		Customer cus2 = cCtr.getCustomer(2);

		oCtr.createOrder(cus1);
		oCtr.addPartOrder(1, 4, pCtr.getProduct(4));
		
		oCtr.isFinished(1);
		
		System.out.println(cus1.getDiscount());
		
		oCtr.createOrder(cus1);
		oCtr.addPartOrder(2, 4, pCtr.getProduct(4));
		
		System.out.println(oCtr.getOrder(2).getTotalPrice());
		
		oCtr.findOrderList(cus1);

	}

}
