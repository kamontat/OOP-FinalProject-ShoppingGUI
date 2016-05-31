package code.store;

import code.customer.Customer;
import code.product.ProductExt;

import java.util.*;

/**
 * @author kamontat
 * @since 31/5/59 - 22:35
 */
public class Store {
	private ArrayList<ProductExt> productList;
	private ArrayList<Customer> customerList;
	private double revenue, expense;
	private boolean restockProduct;
	private ArrayList<Order> historyList = new ArrayList<>();

	private static Store store;
	
	public static Store getInstance(ArrayList<ProductExt> productList, ArrayList<Customer> customerList, String revenue, String expense) {
		if (store == null) store = new Store(productList, customerList, revenue, expense);
		return store;
	}

	public static Store getInstance() {
		return store;
	}

	private Store(ArrayList<ProductExt> productList, ArrayList<Customer> customerList, String revenue, String expense) {
		this.productList = productList;
		this.customerList = customerList;
		this.revenue = Double.parseDouble(revenue);
		this.expense = Double.parseDouble(expense);
		this.restockProduct = false;
	}

	public ArrayList<ProductExt> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ProductExt> productList) {
		this.productList = productList;
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<Customer> customerList) {
		this.customerList = customerList;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public void addRevenue(double revenue) {
		this.revenue += revenue;
	}

	public double getExpense() {
		return expense;
	}

	public void setExpense(double expense) {
		this.expense = expense;
	}

	public void addExpense(double expense) {
		this.expense += expense;
	}

	public boolean isRestockProduct() {
		return restockProduct;
	}

	public void setRestockProduct(boolean restockProduct) {
		this.restockProduct = restockProduct;
	}

	public ArrayList<Order> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(ArrayList<Order> historyList) {
		this.historyList = historyList;
	}

	public Object[][] getAllProduct() {
		Object[][] temp = new Object[productList.size()][10];
		for (int i = 0; i < productList.size(); i++) {
			temp[i] = productList.get(i).getProductInfo(10);
		}
		return temp;
	}

	public Object[][] getAllCustomer() {
		Object[][] temp = new Object[customerList.size() - 1][6];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = customerList.get(i + 1).getCustomerInfo(6);
		}
		return temp;
	}

	public Customer getGuest() {
		return customerList.get(0);
	}

	public void addProduct(ProductExt product) {
		productList.add(product);
	}

	public void addCustomer(Customer customer) {
		customerList.add(customer);
	}

	public ProductExt searchIDProduct(String productID) {
		for (ProductExt product : productList) {
			if (product.getProductID().equals(productID)) {
				return product;
			}
		}
		return null;
	}

	public Customer searchIDCustomer(String personID) {
		for (Customer customer : customerList) {
			if (customer.getID().equals(personID)) {
				return customer;
			}
		}
		return null;
	}

	public void removeCustomer(int CustomerIndex) {
		customerList.remove(CustomerIndex);
	}

	/**
	 * remove Customer and return index of that customer
	 *
	 * @param customer
	 * 		customer want to remove
	 * @param guest
	 * 		include guest or not
	 * @return index of remove customer
	 */
	public int removeCustomer(Customer customer, boolean guest) {
		for (int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getID().equals(customer.getID())) {
				customerList.remove(i);
				if (guest) {
					return i;
				} else {
					return i - 1;
				}
			}
		}
		return -1;
	}

	public void removeProduct(int ProductIndex) {
		productList.remove(ProductIndex);
	}

	public void removeProduct(ProductExt Product) {
		productList.remove(Product);
	}

	public int checkAvailability(ProductExt product, int num) {
		if (product.getCurrNumStock() < num) {
			return 0;
		}
		return 1;
	}

	public void updateStock(OrderElement element) {
		ProductExt customerProduct = element.getProduct();

		for (ProductExt product : productList) {
			// check name
			if (product.getName().equals(customerProduct.getName())) {
				// check material
				if (product.getMaterial().equals(customerProduct.getMaterial())) {
					// check size
					if (product.getSize().equals(customerProduct.getSize())) {
						product.withdrawStock(element.getNum());
					}
				}
			}
		}

		for (ProductExt product : productList) {
			if (product.getCurrNumStock() < 3) {
				product.setRestock(true);
				restockProduct = true;
			}
		}
	}

	public void refundStock(OrderElement element) {
		ProductExt customerProduct = element.getProduct();

		for (ProductExt product : productList) {
			// check ID
			if (product.getProductID().equals(customerProduct.getProductID())) {
				product.returnStock(element.getNum());
			}
		}
	}

	/**
	 * create Order by using paramater and store it into HistoryList Update
	 * revenue and expense And clear product in basket
	 *
	 * @param customer
	 * 		customer
	 * @param registered
	 * 		true if customer want to registered
	 * @param express
	 * 		true if customer want to express
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
	 * Check product in history list of all customer if it equals keep it in arrayList
	 *
	 * @param product
	 * 		product that customer buy
	 * @return arrayList of Customer that buy the product
	 */
	public ArrayList<Customer> checkProductHistory(ProductExt product) {
		ArrayList<Customer> customer = new ArrayList<>();
		for (Customer aCustomer : customerList) {
			for (int j = 0; j < aCustomer.getHistoryList().size(); j++) {
				for (int k = 0; k < aCustomer.getHistoryList().get(j).getBuyList().size(); k++) {
					if (aCustomer.getHistoryList().get(j).getBuyList().get(k).getName().equals(product.getName())) {
						customer.add(aCustomer);
					}
				}
			}
		}
		return customer;
	}

	public String getCustomerListString() {
		String output = "";
		for (Customer customer : customerList) {
			output += customer.toString() + "\n";
		}
		return output;
	}

	public String toString() {
		String format = "Revenue: %.2f, Expense: %.2f, Profit: %.2f";
		return String.format(format, revenue, expense, getProfit());
	}
}
