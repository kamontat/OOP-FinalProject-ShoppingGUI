package gui;

import code.customer.Customer;
import code.payment.Shipping;
import code.store.OrderElement;
import code.store.Store;
import gui1.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PaymentPage extends JFrame {

	private Store store = MainPage.store;
	private Customer shopper;
	private JLabel textProductCustomer, textMemberClass, textCustomer, textPriceShipping, textFinalPrice;
	private JComboBox<String> comboProduct;
	private boolean register, express;
	// check run only first time
	static private boolean check = true;

	public PaymentPage() {
		super("Payment Page");
		getContentPane().setBackground(SystemColor.controlText);
		initComponent();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(200, 200, 600, 720);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
	}

	public static boolean isCheck() {
		return check;
	}

	public static void setCheck(boolean check) {
		PaymentPage.check = check;
	}

	public void initComponent() {
		getContentPane().setLayout(null);
		// make shopper Present
		shopper = store.getCustomerList().get(CustomerPage.getIndexOfCustomer());

		// create Comtainer
		Container firstContainer = new Container();
		firstContainer.setBounds(0, 0, 600, 90);
		firstContainer.setLayout(null);

		Container customerInformationContainer = new Container();
		customerInformationContainer.setBounds(0, 64, 600, 26);
		customerInformationContainer.setLayout(new FlowLayout());

		// assign ComboBox
		comboProduct = new JComboBox<String>(toArray(shopper.getBasket()));
		comboProduct.setForeground(UIManager.getColor("Desktop.background"));
		comboProduct.setFont(new Font("Andale Mono", Font.PLAIN, 13));
		comboProduct.setBounds(456, 101, 144, 30);
		getContentPane().add(comboProduct);

		// assign button
		JButton buttonChange = new JButton("CHANGE");
		buttonChange.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
		buttonChange.setFont(new Font("Heiti TC", Font.PLAIN, 16));
		buttonChange.setBounds(477, 143, 117, 41);
		buttonChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int productIndex = comboProduct.getSelectedIndex();
				OrderElement order = shopper.getBasket().get(productIndex);
				String textChange = JOptionPane.showInputDialog(null, "Change from " + order.getNum() + " To ..?", comboProduct.getSelectedItem().toString(), JOptionPane.INFORMATION_MESSAGE);
				int num = Integer.parseInt(textChange);
				// return old order
				store.refundStock(order);
				order.setNum(num);
				// update the new one
				store.updateStock(order);
				// update textProductCustomer, textNumProduct, textPrice
				setTextproductCustomerInBasket();
				setTextproductCustomerInShopping();
				// update price in basket
				updatePriceShipping();
				updateFinalPrice();
			}
		});
		getContentPane().add(buttonChange);

		JButton buttonRemove = new JButton("REMOVE");
		buttonRemove.setFont(new Font("Heiti TC", Font.PLAIN, 16));
		buttonRemove.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
		buttonRemove.setBounds(477, 196, 117, 41);
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int productIndex = comboProduct.getSelectedIndex();
				OrderElement order = shopper.getBasket().get(productIndex);
				store.refundStock(order);
				shopper.getBasket().remove(productIndex);
				// update textProductCustomer, textNumProduct, textPrice
				setTextproductCustomerInBasket();
				setTextproductCustomerInShopping();
				// update price in basket
				updatePriceShipping();
				updateFinalPrice();
				// update ComboBox*
				comboProduct.removeItemAt(productIndex);
			}
		});
		getContentPane().add(buttonRemove);

		JLabel textShipping = new JLabel("- SHIPPING -");
		textShipping.setHorizontalAlignment(SwingConstants.CENTER);
		textShipping.setBackground(UIManager.getColor("ComboBox.selectionForeground"));
		textShipping.setFont(new Font("Heiti TC", Font.PLAIN, 20));
		textShipping.setForeground(SystemColor.text);
		textShipping.setBounds(456, 249, 144, 35);
		getContentPane().add(textShipping);

		textPriceShipping = new JLabel("0");
		textPriceShipping.setHorizontalAlignment(SwingConstants.CENTER);
		textPriceShipping.setForeground(SystemColor.text);
		textPriceShipping.setFont(new Font("Heiti TC", Font.BOLD, 16));
		textPriceShipping.setBackground(Color.WHITE);
		textPriceShipping.setBounds(456, 365, 144, 60);
		updatePriceShipping();
		getContentPane().add(textPriceShipping);

		JLabel lblFinalPrice = new JLabel("- FINAL PRICE -");
		lblFinalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalPrice.setForeground(SystemColor.text);
		lblFinalPrice.setFont(new Font("Heiti TC", Font.PLAIN, 20));
		lblFinalPrice.setBounds(456, 448, 144, 35);
		getContentPane().add(lblFinalPrice);

		textFinalPrice = new JLabel("0");
		textFinalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textFinalPrice.setForeground(SystemColor.menu);
		textFinalPrice.setFont(new Font("Heiti TC", Font.BOLD, 16));
		textFinalPrice.setBounds(456, 489, 144, 42);
		updateFinalPrice();
		getContentPane().add(textFinalPrice);

		// Information Container
		textCustomer = new JLabel(shopper.toString());
		textCustomer.setForeground(SystemColor.text);
		textCustomer.setFont(new Font("Heiti TC", Font.PLAIN, 15));

		customerInformationContainer.add(textCustomer);
		firstContainer.add(customerInformationContainer);
		getContentPane().add(firstContainer);

		textMemberClass = new JLabel(shopper.getMemberClass());
		textMemberClass.setBounds(199, 7, 190, 57);
		firstContainer.add(textMemberClass);
		textMemberClass.setFont(new Font("Gujarati MT", Font.PLAIN, 27));
		textMemberClass.setForeground(SystemColor.menu);
		
		JLabel label = new JLabel("- Class Member -");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Heiti TC", Font.PLAIN, 19));
		label.setBounds(6, 16, 200, 33);
		firstContainer.add(label);

		// scroll Bar
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 444, 430);
		getContentPane().add(scrollPane);

		textProductCustomer = new JLabel("");
		textProductCustomer.setVerticalAlignment(SwingConstants.TOP);
		textProductCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		setTextproductCustomerInBasket();
		scrollPane.setViewportView(textProductCustomer);

		JButton buttonclose = new JButton("- Exit -");
		buttonclose.setForeground(new Color(128, 0, 0));
		buttonclose.setFont(new Font("AppleMyungjo", Font.BOLD, 27));
		buttonclose.setBounds(6, 633, 117, 57);
		buttonclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(buttonclose);

		JButton buttonShopping = new JButton("Shopping Page");
		buttonShopping.setForeground(new Color(0, 0, 128));
		buttonShopping.setFont(new Font("Hoefler Text", Font.BOLD, 27));
		buttonShopping.setBounds(174, 634, 195, 57);
		buttonShopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check = false;
				ShoppingPage shopping = new ShoppingPage();
				setTextproductCustomerInShopping();
				updateElseInShopping();
				shopping.run();
				setVisible(false);
			}
		});
		getContentPane().add(buttonShopping);

		JButton buttonMainMenu = new JButton("Main Menu");
		buttonMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check = false;
				// return currNumStock
				for (int i = 0; i < shopper.getBasket().size(); i++) {
					store.refundStock(shopper.getBasket().get(i));
				}
				shopper.clearBasket();

				MainPage menu = new MainPage();
				menu.run();
				dispose();
			}
		});
		buttonMainMenu.setForeground(new Color(0, 0, 128));
		buttonMainMenu.setFont(new Font("Hoefler Text", Font.BOLD, 27));
		buttonMainMenu.setBounds(174, 543, 195, 57);
		getContentPane().add(buttonMainMenu);

		// create Radio Button
		JRadioButton radioButtonRegister = new JRadioButton("REGISTER");
		radioButtonRegister.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		radioButtonRegister.setForeground(SystemColor.menu);
		radioButtonRegister.setBounds(456, 290, 141, 23);
		getContentPane().add(radioButtonRegister);
		JRadioButton radioButtonExpress = new JRadioButton("EXPRESS");
		radioButtonExpress.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		radioButtonExpress.setForeground(SystemColor.menu);
		radioButtonExpress.setBounds(456, 313, 141, 23);
		getContentPane().add(radioButtonExpress);
		JRadioButton radioButtonNone = new JRadioButton("NONE");
		radioButtonNone.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		radioButtonNone.setForeground(SystemColor.menu);
		radioButtonNone.setBounds(456, 338, 141, 23);
		getContentPane().add(radioButtonNone);

		// group the radio Button
		ButtonGroup group = new ButtonGroup();
		group.add(radioButtonRegister);
		group.add(radioButtonExpress);
		group.add(radioButtonNone);

		JButton buttonBuy = new JButton("Buy ! ");
		buttonBuy.setForeground(new Color(255, 0, 0));
		buttonBuy.setFont(new Font("Helvetica", Font.BOLD, 35));
		buttonBuy.setBounds(415, 540, 179, 150);
		buttonBuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check = false;
				store.checkOut(shopper, register, express);
				MainPage menu = new MainPage();
				menu.run();
				setVisible(false);
			}
		});
		getContentPane().add(buttonBuy);

		// add ActionListener
		radioButtonRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register = true;
				express = false;
				updatePriceShipping();
				updateFinalPrice();
			}
		});

		radioButtonExpress.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register = false;
				express = true;
				updatePriceShipping();
				updateFinalPrice();
			}
		});

		radioButtonNone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register = false;
				express = false;
				updatePriceShipping();
				updateFinalPrice();
			}
		});
	}

	/**
	 * set basket of Customer in this JFrame
	 */
	public void setTextproductCustomerInBasket() {
		String printBasket = String.format("<pre>%-10s %-31s %-6s %-6s %-8s   %6s</pre>", "ProductID", "Name", "Size", "Weight", "Price", "Number");
		printBasket += shopper.getBasketStringWithoutMaterial();
		textProductCustomer.setText("<html>" + printBasket + "</html>");
	}

	/**
	 * set basket of Customer in Shopping Page
	 */
	public void setTextproductCustomerInShopping() {
		String printBasket = String.format("<pre>%-8s %-10s %-31s %-25s %-6s %-6s %-8s   %6s</pre>", "OrderID", "ProductID", "Name", "Material", "Size", "Weight", "Price", "Number");
		printBasket += shopper.getBasketString();
		ShoppingPage.getTextProductCustomer().setText("<html>" + printBasket + "</html>");
		updateElseInShopping();
	}

	/**
	 * Update num product customer and price of product
	 */
	public void updateElseInShopping() {
		int numProductCustmer = 0;
		int priceProductCustmer = 0;
		for (int i = 0; i < shopper.getBasket().size(); i++) {
			numProductCustmer += shopper.getBasket().get(i).getNum();
			priceProductCustmer += shopper.getBasket().get(i).getNum() * shopper.getBasket().get(i).getProduct().getPrice();
		}
		// set 2 attribute that must update when we change product
		ShoppingPage.setNumProductCustomer(numProductCustmer);
		ShoppingPage.setPriceProductCustomer(priceProductCustmer);
		// update text to present
		ShoppingPage.getTextSelectProduct().setText(Integer.toString(numProductCustmer));
		ShoppingPage.getTextPrice().setText(Integer.toString(priceProductCustmer));
	}

	/**
	 * Update shipping price to present and set in textLabel
	 */
	public void updatePriceShipping() {
		ArrayList<OrderElement> basket = shopper.getBasket();
		double totalWeight = 0;
		for (int i = 0; i < basket.size(); i++) {
			totalWeight += basket.get(i).getProduct().getWeight() * basket.get(i).getNum();
		}
		Shipping shipping = new Shipping(totalWeight, register, express);
		String tempOutput = String.format("<html>" + "Weight: %.2f<br>Price: %d</html>", totalWeight, shipping.getShippingFee());
		textPriceShipping.setText(tempOutput);
	}

	/**
	 * Update final price to present and set in textLabel
	 */
	public void updateFinalPrice() {
		ArrayList<OrderElement> basket = shopper.getBasket();
		double totalWeight = 0;
		for (int i = 0; i < basket.size(); i++) {
			totalWeight += basket.get(i).getProduct().getWeight() * basket.get(i).getNum();
		}
		Shipping shipping = new Shipping(totalWeight, register, express);
		// priceProductCustmer
		String tempOutput = String.format("%,d", shipping.getShippingFee() + ShoppingPage.getPriceProductCustmer());
		textFinalPrice.setText(tempOutput);
	}

	/**
	 * add paramater text in combo box that text is product that customer add
	 * more
	 *
	 * @param text
	 */
	public void addItemInComboBox(String text) {
		comboProduct.addItem(text);
	}

	/**
	 * change Name product in ArrayList(OrderElement) to String[]
	 */
	public String[] toArray(ArrayList<OrderElement> array) {
		String[] temp = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			temp[i] = array.get(i).getName();
		}
		return temp;

	}
}
