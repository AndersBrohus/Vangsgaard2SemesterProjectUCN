package mdl;

public class Department {
	private String title;
	private String address;
	
	public Department(String title, String address) {
		this.title = title;
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
