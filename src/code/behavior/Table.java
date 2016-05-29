package code.behavior;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
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

	default boolean checkValid(int row) {
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Please, Click to choose something", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	default void resetSelection(JTable table) {
		table.clearSelection();
	}
}
