package ctr;

import mdl.*;

public class OrderCtrl {
	private OrderContainer oCon;
	
	public OrderCtrl()
	{
		oCon = OrderContainer.getInstance();
	}
	
	public int createOrder(Customer cus)
	{
		int ordNumber;
		Order ord = new Order(cus);
		oCon.createOrder(ord);
		ordNumber = ord.getOrderNumber();
		return ordNumber;
	}
	
	public Order getOrder(int orderNumber)
	{
		Order ord = oCon.getOrder(orderNumber);
		return ord;
	}
	
	public void findOrderList(Customer cus)
	{
		oCon.findOrderList(cus);
	}
	
	public void addPartOrder(int orderNumber, int amount, Product product)
	{
		Order ord = getOrder(orderNumber);
		ord.addPartOrder(amount, product);
	}
	
	public void deletePartOrder(int orderNumber, int partOrderId)
	{
		Order ord = getOrder(orderNumber);
		ord.deletePartOrder(partOrderId);
	}
	
	public void isFinished(int orderNumber)
	{
		Order ord = getOrder(orderNumber);
		ord.isFinished();
	}
}
