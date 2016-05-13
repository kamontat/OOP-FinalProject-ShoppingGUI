package GUI;

import Code.Customer;
import Code.ProductExt;
import Code.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MainMenu extends JFrame {

	static private Store store;
	private ArrayList<Customer> customerList = new ArrayList<>();
	private ArrayList<ProductExt> productList = new ArrayList<>();

	// check run only first time
	private boolean check = ShoppingPage.isCheck() && CustomerPage.isCheck() && StorePage.isCheck() && PaymentPage.isCheck();

	MainMenu() throws IOException {
		super("Main Menu");
		getContentPane().setBackground(new Color(0, 0, 0));
		initComponent();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	void run() {
		setBounds(50, 50, 880, 500);
		setVisible(true);
		setResizable(false);
	}

	public static Store getStore() {
		return store;
	}

	public static void setStore(Store store) {
		MainMenu.store = store;
	}

	private void initComponent() throws IOException {
		getContentPane().setLayout(null);

		if (check) {
			assignProduct();
			assignCustomer();
			// Money that i have
			store = new Store(productList, customerList, 500000, 0);
		}
		// create container
		Container pageButtonContainer = new Container();
		pageButtonContainer.setBounds(10, 269, 828, 97);
		// null is a absolute layout
		pageButtonContainer.setLayout(null);

		Container informationPositionContainer = new Container();
		informationPositionContainer.setBounds(203, 372, 399, 178);
		informationPositionContainer.setLayout(null);

		// add image title
		ClassLoader loader = this.getClass().getClassLoader();
		JLabel bg2 = new JLabel(new ImageIcon(loader.getResource("image/bg1.png")));
		bg2.setBounds(143, 6, 522, 146);
		bg2.setBackground(new Color(0, 0, 0));
		FlowLayout flowForImg = new FlowLayout();
		flowForImg.setVgap(10);
		flowForImg.setHgap(10);
		bg2.setLayout(flowForImg);
		getContentPane().add(bg2);

		// JLabel show information of Button
		JLabel lblViewProduct = new JLabel("<HTML>1) LIST ALL PRODUCT <br>2) SHOW PAYMENT<br>3) BASKET INFORMATION</HTML>", JLabel.CENTER);
		lblViewProduct.setBounds(33, 0, 238, 90);
		pageButtonContainer.add(lblViewProduct);
		lblViewProduct.setForeground(new Color(248, 248, 255));
		lblViewProduct.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		JLabel lblSearchCustomer = new JLabel("<HTML>1) SEARCH CUSTOMER <br>2) ADD & REMOVE CUSTOMER <br>3) CHOOSE CUSTOMER</HTML>", JLabel.CENTER);
		lblSearchCustomer.setBounds(293, 0, 238, 90);
		pageButtonContainer.add(lblSearchCustomer);
		lblSearchCustomer.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		lblSearchCustomer.setForeground(Color.WHITE);
		JLabel lblSearchProduct = new JLabel("<HTML>1) SEARCH PRODUCT <br>2) SHOW STOCK & RESTOCK <br>3) VIEW REVENUE, EXPENSE, PROFIT</HTML>", JLabel.CENTER);
		lblSearchProduct.setBounds(533, 0, 275, 90);
		pageButtonContainer.add(lblSearchProduct);
		lblSearchProduct.setForeground(Color.WHITE);
		lblSearchProduct.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		getContentPane().add(informationPositionContainer);

		// create button
		JButton exitButton = new JButton("- Exit -");
		exitButton.setBounds(123, 23, 150, 50);
		exitButton.setFont(new Font("AppleMyungjo", Font.BOLD, 35));
		exitButton.setForeground(Color.DARK_GRAY);
		informationPositionContainer.add(exitButton);

		JButton shoppingButton = new JButton("- Shopping -");
		shoppingButton.setBounds(71, 183, 181, 69);
		shoppingButton.setForeground(Color.DARK_GRAY);
		shoppingButton.setFont(new Font("Apple Chancery", Font.PLAIN, 30));
		getContentPane().add(shoppingButton);

		JButton customerButton = new JButton("- Customer -");
		customerButton.setBounds(309, 183, 181, 69);
		customerButton.setForeground(Color.DARK_GRAY);
		customerButton.setFont(new Font("Apple Chancery", Font.PLAIN, 30));
		getContentPane().add(customerButton);

		JButton storeButton = new JButton("- Store -");
		storeButton.setBounds(552, 183, 181, 69);
		storeButton.setForeground(Color.DARK_GRAY);
		storeButton.setFont(new Font("Apple Chancery", Font.PLAIN, 30));
		getContentPane().add(storeButton);

		// make button can use
		exit(exitButton);
		toStore(storeButton);
		toCustomer(customerButton);
		toShopping(shoppingButton);

		// add container in this JFrame
		getContentPane().add(pageButtonContainer);
	}

	/**
	 * assign product into the arrayList and this method I run Only 1 time that
	 * we run this program
	 */
	private void assignProduct() {
		productList.add(new ProductExt("Blue Topazes Ring", 3.5, 22200, 10, 3, "18k Gold", "6", 10000));
		productList.add(new ProductExt("Midnight Titanium Ring", 5.1, 13800, 10, 3, "Steel & Titanium", "9", 9400));
		productList.add(new ProductExt("Pink Diamonds Ring", 3.7, 202000, 5, 1, "Diamonds", "6", 69500));
		productList.add(new ProductExt("Silver Bow Ring", 2.6, 6600, 20, 5, "Silver", "6", 3700));
		productList.add(new ProductExt("X Diamonds Ring", 4.2, 253000, 5, 1, "Pink sapphires", "6", 83200));

		productList.add(new ProductExt("Mixed Cluster Pendant", 12.3, 155000, 5, 1, "Platinum and Diamonds", "Medium", 60500));
		productList.add(new ProductExt("Olive Leaf Pendant", 11.1, 13000, 10, 3, "Silver", "Small", 9600));
		productList.add(new ProductExt("Pierced Pendant", 10.7, 25200, 10, 3, "Gold and Diamonds", "Small", 16000));
		productList.add(new ProductExt("Silver Bow Pendant", 10.3, 5600, 20, 5, "Silver", "Small", 3300));
		productList.add(new ProductExt("White Gold Bow Pendant", 11.6, 111000, 5, 1, "White Gold & Diamonds", "Small", 86900));

		productList.add(new ProductExt("Blue Topazes Olive Leaf Earring", 1.3, 24600, 10, 3, "Gold", "Mini", 16800));
		productList.add(new ProductExt("Bow Earring", 1.5, 14200, 10, 3, "Rose Gold", "Mini", 10500));
		productList.add(new ProductExt("Color By The Yard Earring", 1.1, 30600, 10, 3, "Silver & Pink Sapphires", "Mini", 26000));
		productList.add(new ProductExt("Rivals Night Sky Earring", 2.4, 504000, 3, 1, "Platinum & Diamonds", "Medium", 489000));
		productList.add(new ProductExt("Silver Olive Leaf Earring", 1.8, 5600, 20, 5, "Silver", "Medium", 2900));
	}

	/**
	 * assign product into the arrayList By use text file and this method I run
	 * Only 1 time that we run this program
	 *
	 * @throws IOException
	 */
	private void assignCustomer() throws IOException {
		Customer.setNumCustomers(Customer.getNumCustomers() - customerList.size());
		customerList.removeAll(customerList);

		File input = new File("/Users/kamontat/Documents/workspace/ProjectGUILab12/Customer.txt/");
		Scanner scan = new Scanner(input);

		while (scan.hasNext()) {
			String id = scan.next();
			String name = scan.next();
			String lastname = scan.next();
			String gender = scan.next();
			int age = scan.nextInt();
			String member = scan.next();
			customerList.add(new Customer(id, name, lastname, gender, age, member));
		}

		scan.close();
	}

	/**
	 * Open ShoppingPage by use ActionListener (Only ShoppingButton)
	 *
	 * @param button
	 * 		shopping button
	 */
	private void toShopping(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShoppingPage shopping = new ShoppingPage();
				shopping.run();
				setVisible(false);
			}
		});
	}

	/**
	 * Open StorePage by use ActionListener (Only StoreButton)
	 *
	 * @param button
	 * 		store button
	 */
	private void toStore(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPage store = new LoginPage();
				store.run();
				setVisible(false);
			}
		});
	}

	/**
	 * Open CustomerPage by use ActionListener (Only CustomerButton)
	 *
	 * @param button
	 * 		customer page button
	 */
	private void toCustomer(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerPage customer;
				try {
					customer = new CustomerPage();
					customer.run();
					setVisible(false);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "it's don't have customer page", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	/**
	 * Exit Program by use ActionListener (Only ExitButton)
	 *
	 * @param button
	 * 		exit button
	 */
	private void exit(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) throws IOException {
		MainMenu view = new MainMenu();
		view.run();
	}
}