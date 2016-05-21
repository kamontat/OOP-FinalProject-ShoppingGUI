package gui1;

import code.customer.Customer;
import code.file.FileFactory;
import code.product.ProductExt;
import code.store.Store;
import gui.CustomerPage;
import gui.ShoppingPage;

import javax.swing.*;
import java.awt.*;
import java.util.*;

;

/**
 * @author kamontat
 * @since 20/5/59 - 22:55
 */
public class MainPage extends JFrame {
	private JButton customerButton;
	private JButton shoppingButton;
	private JButton storeButton;
	private JButton exitButton;
	private JPanel panel;

	private static FileFactory factory = new FileFactory();
	private static ArrayList<ProductExt> productList = assignProduct();
	private static ArrayList<Customer> customerList = assignCustomer();
	public static Store store = assignStore();

	public MainPage() {
		super("Main Page");

		setContentPane(panel);

		setCustomerButton();
		setShoppingButton();
		setStoreButton();
		setExitButton();
	}

	public static Object[][] getProductList() {
		Object[][] temp = new Object[productList.size()][ProductExt.numberInfo];
		for (int i = 0; i < productList.size(); i++) {
			temp[i] = productList.get(i).getProductInfo();
		}
		return temp;
	}

	public static ArrayList<Customer> getCustomerList() {
		return customerList;
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

	private void setExitButton() {
		exitButton.addActionListener(e -> System.exit(0));
	}

	private void setCustomerButton() {
		customerButton.addActionListener(e -> {
			try {
				CustomerPage customer = new CustomerPage();
				customer.run();
				setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "it's don't have customer page", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void setShoppingButton() {
		shoppingButton.addActionListener(e -> {
			ShoppingPage shopping = new ShoppingPage();
			shopping.run();
			setVisible(false);
		});
	}

	private void setStoreButton() {
		storeButton.addActionListener(e -> {
			LoginPage login = new LoginPage();
			login.run(getLocation());
		});
	}

	/**
	 * run this gui
	 */
	public void run() {
		pack();
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(650, 400));
	}
}
