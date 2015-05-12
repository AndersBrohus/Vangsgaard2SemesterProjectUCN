package Mdl;

public class partOrder {
	private int id;
	private product product;
	private salesOrder salesOrder;
	private int amount;
	private department departmentId;
	
	public partOrder(int id, Mdl.product product, Mdl.salesOrder salesOrder,
			int amount, department departmentId) {
		super();
		this.id = id;
		this.product = product;
		this.salesOrder = salesOrder;
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
	 * @return the salesOrder
	 */
	public salesOrder getSalesOrder() {
		return salesOrder;
	}

	/**
	 * @param salesOrder the salesOrder to set
	 */
	public void setSalesOrder(salesOrder salesOrder) {
		this.salesOrder = salesOrder;
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
	 * @return the departmentId
	 */
	public department getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(department departmentId) {
		this.departmentId = departmentId;
	}
}
