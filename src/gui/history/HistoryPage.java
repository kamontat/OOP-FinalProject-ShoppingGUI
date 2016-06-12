package gui.history;

import code.customer.Customer;
import code.product.ProductExt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HistoryPage extends JDialog {
	private JPanel contentPane;
	private JButton buttonCancel;
	private JLabel infoLabel;
	private JList<Object> list;

	/**
	 * can use only history of customer & product only!
	 *
	 * @param text
	 */
	public HistoryPage(Object obj, String text) {
		setContentPane(contentPane);
		setModal(true);

		setInformationLabel(text);
		setList(obj);

		buttonCancel.addActionListener(e -> onCancel());

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// call onCancel() on ENTER
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void setInformationLabel(String text) {
		infoLabel.setText(text);
	}

	private void setList(Object obj) {
		Object[] temp = null;
		if (obj instanceof Customer) {
			Customer shopper = (Customer) obj;
			temp = shopper.getHistoryListArray();
		} else {
			ProductExt product = (ProductExt) obj;
			temp = product.getProductInfo(10, true);
		}
		list.setListData(temp);

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					// TODO: 12/6/59 expend all product that user have buy before
				}
			}
		});
	}

	private void onCancel() {
		dispose();
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(0, 0));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
