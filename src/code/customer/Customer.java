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

	/**
	 * for create guest member
	 */
	public Customer() {
		super();
		memberClass = "None";
		basketList = new ArrayList<>();
		historyList = new ArrayList<>();
		customerID = "GUEST";
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
		if (order.getBuyList().size() != 0) {
			historyList.add(order.clone());
		}
	}

	public double getDiscountRate() {
		double discountRate = 0;
		if (memberClass.equals("Gold")) {
			discountRate = 0.2;
		} else if (memberClass.equals("Silver")) {
			discountRate = 0.1;
		} else if (memberClass.equals("Green")) {
			discountRate = 0.05;
		} else if (getAge() >= 60) {
			discountRate = 0.01;
		}
		return discountRate;
	}

	public String getBasketString() {
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
	 * array in form (ID, Name, LastName, Gender, Age, Class)
	 * return null if input element more than it have
	 *
	 * @param element
	 * 		element of array
	 * @return array with element element
	 */
	public Object[] getCustomerInfo(int element) {
		Object[] all = new Object[]{getID(), getName(), getLastName(), getGender(), getAge(), getMemberClass()};
		if (element <= all.length) {
			Object[] temp = new Object[element];
			// copy all into temp with element
			System.arraycopy(all, 0, temp, 0, element);
			return temp;
		}
		return null;
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

	public Customer clone() {
		numCustomers--;
		Customer temp = new Customer(getID(), getName(), getLastName(), getGender(), getAge(), getMemberClass());
		temp.setHistoryList(historyList);
		temp.setBasketList(getBasketList());

		return temp;
	}

	public boolean equals(String personID, String name, String lastname) {
		return super.getID().equals(personID) && super.getName().equals(name) && super.getLastName().equals(lastname);
	}

	public String toString() {
		String format = "%s, %s, %s %s, %s, %d";
		if (getCustomerID().equals("GUEST")) {
			return "GUEST member";
		}
		return String.format(format, this.customerID, super.getID(), super.getName(), super.getLastName(), super.getGender(), super.getAge());
	}
}