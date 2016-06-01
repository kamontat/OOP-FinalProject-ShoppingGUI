package code.constant;

/**
 * @author kamontat
 * @since 1/6/59 - 19:37
 */
public enum Shipping {
	NONE("None"),
	REGISTER("Register"),
	EXPRESS("Express");

	private String name;
	private double price;

	private Shipping(String name) {
		this.name = name;
		if (name.equals("None")) {
			price = 0;
		} else if (name.equals("Register")) {
			price = 30;
		} else if (name.equals("Express")) {
			price = 50;
		}
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return name + " : " + price + "฿";
	}
}
