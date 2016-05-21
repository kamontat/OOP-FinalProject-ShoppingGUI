package gui1;

import code.store.Store;
import code.Interface.ButtonAction;
import code.Interface.Table;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

/**
 * @author kamontat
 * @since 21/5/59 - 22:53
 */
public class CustomerPage extends JFrame implements Table, ButtonAction {
	private Store store = MainPage.store;

	private JPanel panel;
	private JTextField searchField;
	private JButton addButton;
	private JTable table;
	private JButton mainButton;
	private JButton selectButton;
	private JButton removeButton;
	private JButton historyButton;

	public CustomerPage() {
		setContentPane(panel);

		settingTable();

		toMain(this, mainButton);
	}

	private void settingTable() {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable dragging
		table.getTableHeader().setReorderingAllowed(false);

		table.setModel(new StorePage.PersonModel(MainPage.getCustomerList(), new String[]{"ID", "Name", "LastName", "Gender", "Age", "Class"}));

		table.getColumnModel().getColumn(0).setMinWidth(50); // id
		table.getColumnModel().getColumn(1).setMinWidth(100); // name
		table.getColumnModel().getColumn(2).setMinWidth(120); // last name
		table.getColumnModel().getColumn(3).setMinWidth(50); // gender
		table.getColumnModel().getColumn(4).setMinWidth(50); // age
		table.getColumnModel().getColumn(5).setMinWidth(75); // class

		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(rowSorter);
		searching(searchField, rowSorter);
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(500, 530));
		pack();
		System.out.println(getSize());
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
