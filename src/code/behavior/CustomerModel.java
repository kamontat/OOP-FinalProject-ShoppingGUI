package code.behavior;

import javax.swing.table.DefaultTableModel;

/**
 * @author kamontat
 * @since 26/5/59 - 01:00
 */
public class CustomerModel extends DefaultTableModel implements Table {

	/**
	 * own model that specific in each of column
	 */
	public CustomerModel(Object[][] data, Object[] columnNames) {
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
				return String.class;
			case 4:
				return Integer.class;
			case 5:
				return String.class;
			default:
				return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
