package gui1;

import code.Interface.ButtonAction;
import code.product.ProductExt;
import code.store.Store;
import gui.HistoryOfStorePage;
import code.Interface.Table;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * @author kamontat
 * @since 21/5/59 - 12:26
 */
public class StorePage extends JFrame implements Table, ButtonAction {
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
		// assign button

		toMain(this, mainButton);
		restock();
		history();

		updateLabel(store.getRevenue(), store.getExpense());
	}

	private void settingTable() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable dragging
		table.getTableHeader().setReorderingAllowed(false);

		table.setModel(new PersonModel(MainPage.getProductList(), new String[]{"ID", "Name", "Material", "Size", "Weight", "In Stock", "Restock", "Price", "Buying Price"}));

		table.getColumnModel().getColumn(0).setMinWidth(50); // id
		table.getColumnModel().getColumn(1).setMinWidth(240); // name
		table.getColumnModel().getColumn(2).setMinWidth(180); // material
		table.getColumnModel().getColumn(3).setMinWidth(65); // size
		table.getColumnModel().getColumn(4).setMinWidth(65); // weight
		table.getColumnModel().getColumn(5).setMinWidth(75); // stock
		table.getColumnModel().getColumn(6).setMinWidth(65); // restock
		table.getColumnModel().getColumn(7).setMinWidth(80); // price
		table.getColumnModel().getColumn(8).setMinWidth(85); // buying price

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(searchField, rowSorter);
	}

	private void restock() {
		restockButton.addActionListener(e -> {
			int row = table.getSelectedRow();

			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Please, choose some product", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				ProductExt product = getProductAt(row);

				if (product.isRestock()) {
					store.addExpense(product.restockProductExt());
					store.setRestockProduct(false);
					updateLabel(store.getRevenue(), store.getExpense());
					updateTable(product.getCurrNumStock(), row, 5);
				} else {
					int choose = JOptionPane.showConfirmDialog(null, "This product still have in stock", "Do you sure?", JOptionPane.YES_NO_OPTION);
					if (choose == 0) {
						store.addExpense(product.restockProductExt());
						store.setRestockProduct(false);
						updateLabel(store.getRevenue(), store.getExpense());
						updateTable(product.getCurrNumStock(), row, 5);
					}
				}
			}
		});
	}

	private void history() {
		historyButton.addActionListener(e -> {

			int row = table.getSelectedRow();
			HistoryOfStorePage page = new HistoryOfStorePage(getProductAt(row));
			page.run();
		});
	}

	private ProductExt getProductAt(int row) {
		String productName = String.valueOf(table.getValueAt(row, 1));
		return store.searchProduct(productName);
	}

	private void updateLabel(double reven, double expen) {
		revenueLabel.setText("Revenue: " + reven);
		expenseLabel.setText("Expense: " + expen);
		profitLabel.setText("Profit: " + (reven - expen));
	}

	private void updateTable(Object newValue, int row, int column) {
		table.setValueAt(newValue, row, column);
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(925, 500));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
