package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

public class testSalesOrder {

	@Test
	public void test() {
		CtrSalesOrder ctrSOrd = new CtrSalesOrder();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrSOrd.insertNew(100, 1, 1, "20-02-2015", "22-02-2015", true);
				//String PrintTest = "ID - " + ctrCus.getLatest().getId() + " -- Phone - " + ctrCus.getLatest().getPhone();
				//System.out.println(PrintTest);
				ctrSOrd.updateSalesOrder(2, 1000, 1, 1, "20-20-20", "21-21-21", true);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
