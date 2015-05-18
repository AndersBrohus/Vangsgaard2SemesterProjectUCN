package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

public class testColor {

	@Test
	public void test() {
		CtrColor ctrCol = new CtrColor();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrCol.insertNew("Cowboy Blå");
				ctrCol.updateColor(2, "Baby Blå");
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
