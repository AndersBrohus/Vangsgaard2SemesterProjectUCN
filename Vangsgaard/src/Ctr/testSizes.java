package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

public class testSizes {

	@Test
	public void test() {
		CtrSizes ctrSize = new CtrSizes();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrSize.insertNew("XL", "Bukser", 987654321);
				ctrSize.updateSize(1, "XXXL", "Tshirt", 987654321);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
