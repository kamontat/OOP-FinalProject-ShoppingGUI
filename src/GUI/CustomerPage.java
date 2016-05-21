package gui;

import code.customer.Customer;
import code.store.Store;
import gui1.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class CustomerPage extends JFrame {
	// every J must have in this program
	private JTextField searchByCustomerName, searchByIdCustomer;
	private JLabel labelCustomerList;
	private JComboBox<String> comboBox;
	// get customerList and store in MainMenu
	private Store store = MainPage.store;
	private ArrayList<Customer> customerList = store.getCustomerList();
	// variable else
	private String id, name, lastname, gender, age, member;
	// variable use different class
	private static int indexOfCustomer = 0;
	// check run only first time
	static private boolean check = true;

	public CustomerPage() throws Exception {
		super("Customer Page");
		getContentPane().setBackground(Color.BLACK);
		initComponent();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static int getIndexOfCustomer() {
		return indexOfCustomer;
	}

	public static boolean isCheck() {
		return check;
	}

	public static void setCheck(boolean check) {
		CustomerPage.check = check;
	}

	public static void setIndexOfCustomer(int indexOfCustomer) {
		CustomerPage.indexOfCustomer = indexOfCustomer;
	}

	public void run() {
		setBounds(50, 50, 1000, 875);
		setBackground(new Color(216, 191, 216));
		setVisible(true);
		setResizable(false);
	}

	public void initComponent() throws Exception {
		getContentPane().setLayout(null);

		// create Container
		Container customerListContainer = new Container();
		customerListContainer.setBackground(new Color(255, 240, 245));
		customerListContainer.setBounds(5, 153, 945, 585);
		customerListContainer.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(42, 81, 880, 496);
		customerListContainer.add(scrollPane);

		Container searchAndAddContainer = new Container();
		searchAndAddContainer.setBackground(new Color(230, 230, 250));
		searchAndAddContainer.setBounds(5, 74, 940, 89);
		searchAndAddContainer.setLayout(null);

		Container removeAndSelectContainer = new Container();
		removeAndSelectContainer.setBackground(Color.DARK_GRAY);
		removeAndSelectContainer.setBounds(180, 737, 764, 95);
		removeAndSelectContainer.setLayout(null);

		// create Text
		JLabel textId = new JLabel("- ID -");
		textId.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setForeground(new Color(255, 255, 255));
		textId.setBackground(new Color(255, 255, 255));
		textId.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textId.setBounds(42, 53, 116, 16);
		customerListContainer.add(textId);

		JLabel textName = new JLabel("- NAME - ");
		textName.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textName.setForeground(new Color(255, 255, 255));
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setForeground(new Color(255, 255, 255));
		textId.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textName.setBounds(173, 53, 116, 16);
		customerListContainer.add(textName);

		JLabel textLastname = new JLabel("- LASTNAME -");
		textLastname.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textLastname.setForeground(new Color(255, 255, 255));
		textLastname.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setForeground(new Color(255, 255, 255));
		textId.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textLastname.setBounds(345, 53, 126, 16);
		customerListContainer.add(textLastname);

		JLabel textGender = new JLabel("- GENDER -");
		textGender.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textGender.setForeground(new Color(255, 255, 255));
		textGender.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setForeground(new Color(255, 255, 255));
		textId.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textGender.setBounds(530, 53, 116, 16);
		customerListContainer.add(textGender);

		JLabel textAge = new JLabel("- AGE -");
		textAge.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textAge.setForeground(new Color(255, 255, 255));
		textAge.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setForeground(new Color(255, 255, 255));
		textId.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textAge.setBounds(683, 52, 116, 16);
		customerListContainer.add(textAge);

		JLabel textClass = new JLabel("- CLASS -");
		textClass.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textClass.setForeground(new Color(255, 255, 255));
		textClass.setHorizontalAlignment(SwingConstants.CENTER);
		textId.setForeground(new Color(255, 255, 255));
		textId.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		textClass.setBounds(807, 53, 116, 16);
		customerListContainer.add(textClass);

		JLabel textChoose = new JLabel(".. WHAT ID YOU WANT ..");
		textChoose.setHorizontalAlignment(SwingConstants.CENTER);
		textChoose.setBackground(Color.BLACK);
		textChoose.setForeground(Color.WHITE);
		textChoose.setFont(new Font("Heiti TC", Font.PLAIN, 15));
		textChoose.setBounds(19, 38, 214, 16);
		removeAndSelectContainer.add(textChoose);

		JLabel lblHeartToHeart = new JLabel("H E A R T   T O   H E A R T   By  N 2 M ");
		lblHeartToHeart.setFont(new Font("LiSong Pro", Font.PLAIN, 15));
		lblHeartToHeart.setForeground(Color.WHITE);
		lblHeartToHeart.setBounds(346, 44, 282, 36);
		getContentPane().add(lblHeartToHeart);

		JLabel textWelcome = new JLabel("- WELCOME TO CUSTOMER'S PAGE -");
		textWelcome.setForeground(SystemColor.text);
		textWelcome.setBounds(276, 16, 469, 41);
		getContentPane().add(textWelcome);
		textWelcome.setFont(new Font("Heiti TC", Font.BOLD, 25));

		// display Customer List
		labelCustomerList = new JLabel("<html>" + toCustomerListString() + "</html>");
		labelCustomerList.setFont(new Font("LiSong Pro", Font.BOLD, 13));
		labelCustomerList.setVerticalAlignment(SwingConstants.TOP);
		scrollPane.setViewportView(labelCustomerList);

		// assign textField in JFrame
		searchByIdCustomer = new JTextField();
		searchByIdCustomer.setBackground(Color.WHITE);
		searchByIdCustomer.setBounds(251, 20, 350, 50);
		searchByIdCustomer.setColumns(10);
		removeAndSelectContainer.add(searchByIdCustomer);

		searchByCustomerName = new JTextField();
		searchByCustomerName.setBackground(new Color(245, 245, 245));
		searchByCustomerName.setBounds(236, 26, 450, 43);
		searchByCustomerName.setColumns(1);
		searchAndAddContainer.add(searchByCustomerName);

		// create new button
		JButton buttonSearch = new JButton("SEARCH");
		buttonSearch.setForeground(Color.DARK_GRAY);
		buttonSearch.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		buttonSearch.setBounds(693, 19, 117, 30);
		searchAndAddContainer.add(buttonSearch);

		JButton buttonClear = new JButton("CLEAR");
		buttonClear.setForeground(Color.DARK_GRAY);
		buttonClear.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		buttonClear.setBounds(817, 20, 117, 29);
		searchAndAddContainer.add(buttonClear);

		JButton buttonAdd = new JButton("ADD");
		buttonAdd.setForeground(Color.DARK_GRAY);
		buttonAdd.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		buttonAdd.setBounds(693, 54, 117, 29);
		searchAndAddContainer.add(buttonAdd);

		JButton buttonSelect = new JButton("SELECT");
		buttonSelect.setBounds(615, 10, 117, 29);
		buttonSelect.setForeground(Color.DARK_GRAY);
		buttonSelect.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		removeAndSelectContainer.add(buttonSelect);

		JButton buttonRemove = new JButton("REMOVE");
		buttonRemove.setForeground(Color.DARK_GRAY);
		buttonRemove.setBackground(new Color(230, 230, 250));
		buttonRemove.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		buttonRemove.setBounds(615, 35, 117, 29);
		removeAndSelectContainer.add(buttonRemove);

		JButton buttonHistoryList = new JButton("HistoryList");
		buttonHistoryList.setForeground(Color.DARK_GRAY);
		buttonHistoryList.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		buttonHistoryList.setBackground(new Color(230, 230, 250));
		buttonHistoryList.setBounds(615, 60, 117, 29);
		removeAndSelectContainer.add(buttonHistoryList);

		JButton buttonMainMenu = new JButton("MAIN MENU");
		buttonMainMenu.setForeground(Color.DARK_GRAY);
		buttonMainMenu.setFont(new Font("Century Gothic", Font.PLAIN, 15));
		buttonMainMenu.setBounds(23, 759, 123, 51);
		getContentPane().add(buttonMainMenu);

		// make button valid
		buttonSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> indexList = store.searchCustomer(comboBox.getSelectedItem().toString(), searchByCustomerName.getText());
				if (indexList.size() != 0) {
					String textCustomerSearchList = "";
					for (Integer index : indexList) {
						textCustomerSearchList += String.format("<pre>     %-14s  %-18s  %-24s  %-17s  %3d  %16s</pre>", store.getCustomerList().get(index).getID(), store.getCustomerList().get(index).getName(), store.getCustomerList().get(index).getLastname(), store.getCustomerList().get(index).getGender(), store.getCustomerList().get(index).getAge(), store.getCustomerList().get(index).getMemberClass());
					}
					labelCustomerList.setText("<html>" + textCustomerSearchList + "</html>");
				} else {
					JOptionPane.showMessageDialog(null, "Customer Not Found", "Unknown Customer", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		buttonClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String textCustomerList = toCustomerListString();
				labelCustomerList.setText("<html>" + textCustomerList + "</html>");
				searchByCustomerName.setText("");
				searchByIdCustomer.setText("");
			}
		});

		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				id = JOptionPane.showInputDialog(null, "ID(4 Digit) : ", "ADD ID", JOptionPane.PLAIN_MESSAGE);
				name = JOptionPane.showInputDialog(null, "NAME : ", "ADD NAME", JOptionPane.PLAIN_MESSAGE);
				lastname = JOptionPane.showInputDialog(null, "LASTNAME : ", "ADD LASTNAME", JOptionPane.PLAIN_MESSAGE);
				gender = JOptionPane.showInputDialog(null, "GENDER(M/F) : ", "ADD GENDER", JOptionPane.PLAIN_MESSAGE);
				age = JOptionPane.showInputDialog(null, "AGE : ", "ADD AGE", JOptionPane.PLAIN_MESSAGE);
				member = JOptionPane.showInputDialog(null, "Member(None/Green/Silver/Gold) : ", "ADD MEMBER", JOptionPane.PLAIN_MESSAGE);
				Customer shopper = new Customer(id, name, lastname, gender, Integer.parseInt(age), member);
				store.addCustomer(shopper);
				// write CustomerList to file text
				writeCustomerList();
				// update display CustomerList
				String textCustomerList = toCustomerListString();
				labelCustomerList.setText("<html>" + textCustomerList + "</html>");

			}
		});

		buttonSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexOfCustomer = store.searchIDCustomer(searchByIdCustomer.getText());
				if (indexOfCustomer < 0) {
					JOptionPane.showMessageDialog(null, "Please, Enter ID", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					ShoppingPage shopping = new ShoppingPage();
					shopping.run();
					setVisible(false);
				}
			}
		});

		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int index = store.searchIDCustomer(searchByIdCustomer.getText());
				if (index < 0) {
					JOptionPane.showMessageDialog(null, "Please, Enter ID", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					// remove from CustomerList in store
					store.removeCustomer(index);
					// update display CustomerList
					String textCustomerList = toCustomerListString();
					labelCustomerList.setText("<html>" + textCustomerList + "</html>");
					// write CustomerList to file text
					writeCustomerList();
				}
			}
		});

		buttonHistoryList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				indexOfCustomer = store.searchIDCustomer(searchByIdCustomer.getText());
				if (indexOfCustomer < 0) {
					JOptionPane.showMessageDialog(null, "Please, Enter ID", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					HistoryOfCustomerPage history = new HistoryOfCustomerPage();
					history.run();
				}
			}
		});

		buttonMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage menu = new MainPage();
				menu.run(getLocation());
				dispose();
			}
		});
		
		// create new JComboBox
		comboBox = new JComboBox<>(new String[]{"Name", "Lastname", "Age", "MemberClass"});
		comboBox.setBounds(39, 35, 185, 27);
		searchAndAddContainer.add(comboBox);

		// add container to JFrame
		getContentPane().add(searchAndAddContainer);
		getContentPane().add(customerListContainer);
		getContentPane().add(removeAndSelectContainer);
	}

	/**
	 * Update text File with the Newest CustomerList in store
	 */
	public void writeCustomerList() {
		File output = new File("src/textfile/Customer.txt");
		try {
			FileWriter write = new FileWriter(output);
			for (int i = 0; i < store.getCustomerList().size(); i++) {
				String tempOutput = String.format("%s:%s:%s:%s:%d:%s \n", store.getCustomerList().get(i).getID(), store.getCustomerList().get(i).getName(), store.getCustomerList().get(i).getLastname(), store.getCustomerList().get(i).getGender(), store.getCustomerList().get(i).getAge(), store.getCustomerList().get(i).getMemberClass());
				write.write(tempOutput);
			}
			write.close();
		} catch (Exception e) {
			System.err.println(output + " file isn't exist.");
		}
	}

	/**
	 * @return Customer List
	 */
	public String toCustomerListString() {
		String textCustomerList = "";
		for (int i = 0; i < customerList.size(); i++) {
			textCustomerList += String.format("<pre>     %-14s  %-18s  %-24s  %-17s  %3d  %16s</pre>", store.getCustomerList().get(i).getID(), store.getCustomerList().get(i).getName(), store.getCustomerList().get(i).getLastname(), store.getCustomerList().get(i).getGender(), store.getCustomerList().get(i).getAge(), store.getCustomerList().get(i).getMemberClass());
		}
		return textCustomerList;

	}
}
