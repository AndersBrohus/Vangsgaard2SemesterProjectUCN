package Mdl;

public class customer {
	private int id;
	private String name;
	private String Address;
	private int phone;
	private String email;
	private int customerType;
	
	public customer()
	{
		
	}
	
	public customer(int id, String name, String address, int phone,
			String email, int customerType) {
		super();
		this.id = id;
		this.name = name;
		Address = address;
		this.phone = phone;
		this.email = email;
		this.customerType = customerType;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}

	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the customerType
	 */
	public int getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	
	
}
