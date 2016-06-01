package gui1.shopping;

import code.behavior.ButtonFactory;
import code.behavior.DefaultModel;
import code.constant.ImageSize;
import code.constant.ProductType;
import code.customer.Customer;
import code.file.ImageFileFactory;
import code.product.ProductExt;
import code.store.Store;
import gui1.main.MainPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;

/**
 * @author kamontat
 * @since 22/5/59 - 17:34
 */
public class ShoppingPage extends JFrame implements ButtonFactory, Observer {
	private Store store = Store.getInstance();
	private Customer shopper;
	private ProductPanel[] products = new ProductPanel[store.getProductList().size()];

	private JPanel panel;
	private JTable table;

	private JButton mainButton;
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

	public ShoppingPage() {
		super("Shopping Page");
		setContentPane(panel);

		shopper = MainPage.shopper;
		setCustomer();

		setProduct(earringPanel, ProductType.EARRING, 0);
		setProduct(pendantPanel, ProductType.PENDANT, 5);
		setProduct(ringPanel, ProductType.RING, 10);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				System.out.println(getSize());
			}
		});

		// add this to observer
		Arrays.stream(products).forEach(productPanel -> productPanel.addObserver(this));

		toMain(this, mainButton);
		toPayment(this, paymentButton);
	}

	private void updateTable() {
		DefaultTableModel model = new DefaultModel(shopper.getBasketToArray(), new String[]{"ID", "Name", "Type", "Material", "Size", "Weight", "Price", "Num"}, false);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Disable dragging
		table.getTableHeader().setReorderingAllowed(false);

		table.setModel(model);

		table.getColumnModel().getColumn(0).setMinWidth(50); // id
		table.getColumnModel().getColumn(1).setMinWidth(190); // name
		table.getColumnModel().getColumn(2).setMinWidth(75); // type
		table.getColumnModel().getColumn(3).setMinWidth(180); // material
		table.getColumnModel().getColumn(4).setMinWidth(65); // size
		table.getColumnModel().getColumn(5).setMinWidth(65); // weight
		table.getColumnModel().getColumn(6).setMinWidth(80); // price
		table.getColumnModel().getColumn(7).setMinWidth(55); // number

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					ProductExt product = getProductAt(row);
					if (product.getType().equals(ProductType.EARRING)) {
						tabbedPane.setSelectedIndex(0);
					} else if (product.getType().equals(ProductType.PENDANT)) {
						tabbedPane.setSelectedIndex(1);
					} else if (product.getType().equals(ProductType.RING)) {
						tabbedPane.setSelectedIndex(2);
					}
				}
			}
		});
	}

	private ProductExt getProductAt(int row) {
		String productID = String.valueOf(table.getValueAt(row, 0));
		return store.searchIDProduct(productID);
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
				products[i + startIndex] = new ProductPanel(this, panel, store.getProductList().get(i + startIndex));
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
		setMinimumSize(new Dimension(1062, 927));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);

		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	@Override
	public void update(Observable o, Object arg) {
		ProductPanel product = (ProductPanel) o;
		if (arg != null) {

			if (arg.getClass() == String[].class) {
				String[] args = (String[]) arg;

				if (args[0].equals("numProduct")) {
					setNumProduct(Integer.parseInt(args[1]));
				} else if (args[0].equals("totalProduct")) {
					setTotalProduct(Integer.parseInt(args[1]));
					setTotalPrice(Double.parseDouble(args[2]));

					shopper.removeFromBasket(product.getOrder());
					shopper.addToBasket(product.getOrder());

					updateTable();
				}
			}
		}
	}
}
