package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;
import Mdl.*;
public class testSupplier {

	@Test
	public void test() {
		CtrSupplier ctrSup = new CtrSupplier();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrSup.insertNew("Mike", 12345678);
				//ctrSup.updateSupplier(12345678, "Mauran", 87654321);
				
				supplier Mauran = ctrSup.findSupplierByPhone(87654321);
				
				System.out.println("Name : " + Mauran.getName() + " PhoneNr: " + Mauran.getPhone());
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}	}

}
