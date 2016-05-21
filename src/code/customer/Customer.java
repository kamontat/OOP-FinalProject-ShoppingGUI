package code.customer;

import code.store.Order;
import code.store.OrderElement;
import code.product.ProductExt;

import java.util.*;

public class Customer extends Person {
	private String customerID;
	private String memberClass;
	private ArrayList<OrderElement> basketList;
	private ArrayList<Order> historyList;
	private static int numCustomers;

	public Customer() {
		super();
		numCustomers++;
		memberClass = "None";
		basketList = new ArrayList<>();
		historyList = new ArrayList<>();
		customerID = "Member" + numCustomers;
	}

	public Customer(String ID, String name, String lastName, String gender, int age, String memberClass) {
		super(ID, name, lastName, gender, age);
		numCustomers++;
		this.memberClass = memberClass;
		basketList = new ArrayList<>();
		historyList = new ArrayList<>();
		customerID = "Member" + numCustomers;
	}

	public Customer(String ID, String name, String lastName, String gender, String age, String memberClass) {
		super(ID, name, lastName, gender, Integer.parseInt(age));
		numCustomers++;
		this.memberClass = memberClass;
		basketList = new ArrayList<>();
		historyList = new ArrayList<>();
		customerID = "Member" + numCustomers;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getMemberClass() {
		return memberClass;
	}

	public ArrayList<OrderElement> getBasketList() {
		return basketList;
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

	public void setBasketList(ArrayList<OrderElement> basketList) {
		this.basketList = basketList;
	}

	public void setHistoryList(ArrayList<Order> historyList) {
		this.historyList = historyList;
	}

	public static void setNumCustomers(int numCustomers) {
		Customer.numCustomers = numCustomers;
	}

	public void addToBasket(OrderElement element) {
		basketList.add(element);
	}

	public double getBasketTotalWeight() {
		double weight = 0;
		for (OrderElement basket : basketList) {
			weight += basket.getProduct().getWeight() * basket.getNum();
		}
		return weight;
	}

	public double getBasketTotalPrice() {
		double price = 0;
		for (OrderElement basket : basketList) {
			price += basket.getProduct().getPrice() * basket.getNum();

		}
		return price;
	}

	public void clearBasket() {
		basketList.removeAll(basketList);
	}

	public void addToHistoryList(Order order) {
		if (basketList.size() != 0) {
			historyList.add(order.clone());
		}
	}

	public String getBasketString() {
		String output = "";
		ProductExt customerProduct;
		String format = "<pre>%-10s %-31s %-25s %-6s %-6.1f %,-8.0f   %-6d</pre>";
		for (OrderElement basket : basketList) {
			customerProduct = basket.getProduct();
			output += String.format(format, customerProduct.getProductID(), customerProduct.getName(), customerProduct.getMaterial(), customerProduct.getSize(), customerProduct.getWeight(), customerProduct.getPrice(), basket.getNum());
		}
		return output;
	}

	public String getBasketStringWithoutMaterial() {
		String output = "";
		ProductExt customerProduct;
		String format = "<pre>%-10s %-31s %-6s %-6.1f %,-8.0f   %-6d</pre>";
		for (OrderElement basket : basketList) {
			customerProduct = basket.getProduct();
			output += String.format(format, customerProduct.getProductID(), customerProduct.getName(), customerProduct.getSize(), customerProduct.getWeight(), customerProduct.getPrice(), basket.getNum());
		}
		return output;
	}

	/**
	 * @return Array 6 Element
	 */
	public Object[] getCustomerInfo() {
		return new Object[]{getID(), getName(), getLastname(), getGender(), getAge(), getMemberClass()};
	}

	public String getHistoryListString() {
		String output = "<pre>";

		for (Order history : historyList) {
			basketList.removeAll(basketList);
			basketList.addAll(history.getBuyList());
			String format = "OrderNumber: %s %s";
			output += String.format(format, history.getOrderID(), getBasketString());
		}
		output += "</pre>";
		return output;
	}

	public boolean equals(String personID, String name, String lastname) {
		return super.getID().equals(personID) && super.getName().equals(name) && super.getLastname().equals(lastname);
	}

	public String toString() {
		String format = "%s %s %s %s %s %d";
		return String.format(format, this.customerID, super.getID(), super.getName(), super.getLastname(), super.getGender(), super.getAge());
	}
}