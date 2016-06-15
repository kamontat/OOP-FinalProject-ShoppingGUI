package gui.history;

import code.customer.Customer;
import code.product.ProductExt;
import code.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class HistoryPage extends JDialog {
	private Dialog page = this;

	private JPanel contentPane;
	private JButton buttonCancel;
	private JLabel infoLabel;
	private JList<Object> list;

	/**
	 * can use only history of customer & product only!
	 *
	 * @param obj
	 * 		some object that want to show in this gui
	 */
	public HistoryPage(Object obj) {
		setContentPane(contentPane);
		setModal(true);

		setInformationLabel(obj.toString());

		if (obj instanceof Customer) setList((Customer) obj);
		else setList((ProductExt) obj);

		buttonCancel.addActionListener(e -> onCancel());

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

	private void setList(Customer customer) {
		list.setListData(customer.getHistoryListArray());

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					int index = list.getSelectedIndex();
					ExpendProduct somePage = new ExpendProduct(page, customer, index);
					somePage.run(getLocation());
				}
			}
		});
	}

	private void setList(ProductExt product) {
		Store store = Store.getInstance();
		ArrayList<Customer> customers = store.checkProductHistory(product);
		list.setListData(customers.toArray());

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {

					ExpendProduct somePage = new ExpendProduct(page, customers.get(list.getSelectedIndex()), -1);
					somePage.run(getLocation());
				}
			}
		});
	}

	private void onCancel() {
		dispose();
	}

	public void run(Point point) {
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}


}
