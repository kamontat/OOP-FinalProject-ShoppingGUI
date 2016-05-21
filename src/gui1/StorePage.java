package gui1;

import code.product.ProductExt;
import code.store.Store;
import gui.HistoryOfStorePage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author kamontat
 * @since 21/5/59 - 12:26
 */
public class StorePage extends JFrame {
	private Store store = MainPage.store;

	private JPanel panel;
	private JTextField searchField;
	private JTable table;
	private JButton mainButton;
	private JButton restockButton;
	private JButton historyButton;
	private JLabel revenueLabel;
	private JLabel expenseLabel;
	private JLabel profitLabel;

	public StorePage() {
		super("Store Page");
		setContentPane(panel);

		settingTable();

		toMain();
		restock();
		history();

		updateLabel(store.getRevenue(), store.getExpense());
	}

	private void settingTable() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new PersonModel(MainPage.getProductList(), new String[]{"Name", "Material", "Size", "Weight", "In Stock", "Restock", "Price", "Buying Price"}));

		table.getColumnModel().getColumn(0).setMinWidth(240); // name
		table.getColumnModel().getColumn(1).setMinWidth(180); // material
		table.getColumnModel().getColumn(2).setMinWidth(65); // size
		table.getColumnModel().getColumn(3).setMinWidth(65); // weight
		table.getColumnModel().getColumn(4).setMinWidth(75); // stock
		table.getColumnModel().getColumn(5).setMinWidth(65); // restock
		table.getColumnModel().getColumn(6).setMinWidth(80); // price
		table.getColumnModel().getColumn(7).setMinWidth(85); // buying price

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(rowSorter);
	}

	private void searching(TableRowSorter<TableModel> sorter) {
		searchField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try {
						sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					} catch (Exception ignored) {

					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try {
						sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					} catch (Exception ignored) {

					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					try {
						sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
					} catch (Exception ignored) {

					}
				}
			}
		});
	}

	private void toMain() {
		mainButton.addActionListener(e -> {
			MainPage page = new MainPage();
			page.run(getLocation());
			dispose();
		});
	}

	private void restock() {
		restockButton.addActionListener(e -> {
			int row = table.getSelectedRow();

			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Please, choose some product", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				String productName = String.valueOf(table.getValueAt(row, 0));
				ProductExt product = store.searchProduct(productName);

				if (product.isRestock()) {
					store.addExpense(product.restockProductExt());
					store.setRestockProduct(false);
					updateLabel(store.getRevenue(), store.getExpense());
					updateTable(product.getCurrNumStock(), row, 4);
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "This product still have in stock", "Do you sure?", JOptionPane.YES_NO_OPTION);
					if (choose == 0) {
						store.addExpense(product.restockProductExt());
						store.setRestockProduct(false);
						updateLabel(store.getRevenue(), store.getExpense());
						updateTable(product.getCurrNumStock(), row, 4);
					}
				}
			}
		});
	}

	private void history() {
		historyButton.addActionListener(e -> {

			int row = table.getSelectedRow();
			String productName = String.valueOf(table.getValueAt(row, 0));
			ProductExt product = store.searchProduct(productName);

			HistoryOfStorePage page = new HistoryOfStorePage(product);
			page.run();
		});
	}

	private void updateLabel(double reven, double expen) {
		revenueLabel.setText("Revenue: " + reven);
		expenseLabel.setText("Expense: " + expen);
		profitLabel.setText("Profit: " + (reven - expen));
	}

	private void updateTable(Object newValue, int row, int column) {
		table.setValueAt(newValue, row, column);
	}

	/**
	 * own model that specific in each of column
	 */
	private class PersonModel extends DefaultTableModel {
		PersonModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Double.class;
				case 4:
					return Integer.class;
				case 5:
					return Integer.class;
				case 6:
					return Double.class;
				case 7:
					return Double.class;
				default:
					return String.class;
			}
		}

	}

	public void run(Point point) {
		setMinimumSize(new Dimension(860, 500));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
