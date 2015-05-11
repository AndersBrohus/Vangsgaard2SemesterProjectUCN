package mdl;

import java.util.ArrayList;

public class OrderContainer {
	private static OrderContainer instance;
	private ArrayList<Order> orderList;
	
	private OrderContainer() {
		orderList = new ArrayList<Order>();
	}
	
	public static OrderContainer getInstance()
	{
		if(instance == null){
			instance = new OrderContainer();
		}
		return instance;
	}
	
	public void createOrder(Order ord)
	{
		orderList.add(ord);
	}
	
	public Order getOrder(int orderNumber)
	{
		int i=0;
        boolean found = false;
        Order ord = null;
        while(i<orderList.size() && !found){
        	Order order = orderList.get(i);
            int number = order.getOrderNumber();
            if (number == orderNumber){
                found = true;
                ord = orderList.get(i);
                
            }
            else{
                i++;
            }
        }
        return ord;
	}
	
	/*public String findOrderList(Customer cus)
	{
		ArrayList<Order> oList = new ArrayList<Order>();
		 for (Order o : orderList) 
		 {
			if(o.getCustomer().equals(cus))
			{
				oList.add(o);
			}
		 }
		 
		 System.out.println("Order Number - Price \n");
		 for(Order o : oList)
		 {
			 System.out.println(o.getOrderNumber() + " - " + o.getTotalPrice() + "kr");
			 System.out.println("Order list \n");
			 System.out.println("Product - Amount \n");
			 o.listPartOrder();
		 }
		 return "";
	}*/
	
	public void findOrderList(Customer cus)
	{
		ArrayList<Order> oList = new ArrayList<Order>();
		 for (Order o : orderList) 
		 {
			if(o.getCustomer().equals(cus))
			{
				oList.add(o);
			}
		 }
		 
		 if(oList.size() > 0)
		 {
			 System.out.println("Order Number - Price \n");
			 for(Order o : oList)
			 {
				 System.out.println(o.getOrderNumber() + " - " + o.getTotalPrice() + "kr");
				 System.out.println("Order list \n");
				 System.out.println("Product - Amount \n");
				 for(PartOrder pOrder : o.getPartOrderList())
					{
						System.out.println(pOrder.getProduct().getName() + " - " + pOrder.getAmount() + " - " + pOrder.getID());
					}
			 }
		 }
		 else if(oList.isEmpty())
		 {
			 System.out.println("No orders in the system.");
		 }
	}
}
