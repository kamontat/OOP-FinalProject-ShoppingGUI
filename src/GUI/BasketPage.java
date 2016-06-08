package gui;

import code.customer.Customer;
import code.payment.Shipping;
import code.store.OrderElement;
import code.store.Store;
import gui1.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BasketPage extends JFrame {

	private Store store = Store.getInstance();
	private Customer shopper;
	private JLabel textProductCustomer;
	private JLabel textPriceShipping;
	private JLabel textFinalPrice;
	private JComboBox<String> comboProduct;

	public BasketPage() {
		super("Basket Page");
		getContentPane().setBackground(Color.BLACK);

		shopper = MainPage.shopper;

		initComponent();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(1000, 550, 650, 500);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
	}

	private void initComponent() {
		getContentPane().setLayout(null);

		comboProduct = new JComboBox<>(toArray(shopper.getBasketList()));
		comboProduct.setForeground(UIManager.getColor("Desktop.background"));
		comboProduct.setFont(new Font("Andale Mono", Font.PLAIN, 13));
		comboProduct.setBounds(453, 118, 197, 30);
		getContentPane().add(comboProduct);

		JButton buttonChange = new JButton("CHANGE");
		buttonChange.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
		buttonChange.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		buttonChange.setBounds(453, 170, 95, 40);
		buttonChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int productIndex = comboProduct.getSelectedIndex();
				OrderElement order = shopper.getBasketList().get(productIndex);
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
		buttonRemove.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		buttonRemove.setForeground(UIManager.getColor("ComboBox.disabledForeground"));
		buttonRemove.setBounds(555, 170, 95, 40);
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int productIndex = comboProduct.getSelectedIndex();
				OrderElement order = shopper.getBasketList().get(productIndex);
				store.refundStock(order);
				shopper.getBasketList().remove(productIndex);
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

		JLabel lblShipping = new JLabel("- Shipping -");
		lblShipping.setHorizontalAlignment(SwingConstants.CENTER);
		lblShipping.setFont(new Font("PingFang TC", Font.PLAIN, 25));
		lblShipping.setForeground(Color.WHITE);
		lblShipping.setBounds(453, 222, 197, 44);
		getContentPane().add(lblShipping);

		textPriceShipping = new JLabel("0");
		textPriceShipping.setHorizontalAlignment(SwingConstants.CENTER);
		textPriceShipping.setForeground(Color.WHITE);
		textPriceShipping.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		textPriceShipping.setBackground(Color.WHITE);
		textPriceShipping.setBounds(453, 266, 197, 60);
		updatePriceShipping();
		getContentPane().add(textPriceShipping);

		JLabel lblFinalPrice = new JLabel("- Final Price -");
		lblFinalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblFinalPrice.setForeground(Color.WHITE);
		lblFinalPrice.setFont(new Font("PingFang TC", Font.PLAIN, 25));
		lblFinalPrice.setBounds(453, 329, 197, 44);
		getContentPane().add(lblFinalPrice);

		textFinalPrice = new JLabel("0");
		textFinalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textFinalPrice.setForeground(Color.WHITE);
		textFinalPrice.setFont(new Font("Century Gothic", Font.PLAIN, 19));
		textFinalPrice.setBounds(453, 374, 197, 44);
		updateFinalPrice();
		getContentPane().add(textFinalPrice);

		Container firstContainer = new Container();
		firstContainer.setBounds(0, 0, 650, 90);
		getContentPane().add(firstContainer);

		Container customerInformationContainer = new Container();
		customerInformationContainer.setBounds(0, 64, 650, 26);
		firstContainer.add(customerInformationContainer);
		customerInformationContainer.setLayout(new FlowLayout());

		JLabel textCustomer = new JLabel(shopper.toString());
		textCustomer.setForeground(Color.WHITE);
		textCustomer.setFont(new Font("Century Gothic", Font.PLAIN, 17));
		customerInformationContainer.add(textCustomer);

		JLabel textMemberClass = new JLabel(shopper.getMemberClass().getName());
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

		JButton buttonclose = new JButton("CLOSE");
		buttonclose.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		buttonclose.setForeground(Color.DARK_GRAY);
		buttonclose.setBounds(6, 429, 117, 37);
		buttonclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(buttonclose);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 444, 316);
		getContentPane().add(scrollPane);

		textProductCustomer = new JLabel("");
		textProductCustomer.setVerticalAlignment(SwingConstants.TOP);
		textProductCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		setTextproductCustomerInBasket();

		scrollPane.setViewportView(textProductCustomer);
	}

	/**
	 * set basket of Customer in this JFrame
	 */
	public void setTextproductCustomerInBasket() {
		String printBasket = String.format("<pre>%-10s %-31s %-6s %-6s %-8s   %6s</pre>", "ProductID", "Name", "Size", "Weight", "Price", "Number");
		printBasket += shopper.getBasketString();
		textProductCustomer.setText("<html>" + printBasket + "</html>");
	}

	/**
	 * set basket of Customer in Shopping Page
	 */
	private void setTextproductCustomerInShopping() {
		String printBasket = String.format("<pre>%-10s %-31s %-6s %-6s %-8s   %6s</pre>", "ProductID", "Name", "Size", "Weight", "Price", "Number");
		printBasket += shopper.getBasketString();
		ShoppingPage.getTextProductCustomer().setText("<html>" + printBasket + "</html>");
		updateElseInShopping();
	}

	/**
	 * Update num product customer and price of product
	 */
	void updateElseInShopping() {
		int numProductCustmer = 0;
		int priceProductCustmer = 0;
		for (int i = 0; i < shopper.getBasketList().size(); i++) {
			numProductCustmer += shopper.getBasketList().get(i).getNum();
			priceProductCustmer += shopper.getBasketList().get(i).getNum() * shopper.getBasketList().get(i).getProduct().getPrice();
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
	void updatePriceShipping() {
		ArrayList<OrderElement> baskets = shopper.getBasketList();
		double totalWeight = 0;
		for (OrderElement basket : baskets) {
			totalWeight += basket.getProduct().getWeight() * basket.getNum();
		}
		Shipping shipping = new Shipping(totalWeight, code.constant.Shipping.NONE);
		String tempOutput = String.format("<html>" + "Weight: %.2f<br>Price: %d</html>", totalWeight, shipping.getShippingFee());
		textPriceShipping.setText(tempOutput);
	}

	/**
	 * Update final price to present and set in textLabel
	 */
	void updateFinalPrice() {
		ArrayList<OrderElement> baskets = shopper.getBasketList();
		double totalWeight = 0;
		for (OrderElement basket : baskets) {
			totalWeight += basket.getProduct().getWeight() * basket.getNum();
		}
		Shipping shipping = new Shipping(totalWeight, code.constant.Shipping.NONE);
		// priceProductCustmer
		String tempOutput = String.format("%,d", shipping.getShippingFee() + ShoppingPage.getPriceProductCustmer());
		textFinalPrice.setText(tempOutput);
	}

	/**
	 * add paramater text in combo box that text is product that customer add
	 * more
	 *
	 * @param text
	 * 		text to add in combo box
	 */
	void addItemInComboBox(String text) {
		comboProduct.addItem(text);
	}

	/**
	 * change Name product in ArrayList(OrderElement) to String[]
	 */
	private String[] toArray(ArrayList<OrderElement> array) {
		String[] temp = new String[array.size()];
		for (int i = 0; i < array.size(); i++) {
			temp[i] = array.get(i).getName();
		}
		return temp;

	}
}
