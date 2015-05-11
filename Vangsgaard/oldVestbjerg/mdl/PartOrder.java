package mdl;

public class PartOrder {
	private int id;
	private int amount;
	private Product product;
	
	public PartOrder(int id, int amount, Product product) {
		this.id = id;
		this.amount = amount;
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public int getID()
	{
		return id;
	}
}
