package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import code.*;

public class HistoryOfStorePage extends JFrame {

	private Store store = MainMenu.getStore();
	private ProductExt product;
	private JLabel textCustomerProduct, textProduct;

	public HistoryOfStorePage() {
		super("History Of Store Page");
		getContentPane().setBackground(Color.BLACK);
		initComponent();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(200, 162, 650, 600);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
	}

	public void initComponent() {
		getContentPane().setLayout(null);
		// make shopper Present
		product = store.getProductList().get(StorePage.getIndexOfProduct());

		Container firstContainer = new Container();
		firstContainer.setBounds(0, 0, 650, 90);
		getContentPane().add(firstContainer);

		Container customerInformationContainer = new Container();
		customerInformationContainer.setBounds(0, 50, 650, 40);
		firstContainer.add(customerInformationContainer);
		customerInformationContainer.setLayout(new FlowLayout());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 615, 431);
		getContentPane().add(scrollPane);

		textProduct = new JLabel(product.toString());
		textProduct.setForeground(Color.WHITE);
		textProduct.setFont(new Font("Euphemia UCAS", Font.PLAIN, 16));
		customerInformationContainer.add(textProduct);

		JButton buttonclose = new JButton("CLOSE");
		buttonclose.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		buttonclose.setForeground(Color.DARK_GRAY);
		buttonclose.setBounds(6, 535, 117, 37);
		buttonclose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(buttonclose);

		ArrayList<Customer> allCustomer = store.checkProductHistory(product);
		String format = "%-10s %-10s %-18s %-24s %-6s %-4s<br>";
		String printHistoryList = "<pre>";
		printHistoryList += String.format(format, "customerID", "personID", "Name", "Lastname", "Gender", "Age");
		for (int i = 0; i < allCustomer.size(); i++) {
			printHistoryList += String.format(format, allCustomer.get(i).getCustomerID(), allCustomer.get(i).getID(),
					allCustomer.get(i).getName(), allCustomer.get(i).getLastname(), allCustomer.get(i).getGender(),
					Integer.toString(allCustomer.get(i).getAge()));
		}

		printHistoryList += "</pre>";
		textCustomerProduct = new JLabel("<html>" + printHistoryList + "</html>");
		textCustomerProduct.setVerticalAlignment(SwingConstants.TOP);
		textCustomerProduct.setHorizontalAlignment(SwingConstants.LEFT);

		scrollPane.setViewportView(textCustomerProduct);
	}

}
