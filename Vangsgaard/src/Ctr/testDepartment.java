package Ctr;

import static org.junit.Assert.*;

import org.junit.Test;

import Db.*;

public class testDepartment {

	@Test
	public void test() {
		CtrDepartment ctrDpart = new CtrDepartment();
		DbConnection dbCon = DbConnection.getInstance();
		if(dbCon != null)
		{
			try
			{
				//ctrDpart.insertNew("Taadaaaa");
				ctrDpart.updateDepartment(1, "Taadaaaa");
			}
			catch(Exception e){
		    	System.out.println(e.getMessage());
		    }
		}
	}

}
