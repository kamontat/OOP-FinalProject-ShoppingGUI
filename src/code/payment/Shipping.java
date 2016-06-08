package code.payment;

public class Shipping {
	private double totalWeight;
	private int shippingFee;
	private code.constant.Shipping shipping;

	public Shipping() {
		totalWeight = 0;
		shippingFee = 0;
		shipping = code.constant.Shipping.NONE;
	}

	public Shipping(double totalWeight, code.constant.Shipping shipping) {
		this.totalWeight = totalWeight;
		this.shipping = shipping;
		calculateShippingFee();
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public int getShippingFee() {
		return shippingFee;
	}

	public code.constant.Shipping getShipping() {
		return shipping;
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

		if (shipping.equals(code.constant.Shipping.REGISTER)) {
			if (totalWeight > 0 && totalWeight < 10) {
				shippingFee += 24;
			} else if (totalWeight < 20) {
				shippingFee += 50;
			} else {
				shippingFee += 72;
			}
		} else if (shipping.equals(code.constant.Shipping.EXPRESS)) {
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
		String registeredMail = shipping.equals(code.constant.Shipping.REGISTER) ? "Yes": "No";
		String expressMail = shipping.equals(code.constant.Shipping.EXPRESS) ? "Yes": "No";

		String format = "Total weight = %.3f, Registered mail = %s, Express mail = %s, Shipping fee = %d Baht";
		return String.format(format, this.totalWeight, registeredMail, expressMail, this.shippingFee);
	}
}
