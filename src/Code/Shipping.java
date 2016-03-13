package Code;

public class Shipping {
	private double totalWeight;
	private int shippingFee;
	private boolean registered;
	private boolean express;

	public Shipping() {
		totalWeight = 0;
		shippingFee = 0;
		registered = false;
		express = false;
	}

	public Shipping(double totalWeight, boolean registered, boolean express) {
		this.totalWeight = totalWeight;
		this.registered = registered;
		this.express = express;
		calculateShippingFee();
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public int getShippingFee() {
		return shippingFee;
	}

	public boolean isRegistered() {
		return registered;
	}

	public boolean isExpress() {
		return express;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public void setShippingFee(int shippingFee) {
		this.shippingFee = shippingFee;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public void setExpress(boolean express) {
		this.express = express;
	}

	public void calculateShippingFee() {
		int weight = (int) Math.ceil(this.totalWeight);

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
		// when registered true and express false
		if (registered && !express) {
			if (totalWeight > 0 && totalWeight < 10) {
				shippingFee += 24;
			} else if (totalWeight < 20) {
				shippingFee += 50;
			} else {
				shippingFee += 72;
			}

			// when registered false and express true
		} else if (!registered && express) {
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
	}

	public String toString() {
		String registeredMail = "No", expressMail = "No";
		if (registered) {
			registeredMail = "Yes";
		}

		if (express) {
			expressMail = "Yes";
		}

		String format = "Total weight = %.3f, Registered mail = %s, Express mail = %s, Shipping fee = %d Baht";
		return String.format(format, this.totalWeight, registeredMail, expressMail, this.shippingFee);
	}
}
