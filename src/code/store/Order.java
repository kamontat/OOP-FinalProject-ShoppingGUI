package code.store;

import code.customer.Customer;
import code.payment.Payment;
import code.payment.Shipping;

import java.util.ArrayList;

public class Order {
	private ArrayList<OrderElement> buyList = new ArrayList<>();
	private Customer customer;
	private Shipping shipping;
	private Payment payment;
	private int orderID;
	private static int numOrders;

	public Order() {
		numOrders++;
		customer = null;
		shipping = null;
		payment = null;
		orderID = numOrders;
	}

	public Order(Customer customer, boolean registered, boolean express) {
		numOrders++;
		this.buyList.addAll(customer.getBasketList());
		this.customer = customer;
		double discountRate = getDiscountRate();
		shipping = new Shipping(customer.getBasketTotalWeight(), registered, express);
		payment = new Payment(customer.getBasketTotalPrice(), discountRate, shipping.getShippingFee());
		orderID = numOrders;
	}

	public ArrayList<OrderElement> getBuyList() {
		return buyList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public Payment getPayment() {
		return payment;
	}

	public int getOrderID() {
		return orderID;
	}

	public static int getNumOrders() {
		return numOrders;
	}

	public void setBuyList(ArrayList<OrderElement> buyList) {
		this.buyList = buyList;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public static void setNumOrders(int numOrders) {
		Order.numOrders = numOrders;
	}

	public Order clone() {
		numOrders--;

		Order order = new Order();

		for (int i = 0; i < this.buyList.size(); i++) {
			order.buyList.add(this.buyList.get(i));
		}

		order.customer = this.customer;
		order.shipping = this.shipping;
		order.payment = this.payment;
		order.orderID = this.orderID;
		return order;
	}

	public double getDiscountRate() {
		double discountRate = 0;
		if (customer.getMemberClass().equals("Gold")) {
			discountRate = 0.2;
		} else if (customer.getMemberClass().equals("Silver")) {
			discountRate = 0.1;
		} else if (customer.getMemberClass().equals("Green")) {
			discountRate = 0.05;
		} else if (customer.getAge() >= 60) {
			discountRate = 0.01;
		}
		return discountRate;
	}

	public String toString() {
		String output = "";
		output += shipping.toString();
		output += "\n";
		output += payment.toString();
		output += "\n";
		return output;
	}
}