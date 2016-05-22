package gui;

import code.customer.Customer;
import code.store.Store;
import gui1.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoryOfCustomerPage extends JFrame {

	private Store store = MainPage.store;
	private Customer shopper;
	private JLabel textProductCustomer, textMemberClass, textCustomer;

	public HistoryOfCustomerPage(Customer customer) {
		super("History Of Customer Page");
		getContentPane().setBackground(Color.BLACK);

		this.shopper = customer;

		initComponent();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(200, 162, 650, 600);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setVisible(true);
	}

	public void initComponent() {
		getContentPane().setLayout(null);

		Container firstContainer = new Container();
		firstContainer.setBounds(0, 0, 650, 90);
		getContentPane().add(firstContainer);

		Container customerInformationContainer = new Container();
		customerInformationContainer.setBounds(-1, 50, 650, 40);
		firstContainer.add(customerInformationContainer);
		customerInformationContainer.setLayout(new FlowLayout());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 101, 615, 431);
		getContentPane().add(scrollPane);

		textCustomer = new JLabel(shopper.toString());
		textCustomer.setForeground(Color.WHITE);
		textCustomer.setFont(new Font("Gujarati MT", Font.PLAIN, 23));
		customerInformationContainer.add(textCustomer);

		textMemberClass = new JLabel(shopper.getMemberClass());
		textMemberClass.setHorizontalAlignment(SwingConstants.CENTER);
		textMemberClass.setBounds(232, 18, 138, 41);
		firstContainer.add(textMemberClass);
		textMemberClass.setFont(new Font("Gujarati MT", Font.PLAIN, 27));
		textMemberClass.setForeground(Color.WHITE);

		JLabel lblClassMember = new JLabel("- Class Member -");
		lblClassMember.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassMember.setForeground(Color.WHITE);
		lblClassMember.setFont(new Font("Heiti TC", Font.PLAIN, 19));
		lblClassMember.setBounds(30, 19, 200, 33);
		firstContainer.add(lblClassMember);

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

		String printHistoryList = String.format("<pre>%-10s %-31s %-25s %-6s %-6s %-8s   %6s</pre>", "ProductID", "Name", "Material", "Size", "Weight", "Price", "Number");

		printHistoryList += shopper.getHistoryListString();
		shopper.clearBasket();
		
		textProductCustomer = new JLabel("<html>" + printHistoryList + "</html>");
		textProductCustomer.setVerticalAlignment(SwingConstants.TOP);
		textProductCustomer.setHorizontalAlignment(SwingConstants.LEFT);

		scrollPane.setViewportView(textProductCustomer);
	}

}
