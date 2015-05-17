package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.DbConnection;

public class testCustomer {

	@Test
	public void test() {
		CtrCustomer ctrCus = new CtrCustomer();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrCus.insertNew("dsadsadsafsfs", "Vesterbro", 828888888, "Mike@Mike.dk", 1);
				//String PrintTest = "ID - " + ctrCus.getLatest().getId() + " -- Phone - " + ctrCus.getLatest().getPhone();
				//System.out.println(PrintTest);
				
				ctrCus.updateCustomer(12345,"Carl Mar Møller 2", "Østerbro 2", 987654321, "Carlmar@møller.dk", 1);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
