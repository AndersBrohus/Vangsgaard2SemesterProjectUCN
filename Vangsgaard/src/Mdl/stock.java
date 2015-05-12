package Mdl;

public class stock {
	private int id;
	private product product;
	private int amount;
	private department department;
	
	public stock(int id, Mdl.product product, int amount,
			Mdl.department department) {
		super();
		this.id = id;
		this.product = product;
		this.amount = amount;
		this.department = department;
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
	 * @return the product
	 */
	public product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(product product) {
		this.product = product;
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
	/**
	 * @return the department
	 */
	public department getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(department department) {
		this.department = department;
	}
	
	
}
