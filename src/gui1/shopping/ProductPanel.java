package gui1.shopping;

import code.product.ProductExt;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

/**
 * @author kamontat
 * @since 24/5/59 - 00:27
 */
public class ProductPanel extends JComponent {

	private JCheckBox CheckBox;
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
	public ProductPanel(JFrame page, JPanel panel) {
		panel.add(this.panel);
		page.pack();

		dialog.setUndecorated(true);
		dialog.setAlwaysOnTop(true);

		// popup big image when click in label
		picLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);

				dialog.setLocation(e.getLocationOnScreen());
				dialog.setVisible(true);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				super.mouseReleased(e);

				dialog.setVisible(false);
			}
		});
	}

	public void setInformation(String path, ProductExt product) {
		URL url = this.getClass().getClassLoader().getResource(path);
		addIcon(picLabel, url);

		// set product
		info1Label.setText(product.toStringInformation1());
		info2Label.setText(product.toStringInformation2());
		priceLabel.setText(product.getPrice() + " ฿");
		stockLabel.setText("In Stock: " + product.getCurrNumStock());
	}

	public void setInformation(URL url, ProductExt product) {
		addIcon(picLabel, url);

		// set product
		info1Label.setText(product.toStringInformation1());
		info2Label.setText(product.toStringInformation2());
		priceLabel.setText(product.getPrice() + " ฿");
		stockLabel.setText("In Stock: " + product.getCurrNumStock());
	}

	public void setPopupPic(String path) {
		JLabel label = new JLabel();

		URL url = this.getClass().getClassLoader().getResource(path);
		addIcon(label, url);

		dialog.add(label);
		dialog.pack();
	}

	public void setPopupPic(URL url) {
		JLabel label = new JLabel();

		addIcon(label, url);

		dialog.add(label);
		dialog.pack();
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
