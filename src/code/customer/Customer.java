package code.customer;

import code.store.Order;
import code.store.OrderElement;
import code.product.ProductExt;

import java.util.*;

public class Customer extends Person {
	private String customerID;
	private String memberClass;
	private ArrayList<OrderElement> basket;
	private ArrayList<Order> historyList;
	private static int numCustomers;

	public Customer() {
		super();
		numCustomers++;
		memberClass = "None";
		basket = new ArrayList<OrderElement>();
		historyList = new ArrayList<Order>();
		customerID = "Member" + numCustomers;
	}

	public Customer(String ID, String name, String lastname, String gender, int age, String memberClass) {
		super(ID, name, lastname, gender, age);
		numCustomers++;
		this.memberClass = memberClass;
		basket = new ArrayList<OrderElement>();
		historyList = new ArrayList<Order>();
		customerID = "Member" + numCustomers;
	}

	public Customer(String ID, String name, String lastname, String gender, String age, String memberClass) {
		super(ID, name, lastname, gender, Integer.parseInt(age));
		numCustomers++;
		this.memberClass = memberClass;
		basket = new ArrayList<OrderElement>();
		historyList = new ArrayList<Order>();
		customerID = "Member" + numCustomers;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getMemberClass() {
		return memberClass;
	}

	public ArrayList<OrderElement> getBasket() {
		return basket;
	}

	public ArrayList<Order> getHistoryList() {
		return historyList;
	}

	public static int getNumCustomers() {
		return numCustomers;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public void setMemberClass(String memberClass) {
		this.memberClass = memberClass;
	}

	public void setBasket(ArrayList<OrderElement> basket) {
		this.basket = basket;
	}

	public void setHistoryList(ArrayList<Order> historyList) {
		this.historyList = historyList;
	}

	public static void setNumCustomers(int numCustomers) {
		Customer.numCustomers = numCustomers;
	}

	public void addToBasket(OrderElement element) {
		basket.add(element);
	}

	public double getBasketTotalWeight() {
		double weight = 0;
		for (int i = 0; i < basket.size(); i++) {
			weight += basket.get(i).getProduct().getWeight() * basket.get(i).getNum();
		}
		return weight;
	}

	public double getBasketTotalPrice() {
		double price = 0;
		for (int i = 0; i < basket.size(); i++) {
			price += basket.get(i).getProduct().getPrice() * basket.get(i).getNum();

		}
		return price;
	}

	public void clearBasket() {
		basket.removeAll(basket);
	}

	public void addToHistoryList(Order order) {
		if (basket.size() != 0) {
			historyList.add(order.clone());
		}
	}

	public String getBasketString() {
		String output = "";
		ProductExt customerProduct;
		String format = "<pre>%-10s %-31s %-25s %-6s %-6.1f %,-8.0f   %-6d</pre>";
		for (int i = 0; i < basket.size(); i++) {
			customerProduct = basket.get(i).getProduct();
			output += String.format(format, customerProduct.getProductID(), customerProduct.getName(), customerProduct.getMaterial(), customerProduct.getSize(), customerProduct.getWeight(), customerProduct.getPrice(), basket.get(i).getNum());
		}
		return output;
	}

	public String getBasketStringWithoutMaterial() {
		String output = "";
		ProductExt customerProduct;
		String format = "<pre>%-10s %-31s %-6s %-6.1f %,-8.0f   %-6d</pre>";
		for (int i = 0; i < basket.size(); i++) {
			customerProduct = basket.get(i).getProduct();
			output += String.format(format, customerProduct.getProductID(), customerProduct.getName(), customerProduct.getSize(), customerProduct.getWeight(), customerProduct.getPrice(), basket.get(i).getNum());
		}
		return output;
	}

	public String getHistoryListString() {
		String output = "<pre>";

		for (int i = 0; i < historyList.size(); i++) {
			basket.removeAll(basket);
			basket.addAll(historyList.get(i).getBuyList());
			String format = "OrderNumber: %s %s";
			output += String.format(format, historyList.get(i).getOrderID(), getBasketString());
		}
		output += "</pre>";
		return output;
	}

	public boolean equals(String personID, String name, String lastname) {
		if (super.getID().equals(personID) && super.getName().equals(name) && super.getLastname().equals(lastname)) {
			return true;
		}
		return false;
	}

	public String toString() {
		String format = "%s %s %s %s %s %d";
		return String.format(format, this.customerID, super.getID(), super.getName(), super.getLastname(), super.getGender(), super.getAge());
	}
}