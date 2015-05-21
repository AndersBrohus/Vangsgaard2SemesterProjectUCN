package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

public class testStock {

	@Test
	public void test() {
		CtrStock ctrSto = new CtrStock();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrSto.insertNew(3, 10, 1);
				ctrSto.updateSize(1, 3, 5, 1);
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}
}
