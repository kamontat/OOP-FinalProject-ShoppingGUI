package gui1.shopping;

import code.behavior.ButtonFactory;
import code.constant.ImageSize;
import code.constant.ProductType;
import code.customer.Customer;
import code.file.ImageFileFactory;
import gui1.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author kamontat
 * @since 22/5/59 - 17:34
 */
public class ShoppingPage extends JFrame implements ButtonFactory {
	private JPanel panel;
	private JTable table;

	private JButton mainButton;
	private JButton basketButton;
	private JButton paymentButton;

	private JLabel numProductLabel;
	private JLabel totalPriceLabel;
	private JLabel totalProductLabel;

	private JLabel memberLabel;
	private JLabel customerLabel;

	private JTabbedPane tabbedPane;

	private JPanel earringPanel;
	private JPanel pendantPanel;
	private JPanel ringPanel;

	private Customer shopper;

	public ShoppingPage() {
		super("Shopping Page");
		setContentPane(panel);

		shopper = MainPage.shopper;
		setCustomer();

		setProduct(earringPanel, ProductType.EARRING, 0);
		setProduct(pendantPanel, ProductType.PENDANT, 5);
		setProduct(ringPanel, ProductType.RING, 10);

		toMain(this, mainButton);
	}

	private void setCustomer() {
		customerLabel.setText(shopper.toString());
		memberLabel.setText(shopper.getMemberClass());
	}

	private void setProduct(JPanel panel, ProductType name, int startIndex) {
		ImageFileFactory factory = new ImageFileFactory("src/images");

		// set url from big picture
		factory.setName(name);
		factory.setSize(ImageSize.BIG);
		URL[] big = factory.getAllImageURL();
		factory.resetPath();

		// set url from small picture
		factory.setName(name);
		factory.setSize(ImageSize.SMALL);
		URL[] small = factory.getAllImageURL();

		if (big.length == small.length) {
			for (int i = 0; i < big.length; i++) {
				ProductPanel product = new ProductPanel(this, panel, MainPage.store.getProductList().get(i + startIndex));
				product.setInformation(small[i]);
				product.setPopupPic(big[i]);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Not enough Picture for product", "Error Images", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void addNumProduct(int numProduct) {
		int num = Integer.parseInt(numProductLabel.getText()) + numProduct;
		numProductLabel.setText(String.valueOf(num));
	}

	public void removeNumProduct(int numProduct) {
		int num = Integer.parseInt(numProductLabel.getText()) - numProduct;
		numProductLabel.setText(String.valueOf(num));
	}

	public void addTotalProduct(int numTotalProduct) {
		int num = Integer.parseInt(totalProductLabel.getText()) + numTotalProduct;
		totalProductLabel.setText(String.valueOf(num));
	}

	public void removeTotalProduct(int numTotalProduct) {
		int num = Integer.parseInt(totalProductLabel.getText()) - numTotalProduct;
		totalProductLabel.setText(String.valueOf(num));
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(650, 470));
		setSize(new Dimension(800, 875));

		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
