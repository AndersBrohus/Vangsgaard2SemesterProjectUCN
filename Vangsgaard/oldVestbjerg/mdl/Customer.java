package mdl;

public class Customer {
	//Counter for making unique customer numbers.
	private static int counter = 1;
	
	private int customerNumber;
	private String name;
	private String address;
	private String phone;
	private String email;
	private Discount discount;
	private double amountSpend;
	
	public Customer(String name, String address, String phone, String email) {
		this.customerNumber = counter++;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;		
		Discount discount = new Discount("Zero",0);
		this.discount = discount;
		amountSpend = 0;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getDiscount(){
		return discount.getDiscount();
	}
	
	public void setDiscount(){
		discount.setTitle("Ten");
		discount.setDiscount(10);
	}

	public double getAmountSpend() {
		return amountSpend;
	}

	public void setAmountSpend(double amountSpend) {
		this.amountSpend += amountSpend;
	}
	
}