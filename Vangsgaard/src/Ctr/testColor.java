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
				//ctrCol.insertNew("Cowboy Bl�");
				ctrCol.updateColor(2, "Baby Bl�");
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
