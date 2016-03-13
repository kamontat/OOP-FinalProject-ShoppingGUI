package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import Code.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StorePage extends JFrame {
	private Store store = MainMenu.getStore();
	static JTextField searchField;
	private JLabel txtRevenue, txtExpense, txtProfit;
	private JTextField txtStock;
	private JTextField textField_0, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6,
			textField_7, textField_8, textField_9, textField_10, textField_11, textField_12, textField_13, textField_14;
	private String showInDialog;
	private int currNumProduct;
	private JTextField txtProductType, txtProductId, txtName, txtSizeLength, txtMaterial, txtPrice;
	private JTextField txtWeigth;
	private JTextField txtBuyingPrice;
	private String[] headLine = new String[] { "Prodect Type", "Product ID", "Name", "Weight", "Size", "Material",
			"Buying Price", "Price" };
	private String[][] info = { { "Ring", "1001", "Blue Topazes", "3.5", "6\" ", "Gold", "10,000", "22,000" },
			{ "Ring", "1002", "Midnight Titanium", "5.1", "9\"", "Steel and Titanium", "9,400", "13,800" },
			{ "Ring", "1003", "Pink Diamonds", " 3.7", "6\"", "Diamonds & rose gold", "69,500", "202,000" },
			{ "Ring", "1004", "Silver Bow", "2.6", "6\"", "Silver", "3,700", "6,600" },
			{ "Ring", "1005", "X Diamonds", "4.2", "6\"", "Pink sapphires", "83,200", "253,000" },
			{ "Pendant", "1006", "Mixed Cluster", " 12.3", "Medium", "Platinum and Diamonds", "60,500", "155,000" },
			{ "Pendant", "1007", "Olive Leaf", "11.1", "Small", "Silver", "9,600", "13,000" },
			{ "Pendant", "1008", "Pierced", "10.7", "Small", "Gold and Diamonds", "16,000", "25,200" },
			{ "Pendant", "1009", "Silver Bow", "10.3", "Small", "Silver", "3,300", "5,600" },
			{ "Pendant", "1010", "Whitegold Bow", "11.6", "Small", "White gold & diamonds", "86,900", "111,000" },
			{ "Earing", "1011", "Blue Topazes Olive Leaf", "1.3", "Mini", "Gold & blue topazes", "16,800", "24,600" },
			{ "Earing", "1012", "Bow Earing", "1.5", "Mini", "Rose gold", "10,500", "14,200" },
			{ "Earing", "1013", "Color By The Yard", "1.1", "Mini", "Silver and Pink sapphires", "26,000", "30,600" },
			{ "Earing", "1014", "Rivals Night Sky", "2.4", "Medium", "Platinum & diamonds", "489,000", "504,000" },
			{ "Earing", "1015", "Silver Olive Leaf", "1.8", "Medium", "Silver", "2,900", "5,600" } };
	private DefaultTableModel model = new DefaultTableModel(info, headLine);
	private JTable table = new JTable(model);
	private TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
	private JLabel labelSearch;
	// check run only first time
	static private boolean check = true;
	private JButton btnHistory;
	private JLabel lblShowHistoryProduct;
	static private int indexOfProduct = 0;

	public static int getIndexOfProduct() {
		return indexOfProduct;
	}

	public static void setIndexOfProduct(int indexOfProduct) {
		StorePage.indexOfProduct = indexOfProduct;
	}

	public StorePage() {
		super("Store Page");
		getContentPane().setBackground(SystemColor.activeCaptionText);
		initComponent();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(50, 50, 1070, 600);
		setBackground(Color.PINK);
		setVisible(true);
		setResizable(false);
	}

	public static boolean isCheck() {
		return check;
	}

	public static void setCheck(boolean check) {
		StorePage.check = check;
	}

	public void initComponent() {
		this.setBackground(new Color(255, 228, 225));
		this.setForeground(Color.WHITE);
		getContentPane().setLayout(null);

		searchField = new JTextField();
		searchField.setBackground(Color.WHITE);
		searchField.setBounds(272, 82, 523, 25);
		getContentPane().add(searchField);
		searchField.setColumns(10);

		table.setRowSorter(rowSorter);
		searchField.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				String text = searchField.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = searchField.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}

		});

		table.setShowHorizontalLines(false);
		table.setForeground(Color.BLACK);
		table.setToolTipText("");
		table.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		table.setBounds(10, 150, 882, 240);
		getContentPane().add(table);

		JButton ButtonRestock = new JButton("RESTOCK");
		ButtonRestock.setForeground(new Color(25, 25, 112));
		ButtonRestock.setFont(new Font("Herculanum", Font.PLAIN, 25));
		ButtonRestock.setBounds(903, 217, 161, 60);
		getContentPane().add(ButtonRestock);

		txtRevenue = new JLabel();
		txtRevenue.setForeground(new Color(30, 144, 255));
		txtRevenue.setFont(new Font("Gurmukhi MT", Font.PLAIN, 20));
		txtRevenue.setHorizontalAlignment(SwingConstants.CENTER);
		txtRevenue.setText("Revenue : " + store.getRevenue());
		txtRevenue.setBounds(128, 410, 235, 35);
		getContentPane().add(txtRevenue);

		txtExpense = new JLabel();
		txtExpense.setHorizontalAlignment(SwingConstants.CENTER);
		txtExpense.setForeground(new Color(255, 0, 0));
		txtExpense.setFont(new Font("Gurmukhi MT", Font.PLAIN, 20));
		txtExpense.setText("Expense : " + store.getExpense());
		txtExpense.setBounds(370, 410, 235, 35);
		getContentPane().add(txtExpense);

		txtProfit = new JLabel();
		txtProfit.setHorizontalAlignment(SwingConstants.CENTER);
		txtProfit.setForeground(new Color(255, 250, 250));
		txtProfit.setFont(new Font("Gurmukhi MT", Font.PLAIN, 25));
		txtProfit.setText("Profit : " + store.getProfit());
		txtProfit.setBounds(607, 410, 235, 35);
		getContentPane().add(txtProfit);

		txtStock = new JTextField();
		txtStock.setFont(new Font("Heiti TC", Font.PLAIN, 14));
		txtStock.setBackground(SystemColor.window);
		txtStock.setHorizontalAlignment(SwingConstants.CENTER);
		txtStock.setText("- STOCK -");
		txtStock.setBounds(10, 423, 86, 60);
		getContentPane().add(txtStock);
		txtStock.setColumns(10);

		textField_0 = new JTextField((store.getProductList().get(0).getCurrNumStock() + ""));
		textField_0.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_0.setHorizontalAlignment(SwingConstants.CENTER);
		textField_0.setBounds(95, 451, 49, 32);
		getContentPane().add(textField_0);
		textField_0.setColumns(10);

		textField_1 = new JTextField((store.getProductList().get(1).getCurrNumStock() + ""));
		textField_1.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setBounds(146, 451, 49, 32);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField((store.getProductList().get(2).getCurrNumStock() + ""));
		textField_2.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setBounds(197, 451, 49, 32);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField((store.getProductList().get(3).getCurrNumStock() + ""));
		textField_3.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setBounds(248, 451, 49, 32);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField((store.getProductList().get(4).getCurrNumStock() + ""));
		textField_4.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setBounds(299, 451, 49, 32);
		getContentPane().add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField((store.getProductList().get(5).getCurrNumStock() + ""));
		textField_5.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setBounds(350, 451, 49, 32);
		getContentPane().add(textField_5);
		textField_5.setColumns(10);

		textField_6 = new JTextField((store.getProductList().get(6).getCurrNumStock() + ""));
		textField_6.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setBounds(401, 451, 49, 32);
		getContentPane().add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField(store.getProductList().get(7).getCurrNumStock() + "");
		textField_7.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setBounds(452, 451, 49, 32);
		getContentPane().add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField((store.getProductList().get(8).getCurrNumStock() + ""));
		textField_8.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setBounds(503, 451, 49, 32);
		getContentPane().add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField((store.getProductList().get(9).getCurrNumStock() + ""));
		textField_9.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setBounds(554, 451, 49, 32);
		getContentPane().add(textField_9);
		textField_9.setColumns(10);

		textField_10 = new JTextField((store.getProductList().get(10).getCurrNumStock() + ""));
		textField_10.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_10.setHorizontalAlignment(SwingConstants.CENTER);
		textField_10.setBounds(605, 451, 49, 32);
		getContentPane().add(textField_10);
		textField_10.setColumns(10);

		textField_11 = new JTextField((store.getProductList().get(11).getCurrNumStock() + ""));
		textField_11.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setBounds(656, 451, 49, 32);
		getContentPane().add(textField_11);
		textField_11.setColumns(10);

		textField_12 = new JTextField((store.getProductList().get(12).getCurrNumStock() + ""));
		textField_12.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_12.setHorizontalAlignment(SwingConstants.CENTER);
		textField_12.setBounds(707, 451, 49, 32);
		getContentPane().add(textField_12);
		textField_12.setColumns(10);

		textField_13 = new JTextField((store.getProductList().get(13).getCurrNumStock() + ""));
		textField_13.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_13.setHorizontalAlignment(SwingConstants.CENTER);
		textField_13.setBounds(758, 451, 49, 32);
		getContentPane().add(textField_13);
		textField_13.setColumns(10);

		textField_14 = new JTextField((store.getProductList().get(14).getCurrNumStock() + ""));
		textField_14.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		textField_14.setHorizontalAlignment(SwingConstants.CENTER);
		textField_14.setBounds(809, 451, 49, 32);
		getContentPane().add(textField_14);
		textField_14.setColumns(10);

		makeTextFieldCannotChange();

		JLabel lblNewLabel_1 = new JLabel("RESTOCK PRODUCT");
		lblNewLabel_1.setForeground(SystemColor.menu);
		lblNewLabel_1.setFont(new Font("Heiti TC", Font.BOLD, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(893, 187, 171, 32);
		getContentPane().add(lblNewLabel_1);

		JComboBox<String> choice = new JComboBox<String>(
				new String[] { "Choose Product", "-- Rings --", "1. (1001) Blue Topaz", "2. (1002) Midnight Titanium",
						"3. (1003) Pink Diamonds", "4. (1004) Silver Bow", "5. (1005) X Diamonds", "-- Pendants --",
						"1. (1006) Mixed Cluster Pendant", "2. (1007) Olive Leaf Pendant", "3. (1008) Pierced Pendant",
						"4. (1009) Silver Bow Pendant", "5. (1010) White Gold Bow Pendant", "-- Earings --",
						"1. (1011) Blue Topazes Olive Leaf", "2. (1012) Bow Earring", "3. (1013) Color By The Yard",
						"4. (1014) Rivals Night Sky", "5. (1015) Silver Olive Leaf" });
		choice.setToolTipText("Choose Product");
		choice.setBounds(893, 150, 171, 32);
		getContentPane().add(choice);

		txtProductType = new JTextField();
		txtProductType.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		txtProductType.setBackground(SystemColor.windowBorder);
		txtProductType.setHorizontalAlignment(SwingConstants.CENTER);
		txtProductType.setText("PRODUCT TYPE");
		txtProductType.setBounds(10, 124, 108, 20);
		getContentPane().add(txtProductType);
		txtProductType.setColumns(10);

		txtProductId = new JTextField();
		txtProductId.setFont(new Font("Heiti TC", Font.PLAIN, 12));
		txtProductId.setBackground(SystemColor.windowBorder);
		txtProductId.setHorizontalAlignment(SwingConstants.CENTER);
		txtProductId.setText("PRODUCT ID");
		txtProductId.setBounds(117, 122, 108, 24);
		getContentPane().add(txtProductId);
		txtProductId.setColumns(10);

		txtName = new JTextField();
		txtName.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		txtName.setBackground(SystemColor.windowBorder);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setText("NAME");
		txtName.setBounds(223, 124, 118, 20);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		txtSizeLength = new JTextField();
		txtSizeLength.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		txtSizeLength.setBackground(SystemColor.windowBorder);
		txtSizeLength.setHorizontalAlignment(SwingConstants.CENTER);
		txtSizeLength.setText("SIZE / LENGTH");
		txtSizeLength.setBounds(446, 124, 117, 20);
		getContentPane().add(txtSizeLength);
		txtSizeLength.setColumns(10);

		txtMaterial = new JTextField();
		txtMaterial.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		txtMaterial.setText("MATERIAL");
		txtMaterial.setBackground(SystemColor.windowBorder);
		txtMaterial.setHorizontalAlignment(SwingConstants.CENTER);
		txtMaterial.setBounds(558, 124, 117, 20);
		getContentPane().add(txtMaterial);
		txtMaterial.setColumns(10);

		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		txtPrice.setBackground(SystemColor.windowBorder);
		txtPrice.setText("PRICE");
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setBounds(781, 124, 111, 20);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);

		txtWeigth = new JTextField();
		txtWeigth.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		txtWeigth.setBackground(SystemColor.windowBorder);
		txtWeigth.setText("WEIGHT");
		txtWeigth.setHorizontalAlignment(SwingConstants.CENTER);
		txtWeigth.setBounds(336, 124, 111, 20);
		getContentPane().add(txtWeigth);
		txtWeigth.setColumns(10);

		txtBuyingPrice = new JTextField();
		txtBuyingPrice.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		txtBuyingPrice.setBackground(SystemColor.windowBorder);
		txtBuyingPrice.setText("BUYING PRICE");
		txtBuyingPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtBuyingPrice.setBounds(672, 124, 111, 20);
		getContentPane().add(txtBuyingPrice);
		txtBuyingPrice.setColumns(10);

		JLabel label = new JLabel(
				"1001       1002        1003        1004        1005        1006       1007        1008        1009        1010        1011       1012        1013        1014       1015 ");
		label.setForeground(SystemColor.text);
		label.setFont(new Font("Heiti TC", Font.PLAIN, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(95, 484, 763, 20);
		getContentPane().add(label);

		labelSearch = new JLabel("- SEARCH -");
		labelSearch.setForeground(Color.WHITE);
		labelSearch.setFont(new Font("Heiti TC", Font.PLAIN, 16));
		labelSearch.setBounds(170, 84, 90, 20);
		getContentPane().add(labelSearch);

		JButton buttonMainMenu = new JButton("- MAIN MENU -");
		buttonMainMenu.setBackground(SystemColor.controlShadow);
		buttonMainMenu.setFont(new Font("Heiti TC", Font.PLAIN, 16));
		buttonMainMenu.setBounds(22, 516, 144, 48);
		buttonMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				check = false;
				try {
					MainMenu menu = new MainMenu();
					menu.run();
					setVisible(false);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(buttonMainMenu);

		JLabel lblWelcomeTo = new JLabel("- WELCOME TO MY STORE'S PAGE -");
		lblWelcomeTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeTo.setForeground(SystemColor.menu);
		lblWelcomeTo.setFont(new Font("Herculanum", Font.BOLD, 35));
		lblWelcomeTo.setBounds(232, 23, 563, 35);
		getContentPane().add(lblWelcomeTo);

		btnHistory = new JButton("HISTORY");
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean check = true;
				String msg = (String) choice.getSelectedItem();

				switch (msg) {
				case "Choose Product":
				case "-- Rings --":
				case "-- Pendants --":
				case "-- Earings --":
					showInDialog = "Please select the product!";
					showDialog(showInDialog);
					check = false;
					break;
				case "1. (1001) Blue Topaz":
					indexOfProduct = 0;
					break;
				case "2. (1002) Midnight Titanium":
					indexOfProduct = 1;
					break;
				case "3. (1003) Pink Diamonds":
					indexOfProduct = 2;
					break;
				case "4. (1004) Silver Bow":
					indexOfProduct = 3;
					break;
				case "5. (1005) X Diamonds":
					indexOfProduct = 4;
					break;
				case "1. (1006) Mixed Cluster Pendant":
					indexOfProduct = 5;
					break;
				case "2. (1007) Olive Leaf Pendant":
					indexOfProduct = 6;
					break;
				case "3. (1008) Pierced Pendant":
					indexOfProduct = 7;
					break;
				case "4. (1009) Silver Bow Pendant":
					indexOfProduct = 8;
					break;
				case "5. (1010) White Gold Bow Pendant":
					indexOfProduct = 9;
					break;

				case "1. (1011) Blue Topazes Olive Leaf":
					indexOfProduct = 10;
					break;
				case "2. (1012) Bow Earring":
					indexOfProduct = 11;
					break;
				case "3. (1013) Color By The Yard":
					indexOfProduct = 12;
					break;
				case "4. (1014) Rivals Night Sky":
					indexOfProduct = 13;
					break;
				case "5. (1015) Silver Olive Leaf":
					indexOfProduct = 14;
					break;
				}
				if (check) {
					HistoryOfStorePage history = new HistoryOfStorePage();
					history.run();
				}
			}
		});
		btnHistory.setForeground(new Color(25, 25, 112));
		btnHistory.setFont(new Font("Herculanum", Font.PLAIN, 25));
		btnHistory.setBounds(903, 330, 161, 60);
		getContentPane().add(btnHistory);

		lblShowHistoryProduct = new JLabel("SHOW HISTORY PRODUCT");
		lblShowHistoryProduct.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowHistoryProduct.setForeground(Color.WHITE);
		lblShowHistoryProduct.setFont(new Font("Heiti TC", Font.BOLD, 13));
		lblShowHistoryProduct.setBounds(893, 289, 171, 32);
		getContentPane().add(lblShowHistoryProduct);

		ButtonRestock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int Product1 = getNumber(textField_0);
				int Product2 = getNumber(textField_1);
				int Product3 = getNumber(textField_2);
				int Product4 = getNumber(textField_3);
				int Product5 = getNumber(textField_4);
				int Product6 = getNumber(textField_5);
				int Product7 = getNumber(textField_6);
				int Product8 = getNumber(textField_7);
				int Product9 = getNumber(textField_8);
				int Product10 = getNumber(textField_9);
				int Product11 = getNumber(textField_10);
				int Product12 = getNumber(textField_11);
				int Product13 = getNumber(textField_12);
				int Product14 = getNumber(textField_13);
				int Product15 = getNumber(textField_14);

				String msg = (String) choice.getSelectedItem();

				switch (msg) {
				case "Choose Product":
				case "-- Rings --":
				case "-- Pendants --":
				case "-- Earings --":
					showInDialog = "Please select the product!";
					showDialog(showInDialog);
					break;
				case "1. (1001) Blue Topaz":
					setText(0, textField_0, Product1, 3);
					break;
				case "2. (1002) Midnight Titanium":
					setText(1, textField_1, Product2, 3);
					break;
				case "3. (1003) Pink Diamonds":
					setText(2, textField_2, Product3, 1);
					break;
				case "4. (1004) Silver Bow":
					setText(3, textField_3, Product4, 5);
					break;
				case "5. (1005) X Diamonds":
					setText(4, textField_4, Product5, 1);
					break;
				case "1. (1006) Mixed Cluster Pendant":
					setText(5, textField_5, Product6, 1);
					break;
				case "2. (1007) Olive Leaf Pendant":
					setText(6, textField_6, Product7, 3);
					break;
				case "3. (1008) Pierced Pendant":
					setText(7, textField_7, Product8, 3);
					break;
				case "4. (1009) Silver Bow Pendant":
					setText(8, textField_8, Product9, 5);
					break;
				case "5. (1010) White Gold Bow Pendant":
					setText(9, textField_9, Product10, 1);
					break;

				case "1. (1011) Blue Topazes Olive Leaf":
					setText(10, textField_10, Product11, 3);
					break;
				case "2. (1012) Bow Earring":
					setText(11, textField_11, Product12, 3);
					break;
				case "3. (1013) Color By The Yard":
					setText(12, textField_12, Product13, 3);
					break;
				case "4. (1014) Rivals Night Sky":
					setText(13, textField_13, Product14, 1);
					break;
				case "5. (1015) Silver Olive Leaf":
					setText(14, textField_14, Product15, 5);
					break;
				}

			}
		});
	}

	public void setText(int index, JTextField text, int numProduct, int numRestock) {
		showInDialog = "Your restock is successful!";
		showDialog(showInDialog);
		currNumProduct = updateNumber(numProduct, numRestock);
		text.setText(currNumProduct + "");
		store.getProductList().get(index).setCurrNumStock(currNumProduct);
		store.restockInstore(index);
		txtRevenue.setText("Revenue : " + store.getRevenue());
		txtExpense.setText("Expense : " + store.getExpense());
		txtProfit.setText("Profit : " + store.getProfit());
	}

	public void makeTextFieldCannotChange() {
		txtStock.setEditable(false);
		textField_0.setEditable(false);
		textField_1.setEditable(false);
		textField_2.setEditable(false);
		textField_3.setEditable(false);
		textField_4.setEditable(false);
		textField_5.setEditable(false);
		textField_6.setEditable(false);
		textField_7.setEditable(false);
		textField_8.setEditable(false);
		textField_9.setEditable(false);
		textField_10.setEditable(false);
		textField_11.setEditable(false);
		textField_12.setEditable(false);
		textField_13.setEditable(false);
		textField_14.setEditable(false);
	}

	private void showDialog(String str) {
		String str2 = str;
		if (!str2.equals("")) {
			JOptionPane.showMessageDialog(this, str2, "N2M'S JEWELRY STORE", JOptionPane.PLAIN_MESSAGE);
		} else {
			str2 = "";
		}
	}

	public int getNumber(JTextField text) {
		int number = 0;
		String info = text.getText();
		number = Integer.valueOf(info);
		return number;
	}

	public int updateNumber(int product, int numOfRestock) {
		product += numOfRestock;
		return product;
	}
}