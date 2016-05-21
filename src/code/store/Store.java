package code.store;

import code.customer.Customer;
import code.product.ProductExt;

import java.util.ArrayList;

public class Store {
	private ArrayList<ProductExt> productList;
	private ArrayList<Customer> customerList;
	private double revenue, expense;
	private boolean restockProduct;
	private ArrayList<Order> historyList = new ArrayList<Order>();

	public Store() {
		productList = new ArrayList<ProductExt>();
		customerList = new ArrayList<Customer>();
		revenue = 0;
		expense = 0;
		restockProduct = false;
	}

	public Store(ArrayList<ProductExt> productList, ArrayList<Customer> customerList, double revenue, double expense) {
		this.productList = productList;
		this.customerList = customerList;
		this.revenue = revenue;
		this.expense = expense;
		this.restockProduct = false;
	}

	public Store(ArrayList<ProductExt> productList, ArrayList<Customer> customerList, String revenue, String expense) {
		this.productList = productList;
		this.customerList = customerList;
		this.revenue = Double.parseDouble(revenue);
		this.expense = Double.parseDouble(expense);
		this.restockProduct = false;
	}

	public ArrayList<ProductExt> getProductList() {
		return productList;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public double getRevenue() {
		return revenue;
	}

	public double getExpense() {
		return expense;
	}

	public boolean getRestockProduct() {
		return restockProduct;
	}

	public void setProductList(ArrayList<ProductExt> productList) {
		this.productList = productList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public void addRevenue(double revenue) {
		this.revenue += revenue;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public void addExpense(double expense) {
		this.expense += expense;
	}

	public void setRestockProduct(boolean restockProduct) {
		this.restockProduct = restockProduct;
	}

	public void addProduct(ProductExt product) {
		productList.add(product);
	}

	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}

	public ProductExt searchProduct(String name) {
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getName().equals(name)) {
				return productList.get(i);
			}
		}
		return null;
	}

	public int searchIDCustomer(String personID) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getID().equals(personID)) {
				return i;
			}
		}
		return -1;
	}

	public ArrayList<Integer> searchCustomer(String type, String text) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < customerList.size(); i++) {
			if (type.equalsIgnoreCase("name") && customerList.get(i).getName().equalsIgnoreCase(text)) {
				index.add(i);
			} else if (type.equalsIgnoreCase("lastname") && customerList.get(i).getLastname().equalsIgnoreCase(text)) {
				index.add(i);
			} else if (type.equalsIgnoreCase("age") && customerList.get(i).getAge() == Integer.parseInt(text)) {
				index.add(i);
			} else if (type.equalsIgnoreCase("memberclass") && customerList.get(i).getMemberClass().equalsIgnoreCase(text)) {
				index.add(i);
			}
		}
		return index;
	}

	public void removeCustomer(int CustomerIndex) {
		customerList.remove(CustomerIndex);
	}

	public void removeProduct(int ProductIndex) {
		productList.remove(ProductIndex);
	}

	public int checkAvailability(ProductExt product, int num) {
		if (product.getCurrNumStock() < num) {
			return 0;
		}
		return 1;
	}

	public void updateStock(OrderElement element) {
		ProductExt customerProduct = element.getProduct();

		for (int i = 0; i < productList.size(); i++) {
			// check name
			if (productList.get(i).getName().equals(customerProduct.getName())) {
				// check material
				if (productList.get(i).getMaterial().equals(customerProduct.getMaterial())) {
					// check size
					if (productList.get(i).getSize().equals(customerProduct.getSize())) {
						productList.get(i).withdrawStock(element.getNum());
					}
				}
			}
		}

		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getCurrNumStock() < 3) {
				productList.get(i).setRestock(true);
				restockProduct = true;
			}
		}
	}

	/**
	 * return the number to currstock
	 *
	 * @param element
	 */
	public void refundStock(OrderElement element) {
		ProductExt customerProduct = element.getProduct();

		for (int i = 0; i < productList.size(); i++) {
			// check ID
			if (productList.get(i).getProductID().equals(customerProduct.getProductID())) {
				productList.get(i).returnStock(element.getNum());
			}
		}
	}

	/**
	 * create Order by using paramater and store it into HistoryList Update
	 * revenue and expense And clear product in basket
	 *
	 * @param customer
	 * @param registered
	 * @param express
	 */
	public void checkOut(Customer customer, boolean registered, boolean express) {
		Order order = new Order(customer, registered, express);
		customer.addToHistoryList(order);
		revenue += order.getPayment().getValue();
		expense += order.getPayment().getShippingFee();
		customer.clearBasket();
	}

	public double getProfit() {
		return revenue - expense;
	}

	public String getProductListString() {
		String output = "";
		for (int i = 0; i < productList.size(); i++) {
			output += productList.get(i).toString() + "\n";
		}
		return output;
	}

	/**
	 * Check product in history list of all customer if it equals keep it in
	 * arraylist
	 *
	 * @param product
	 * @return arrayList of Customer
	 */
	public ArrayList<Customer> checkProductHistory(ProductExt product) {
		ArrayList<Customer> customer = new ArrayList<Customer>();
		for (int i = 0; i < customerList.size(); i++) {
			for (int j = 0; j < customerList.get(i).getHistoryList().size(); j++) {
				for (int k = 0; k < customerList.get(i).getHistoryList().get(j).getBuyList().size(); k++) {
					if (customerList.get(i).getHistoryList().get(j).getBuyList().get(k).getName().equals(product.getName())) {
						customer.add(customerList.get(i));
					}
				}
			}
		}
		return customer;
	}

	public String getCustomerListString() {
		String output = "";
		for (int i = 0; i < customerList.size(); i++) {
			output += customerList.get(i).toString() + "\n";
		}
		return output;
	}

	public String toString() {
		String format = "Revenue: %.2f, Expense: %.2f, Profit: %.2f";
		return String.format(format, revenue, expense, getProfit());
	}
}
