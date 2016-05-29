package gui1.shopping;

import code.behavior.ButtonFactory;
import code.constant.ImageSize;
import code.constant.ProductType;
import code.customer.Customer;
import code.file.ImageFileFactory;
import code.store.OrderElement;
import gui1.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.*;

/**
 * @author kamontat
 * @since 22/5/59 - 17:34
 */
public class ShoppingPage extends JFrame implements ButtonFactory, Observer {
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
	private ProductPanel[] products = new ProductPanel[MainPage.store.getProductList().size()];

	public ShoppingPage() {
		super("Shopping Page");
		setContentPane(panel);

		shopper = MainPage.shopper;
		setCustomer();

		setProduct(earringPanel, ProductType.EARRING, 0);
		setProduct(pendantPanel, ProductType.PENDANT, 5);
		setProduct(ringPanel, ProductType.RING, 10);

		// add this to observer
		Arrays.stream(products).forEach(productPanel -> productPanel.addObserver(this));

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
				products[i + startIndex] = new ProductPanel(this, panel, MainPage.store.getProductList().get(i + startIndex));
				products[i + startIndex].setInformation(small[i]);
				products[i + startIndex].setPopupPic(big[i]);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Not enough Picture for product", "Error Images", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * when number in positive this method will increase
	 * otherwise this method will decrease number Product
	 *
	 * @param num
	 * 		some number
	 */
	private void setNumProduct(int num) {
		int number = Integer.parseInt(numProductLabel.getText()) + num;
		this.numProductLabel.setText(String.valueOf(number));
	}

	/**
	 * when number in positive this method will increase
	 * otherwise this method will decrease total Product
	 *
	 * @param total
	 * 		some number
	 */
	public void setTotalProduct(int total) {
		int number = Integer.parseInt(totalProductLabel.getText()) + total;
		this.totalProductLabel.setText(String.valueOf(number));
	}

	/**
	 * when number in positive this method will increase
	 * otherwise this method will decrease total price
	 *
	 * @param price
	 * 		some number
	 */
	public void setTotalPrice(double price) {
		double number = Double.parseDouble(totalPriceLabel.getText()) + price;
		this.totalPriceLabel.setText(String.valueOf(number));
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(650, 470));
		setSize(new Dimension(800, 875));

		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) {

			if (arg.getClass() == String[].class) {
				String[] args = (String[]) arg;

				if (args[0].equals("numProduct")) {
					setNumProduct(Integer.parseInt(args[1]));
				} else if (args[0].equals("totalProduct")) {
					setTotalProduct(Integer.parseInt(args[1]));
					setTotalPrice(Double.parseDouble(args[2]));
				}
			} else if (arg.getClass() == OrderElement.class) {
				System.out.println("Order please.");
			}
		}
	}
}
