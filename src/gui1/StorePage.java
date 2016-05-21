package gui1;

import code.product.ProductExt;
import code.store.Store;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		restock();

		updateLabel(store.getRevenue(), store.getExpense());
	}

	private void settingTable() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new PersonModel(MainPage.getProductList(), new String[]{"Name", "Weight", "Price", "In Stock", "Restock", "Material", "Size", "Buying Price"}));

		table.getColumnModel().getColumn(0).setMinWidth(190);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(4).setMinWidth(50);
		table.getColumnModel().getColumn(5).setMinWidth(155);
		table.getColumnModel().getColumn(6).setMinWidth(65);
		table.getColumnModel().getColumn(7).setMinWidth(85);

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
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}
		});
	}

	private void restock() {
		restockButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Please, choose some product", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					ProductExt product = store.getProductList().get(row);

					if (product.isRestock()) {
						store.addExpense(product.restockProductExt());
						store.setRestockProduct(false);
						updateLabel(store.getRevenue(), store.getExpense());
						updateTable(product.getCurrNumStock(), row, 3);
					} else {
						int choose = JOptionPane.showConfirmDialog(null, "This product still have in stock", "Do you sure?", JOptionPane.YES_NO_OPTION);
						if (choose == 0) {
							store.addExpense(store.getProductList().get(row).restockProductExt());
							store.setRestockProduct(false);
							updateLabel(store.getRevenue(), store.getExpense());
							updateTable(product.getCurrNumStock(), row, 3);
						}
					}
				}
			}
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
					return Double.class;
				case 2:
					return Double.class;
				case 3:
					return Integer.class;
				case 4:
					return Integer.class;
				case 5:
					return String.class;
				case 6:
					return String.class;
				case 7:
					return Double.class;
				default:
					return String.class;
			}
		}
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(800, 500));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		StorePage page = new StorePage();
		page.run(new Point(0, 0));
	}
}
