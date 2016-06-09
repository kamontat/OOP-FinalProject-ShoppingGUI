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

	private int calculateShippingFee(double totalWeight) {
		int shippingFee = 0;
		int weight = (int) Math.ceil(totalWeight);

		if (weight > 0) {
			if (weight <= 10) {
				shippingFee += 10;
			}
			if (weight > 10 && weight <= 50) {
				shippingFee += 5 * (weight - 1);
			} else {
				shippingFee += 3 * (weight - 1);
			}
		}

		if (this.equals(Shipping.REGISTER)) {
			if (totalWeight > 0 && totalWeight < 10) {
				shippingFee += 24;
			} else if (totalWeight < 20) {
				shippingFee += 50;
			} else {
				shippingFee += 72;
			}
		} else if (this.equals(Shipping.EXPRESS)) {
			if (totalWeight > 0 && totalWeight < 10) {
				shippingFee += 55;
			} else if (totalWeight < 25) {
				shippingFee += 90;
			} else if (totalWeight < 50) {
				shippingFee += 345;
			} else {
				shippingFee += 780;
			}
		}
		return shippingFee;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice(double weight) {
		return calculateShippingFee(weight);
	}


	public static Shipping check(String shipping) {
		for (Shipping name : Shipping.values()) {
			if (name.getName().equals(shipping)) return name;
		}
		System.err.println("Don't have this " + shipping + " yet.");
		return null;
	}

	public String toString(double weight) {
		return name + " : " + calculateShippingFee(weight) + "฿";
	}
}
