package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.DbConnection;

public class testPartOrder {

	@Test
	public void test() {
		CtrPartOrder ctrPOrd = new CtrPartOrder();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrPOrd.insertNew(3, 1, 2, 1);
				ctrPOrd.updateSalesOrder(1, 3, 1, 3, 1);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
