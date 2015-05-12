package Mdl;

public class sizes {
	private int id;
	private String size;
	private String type;
	private customer customer;
	
	public sizes(int id, String size, String type, Mdl.customer customer) {
		super();
		this.id = id;
		this.size = size;
		this.type = type;
		this.customer = customer;
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
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the customer
	 */
	public customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(customer customer) {
		this.customer = customer;
	}
	
	
	
}
