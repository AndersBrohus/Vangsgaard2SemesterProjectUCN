package Mdl;

public class color {
	private int id;
	private String color;
	public color(int id, String color) {
		this.id = id;
		this.color = color;
	}
	
	public color() {
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
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString()
    { 
		String print = "Farve: " + color;
		return print;
	}
	
}
