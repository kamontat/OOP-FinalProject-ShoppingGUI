package gui.store;

import code.TableModel.ProductModel;
import code.behavior.ButtonFactory;
import code.behavior.Table;
import code.product.ProductExt;
import code.store.Store;
import gui.main.MainPage;
import oldgui.HistoryOfStorePage;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author kamontat
 * @since 21/5/59 - 12:26
 */
public class StorePage extends JFrame implements Table, ButtonFactory {
	private Store store = Store.getInstance();

	private JPanel panel;
	private JTextField searchField;
	private JTable table;
	private JButton mainButton;
	private JButton restockButton;
	private JButton historyButton;
	private JLabel revenueLabel;
	private JLabel expenseLabel;
	private JLabel profitLabel;
	private JButton checkButton;

	private ProductModel model;

	public StorePage() {
		super("Store Page");
		setContentPane(panel);
		model = new ProductModel(store.getAllProduct(true), new String[]{"ID", "Name", "Type", "Material", "Size", "Weight", "In Stock", "Restock", "Price", "Buying Price"});

		settingTable();

		// assign button
		toMain(this, mainButton);
		restockButton.addActionListener(e -> restock());
		history();
		check();

		updateLabel(store.getRevenue(), store.getExpense());
	}

	private void settingTable() {
		table.setModel(model);
		settingTable(table, new int[]{50, 190, 75, 180, 65, 65, 75, 75, 80, 100});

		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				// 10 mean enter
				if (e.getKeyCode() == 10) {
					restock();
				}
			}
		});

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(searchField, rowSorter);
	}

	private void restock() {
		int row = table.getSelectedRow();

		if (checkValid(row)) {
			ProductExt product = getProductAt(row);

			if (product.isRestock()) {
				store.addExpense(product.restockProductExt());
				store.setRestockProduct(false);
				updateLabel(store.getRevenue(), store.getExpense());
				updateTable(product.getCurrNumStock(), row, 6);
			} else {
				int choose = JOptionPane.showConfirmDialog(null, "This product still have in stock", "Do you sure?", JOptionPane.YES_NO_OPTION);
				if (choose == 0) {
					store.addExpense(product.restockProductExt());
					store.setRestockProduct(false);
					updateLabel(store.getRevenue(), store.getExpense());
					updateTable(product.getCurrNumStock(), row, 6);
				}
			}
		}
	}

	private void history() {
		historyButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (checkValid(row)) {
				HistoryOfStorePage page = new HistoryOfStorePage(getProductAt(row));
				page.run();
			}
			resetSelection(table);
		});
	}

	private void check() {
		checkButton.addActionListener(e -> {
			MainPage.reWriteStoreInfo();
			MainPage.reWriteProductInfo();

			JOptionPane.showMessageDialog(null, "Update text file successful", "Thank you", JOptionPane.INFORMATION_MESSAGE);
		});
	}

	private ProductExt getProductAt(int row) {
		String productID = String.valueOf(table.getValueAt(row, 0));
		return store.searchProductID(productID);
	}

	private void updateLabel(double revenue, double expense) {
		revenueLabel.setText("Revenue: " + revenue);
		expenseLabel.setText("Expense: " + expense);
		profitLabel.setText("Profit: " + (revenue - expense));
	}

	private void updateTable(Object newValue, int row, int column) {
		table.setValueAt(newValue, row, column);
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(970, 500));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
