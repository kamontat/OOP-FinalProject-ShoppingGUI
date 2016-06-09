package code.store;

import code.constant.Shipping;
import code.customer.Customer;
import code.payment.Payment;

import java.util.ArrayList;

public class Order {
	private ArrayList<OrderElement> buyList = new ArrayList<>();
	private Customer customer;
	private Shipping shipping;
	private Payment payment;
	private int orderID;
	private static int numOrders;

	public Order(Customer customer, Shipping shipping) {
		numOrders++;
		this.buyList.addAll(customer.getBasketList());

		this.customer = customer;
		this.shipping = shipping;

		payment = new Payment(customer.getPrice(), customer.getMemberClass().getDiscount(), shipping.getPrice(customer.getWeight()));

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
		return new Order(customer.clone(), shipping);
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