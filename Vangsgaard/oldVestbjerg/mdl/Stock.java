package mdl;

public class Stock {
	private int quantity;
	private Department department;
	
	public Stock(int quantity, Department department) {
		this.quantity = quantity;
		this.department = department;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
