package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.DbConnection;

public class testCustomerType {

	@Test
	public void test() {
		CtrCustomerType ctrCType = new CtrCustomerType();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrCType.insertNew("Dubble VIP", 50);
				ctrCType.updateCustomerType(2, "WOW What a VIP!", 75);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
