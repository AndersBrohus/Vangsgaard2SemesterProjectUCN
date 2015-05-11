package ctr;

import mdl.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestProduct {

	@Test
	public void test() {
		ProductCtrl pCtr = new ProductCtrl();
		
		pCtr.createProduct("Test", "Værktøj", 20);
		pCtr.createProduct("Test2", "Belysning", 200);
		pCtr.createProduct("Test3", "Køkken", 2000);
		pCtr.createProduct("Bla", "Træ", 2000);
		
		
		pCtr.findProduct("Te");
	
	}
}
