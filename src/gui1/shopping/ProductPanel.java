package gui1.shopping;

import code.product.Product;
import code.product.ProductExt;
import code.store.OrderElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;

/**
 * @author kamontat
 * @since 24/5/59 - 00:27
 */
public class ProductPanel extends Observable {
	private OrderElement order;
	private ProductExt product;

	private int num;

	private JCheckBox buyCheckBox;
	private JSpinner spinner;
	private JLabel picLabel;
	private JPanel panel;
	private JLabel priceLabel;
	private JLabel stockLabel;
	private JLabel info2Label;
	private JLabel info1Label;

	private JDialog dialog = new JDialog();

	/**
	 * Add product into <code>"panel"</code> in the <code>"page"</code>
	 * <p>
	 * after call this method you have to call <br>
	 * 1. setInformation <- to set all important information <br>
	 * 2. setPopupPic <- when user click in small picture, program will popup this big one
	 *
	 * @param page
	 * 		Add to this page
	 * @param panel
	 * 		Add to this panel
	 */
	public ProductPanel(ShoppingPage page, JPanel panel, ProductExt product) {

		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, product.getCurrNumStock(), 1);
		spinner.setModel(model);

		this.product = product;
		order = new OrderElement(product, 0);

		panel.add(this.panel);
		page.pack();

		// change color of stock if less than 1
		if (product.getCurrNumStock() <= 1) {
			stockLabel.setForeground(new Color(255, 0, 0));
		}

		dialog.setUndecorated(true);
		dialog.setAlwaysOnTop(true);

		// popup big image when click in label
		picLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);

				popup(page);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);

				cancel();
			}
		});

		buyCheckBox.addItemListener(e -> {
			setChanged();
			notifyObservers();

			if (buyCheckBox.isSelected()) {
				if ((int) model.getNumber() == 0) spinner.setValue(1);
				spinner.setEnabled(true);
			} else {
				spinner.setValue(0);
				spinner.setEnabled(false);
			}
		});

		spinner.addChangeListener(e -> {
			num = (int) model.getNumber();

			setChanged();
			notifyObservers();

			if (num > 0) {
				buyCheckBox.setSelected(true);
			} else {
				buyCheckBox.setSelected(false);
			}
		});
	}

	public void popup(JFrame page) {
		dialog.setLocation(new Point((page.getX() + (page.getSize().width / 2)) - (dialog.getSize().width / 2), page.getY()));
		dialog.setVisible(true);
	}

	public void cancel() {
		dialog.setVisible(false);
	}

	public void setInformation(URL url) {
		addIcon(picLabel, url);

		// set product
		info1Label.setText(product.toStringInformation1());
		info2Label.setText(product.toStringInformation2());
		priceLabel.setText(product.getPrice() + " ฿");
		stockLabel.setText("In Stock: " + product.getCurrNumStock());
	}

	public void setPopupPic(URL url) {
		JLabel label = new JLabel();

		addIcon(label, url);

		dialog.add(label);
		dialog.pack();
	}

	public OrderElement getOrder() {
		// make number of product present
		order.setNum(num);
		// return present OrderElement
		return order;
	}

	public double getPrice(int number) {
		return product.getPrice() * number;
	}

	public boolean equals(Product product) {
		return product.getProductID().equals(this.product.getProductID());
	}

	private void addIcon(JLabel label, URL url) {
		try {
			if (url == null) throw new NullPointerException();
			label.setIcon(new ImageIcon(url));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
}
