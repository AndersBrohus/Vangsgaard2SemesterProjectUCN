package mdl;

public class Discount {
	private String title;
	private int discount;
	
	public Discount(String title, int discount) {
		this.title = title;
		this.discount = discount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
