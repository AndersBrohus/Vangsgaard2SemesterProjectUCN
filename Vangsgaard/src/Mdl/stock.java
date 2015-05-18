package Mdl;

public class stock {
	private int id;
	private int productId;
	private int amount;
	private int departmentId;
	
	public stock() {
		// TODO Auto-generated constructor stub
	}
	
	public stock(int id, int productId, int amount, int departmentId) {
		super();
		this.id = id;
		this.productId = productId;
		this.amount = amount;
		this.departmentId = departmentId;
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
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	
	
	
}
