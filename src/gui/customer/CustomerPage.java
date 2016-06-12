package gui.customer;

import code.TableModel.CustomerModel;
import code.behavior.ButtonFactory;
import code.behavior.Table;
import code.customer.Customer;
import code.store.Store;
import gui.history.HistoryPage;
import gui.main.MainPage;
import gui.shopping.ShoppingPage;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * @author kamontat
 * @since 21/5/59 - 22:53
 */
public class CustomerPage extends JFrame implements Table, ButtonFactory {
	private Store store = Store.getInstance();

	private JPanel panel;
	private JTextField searchField;
	private JButton addButton;
	private JTable table;
	private JButton mainButton;
	private JButton selectButton;
	private JButton removeButton;
	private JButton historyButton;
	private JButton guestButton;

	private CustomerModel model;

	public CustomerPage() {
		super("Customer Page");
		setContentPane(panel);
		model = new CustomerModel(store.getAllCustomer(), new String[]{"ID", "Name", "LastName", "Gender", "Age", "Class"});

		settingTable();

		add();

		selectButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (checkValid(row)) {
				MainPage.shopper = getCustomerAt(row);
				openShoppingPage();
			}
		});

		guestButton.addActionListener(e -> {
			MainPage.shopper = store.getGuest();
			openShoppingPage();
		});

		history();
		remove();

		toMain(this, mainButton);
	}

	private void settingTable() {
		table.setModel(model);
		settingTable(table, new int[]{50, 100, 120, 50, 50, 75});

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(searchField, rowSorter);
	}

	private void add() {
		addButton.addActionListener(e -> {
			AdderCustomerPage adderPage = new AdderCustomerPage();
			adderPage.run(getLocation());
			if (adderPage.getNewCustomer() != null) {
				model.addRow(adderPage.getNewCustomer().getCustomerInfo(6));
			}
			resetSelection(table);
		});
	}

	private void openShoppingPage() {
		ShoppingPage shopping = new ShoppingPage();
		shopping.run(getLocation());
		dispose();
		resetSelection(table);
	}

	private void remove() {
		removeButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (checkValid(row)) {
				int customerIndex = store.removeCustomer(getCustomerAt(row), false);
				model.removeRow(customerIndex);
				MainPage.reWriteCustomer();
			}
			resetSelection(table);
		});
	}

	private void history() {
		historyButton.addActionListener(e -> {
			int row = table.getSelectedRow();
			HistoryPage history = new HistoryPage(getCustomerAt(table.getSelectedRow()), getCustomerAt(table.getSelectedRow()).toString());
			history.run(getLocation());
		});
	}

	private Customer getCustomerAt(int row) {
		if (row != -1) {
			String id = String.valueOf(table.getValueAt(row, 0));
			return store.searchCustomerID(id);
		}
		return store.getGuest();
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(650, 530));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	private void createUIComponents() {
		// make table fit size by contact
		table = fitSize(table);
	}
}
