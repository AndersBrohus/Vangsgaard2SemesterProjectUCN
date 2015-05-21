package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

public class testInvoice {

	@Test
	public void test() {
		CtrInvoice ctrInvoice = new CtrInvoice();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				ctrInvoice.insertNew("adfdsa", 1000);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
