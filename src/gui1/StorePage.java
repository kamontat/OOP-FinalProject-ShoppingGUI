package gui1;

import code.store.Store;

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

	}

	private void settingTable() {
		table.setModel(new DefaultTableModel(MainPage.getProductList(), new String[]{"Name", "Weight", "Price", "In Stock", "Restock", "Material", "Size", "Buying Price"}));

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
					sorter.setRowFilter(RowFilter.regexFilter("^" + text + "$"));
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("^" + text + "$"));
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter("^" + text + "$"));
				}
			}
		});
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
