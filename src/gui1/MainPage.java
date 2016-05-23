package gui1;

import code.Interface.ButtonAction;
import code.customer.Customer;
import code.file.FileFactory;
import code.product.ProductExt;
import code.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * @author kamontat
 * @since 20/5/59 - 22:55
 */
public class MainPage extends JFrame implements ButtonAction {
	private JButton customerButton;
	private JButton shoppingButton;
	private JButton storeButton;
	private JButton exitButton;
	private JPanel panel;

	private static FileFactory factory = new FileFactory();
	private static ArrayList<ProductExt> productList = assignProduct();
	private static ArrayList<Customer> customerList = assignCustomer();
	public static Store store = assignStore();

	public static Customer shopper = customerList.get(0);

	public MainPage() {
		super("Main Page");
		setContentPane(panel);

		toShopping(this, shoppingButton);
		toCustomer(this, customerButton);
		toLogin(this, storeButton);
		toExit(exitButton);
	}

	public static Object[][] getProductList() {
		Object[][] temp = new Object[productList.size()][9];
		for (int i = 0; i < productList.size(); i++) {
			temp[i] = productList.get(i).getProductInfo(9);
		}
		return temp;
	}

	public static Object[][] getCustomerList() {
		Object[][] temp = new Object[customerList.size()][6];
		for (int i = 0; i < customerList.size(); i++) {
			temp[i] = customerList.get(i).getCustomerInfo(6);
		}
		return temp;
	}

	/**
	 * rewrite customer file by using array of customer
	 */
	public static void reWriteCustomer() {
		factory.setPath("src/textfile/Customer.txt");
		factory.write(getCustomerList());
	}

	/**
	 * Assign product into the arrayList By using <i>text file</i> and <i>return</i>
	 *
	 * @return Product Array
	 */
	private static ArrayList<ProductExt> assignProduct() {
		ArrayList<ProductExt> temp = new ArrayList<>();

		factory.setPath("src/textfile/Product.txt");
		String[][] allProduct = factory.read(":");

		for (String[] product : allProduct) {
			if (product.length == 8)
				temp.add(new ProductExt(product[0], product[1], product[2], product[3], product[4], product[5], product[6], product[7]));
			else System.err.println("product text-file error");
		}
		return temp;
	}

	/**
	 * Assign customer into the arrayList By using <i>text file</i> and <i>return</i>
	 *
	 * @return Customer Array
	 */
	private static ArrayList<Customer> assignCustomer() {
		ArrayList<Customer> temp = new ArrayList<>();

		factory.setPath("src/textfile/Customer.txt");
		String[][] allCustomer = factory.read(":");

		temp.add(new Customer());

		for (String[] customer : allCustomer) {
			if (customer.length == 6)
				temp.add(new Customer(customer[0], customer[1], customer[2], customer[3], customer[4], customer[5]));
			else System.err.println("customer text-file error");
		}
		Customer.setNumCustomers(temp.size());

		return temp;
	}

	/**
	 * Assign store information By using <i>text file</i> and <i>return</i>
	 *
	 * @return Store
	 */
	private static Store assignStore() {
		Store temp = null;
		factory.setPath("src/textfile/StoreInfo.txt");
		String[][] informations = factory.read(":");
		for (String[] info : informations) {
			temp = new Store(productList, customerList, info[0], info[1]);
		}

		return temp;
	}

	/**
	 * run this gui
	 */
	public void run(Point point) {
		pack();
		setVisible(true);
		setLocation(point);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(650, 400));
	}
}
