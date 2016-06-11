package code.TableModel;

import javax.swing.table.DefaultTableModel;

/**
 * @author kamontat
 * @since 26/5/59 - 01:01
 */
public class ProductModel extends DefaultTableModel{

	/**
	 * own model that specific in each of column
	 */
	public ProductModel(Object[][] data, Object[] columnNames) {
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
				return String.class;
			case 5:
				return Double.class;
			case 6:
				return Integer.class;
			case 7:
				return Integer.class;
			case 8:
				return Double.class;
			case 9:
				return Double.class;
			default:
				return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
