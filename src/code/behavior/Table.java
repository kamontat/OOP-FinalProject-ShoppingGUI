package code.behavior;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author kamontat
 * @since 21/5/59 - 23:27
 */
public interface Table {

	/**
	 * assign searching text in table by use JtextField
	 *
	 * @param input
	 * 		input string to check in table
	 * @param sorter
	 * 		Table model
	 */
	default void searching(JTextField input, TableRowSorter<TableModel> sorter) {
		input.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				String text = input.getText();
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
				String text = input.getText();
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
				String text = input.getText();
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

	/**
	 * check that row is -1 or not, -1 is mean no found
	 *
	 * @param row
	 * 		some row
	 * @return yes or no
	 */
	default boolean checkValid(int row) {
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Please, Click to choose something", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * clear all selected in `table`
	 *
	 * @param table
	 * 		some JTable
	 */
	default void resetSelection(JTable table) {
		table.clearSelection();
	}

	/**
	 * fit size in JTable, BUT it will create new JTable
	 *
	 * @param table
	 * 		some JTable
	 * @return that table
	 */
	default JTable fitSize(JTable table) {
		// make table fit size by contact
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				System.out.println(tableColumn.getWidth());
				return component;
			}
		};
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		return table;
	}


	/**
	 * make `table` cannot drag and make in can single selection <br>
	 * and fixed min size by `sizes`
	 *
	 * @param table
	 * 		JTable
	 * @param sizes
	 * 		array of size, if don't want, make it to null
	 */
	default void settingTable(JTable table, int[] sizes) {
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable dragging
		table.getTableHeader().setReorderingAllowed(false);

		int i = 0;

		if (sizes != null) {
			for (int size : sizes) {
				table.getColumnModel().getColumn(i++).setMinWidth(size);
			}
		}
	}
}
