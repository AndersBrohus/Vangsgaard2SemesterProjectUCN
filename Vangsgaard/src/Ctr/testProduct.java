package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

import Mdl.*;
public class testProduct {

	@Test
	public void test() {
		CtrProduct ctrPro = new CtrProduct();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrPro.insertNew(100, 1, 1, 1, "Smarte bukser", "bukser", "Alt for store");
				ctrPro.updateProduct(3, 1000, 1, 1, 1, "Grimme bukser", "Bukser", "Alt for små");
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
