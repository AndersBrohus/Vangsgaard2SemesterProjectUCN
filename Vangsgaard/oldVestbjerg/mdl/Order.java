package mdl;

import java.util.ArrayList;

public class Order {
	private static int counter = 1;
	private int orderNumber;
	private double totalPrice;
	private Customer customer;
	private ArrayList<PartOrder> partOrderList;
	private int partOrderCounter = 1;
	private boolean isFinished;
	
	public Order(Customer customer) 
	{
		this.orderNumber = counter++;
		totalPrice = 0;
		this.customer = customer;
		partOrderList = new ArrayList<PartOrder>();
		isFinished = false;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer Customer) {
		this.customer = Customer;
	}
	
	public void addPartOrder(int amount, Product product) {
		PartOrder pOrder = new PartOrder(partOrderCounter,amount, product);
		partOrderList.add(pOrder);
		totalPrice += pOrder.getAmount() * pOrder.getProduct().getPrice();
		if(customer.getDiscount() != 0)
		{			
			totalPrice = totalPrice - (totalPrice/= customer.getDiscount());
		}
		partOrderCounter++;
	}
	
	public void listPartOrder()
	{
		for(PartOrder pOrder : partOrderList)
		{
			System.out.println(pOrder.getProduct().getName() + " - " + pOrder.getAmount());
		}
	}
		
	public void deletePartOrder(int partOrderId)
	{
		int i=0;
        boolean found = false;
        while(i<partOrderList.size() && !found){
            PartOrder pOrder = partOrderList.get(i);
            int partId = pOrder.getID();
            if (partId == partOrderId){
                found = true;
            }
            else{
                i++;
            }
        }
        if (found){
        	partOrderList.remove(i);
        }
	}
	
	public void isFinished()
	{
		
		customer.setAmountSpend(totalPrice);	
		if(customer.getAmountSpend() >=  5000)
		{
			customer.setDiscount();
		}
		isFinished = true;
	}
	
	public ArrayList<PartOrder> getPartOrderList()
	{
		return partOrderList;
	}

}
