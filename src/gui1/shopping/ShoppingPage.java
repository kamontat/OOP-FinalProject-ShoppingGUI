package gui1.shopping;

import code.behavior.ButtonFactory;
import code.TableModel.DefaultModel;
import code.behavior.Table;
import code.constant.ImageSize;
import code.constant.ProductType;
import code.customer.Customer;
import code.file.ImageFileFactory;
import code.product.ProductExt;
import code.store.Store;
import gui1.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;

import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;

/**
 * @author kamontat
 * @since 22/5/59 - 17:34
 */
public class ShoppingPage extends JFrame implements ButtonFactory, Observer, Table {
	private JFrame page = this;
	private Store store = Store.getInstance();
	private Customer shopper = MainPage.shopper;

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

		setCustomer();

		setProduct(earringPanel, ProductType.EARRING, 0);
		setProduct(pendantPanel, ProductType.PENDANT, 5);
		setProduct(ringPanel, ProductType.RING, 10);

		// add this to observer
		Arrays.stream(products).forEach(productPanel -> productPanel.addObserver(this));

		toMain();
		toPayment(this, paymentButton);

		updateTable();
	}

	private void updateTable() {
		DefaultModel model = new DefaultModel(shopper.getBasketToArray(), new String[]{"ID", "Name", "Type", "Material", "Size", "Weight", "Price", "Num"}, false);
		table.setModel(model);

		settingTable(table, new int[]{75, 75, 75, 75, 75, 75, 75, 75});

		table.addMouseListener(new MouseAdapter() {
			private boolean mousePressed = false;

			// when user double click in the table
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					int row = table.getSelectedRow();
					directToProduct(row);
				}
			}

			// for long click
			@Override
			public void mousePressed(MouseEvent e) {
				new Thread(() -> {
					mousePressed = true;
					while (mousePressed) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						if (mousePressed) {
							int row = table.getSelectedRow();
							ProductExt product = getProductAt(row);

							for (ProductPanel productPanel : products) {
								if (productPanel.equals(product)) {
									productPanel.popup(page);
								}
							}
						}
					}
				}).start();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				mousePressed = false;
				for (ProductPanel pa : products) {
					pa.cancel();
				}
			}
		});
	}

	private ProductExt getProductAt(int row) {
		String productID = String.valueOf(table.getValueAt(row, 0));
		return store.searchProductID(productID);
	}

	private void setCustomer() {
		customerLabel.setText(shopper.toString());
		memberLabel.setText(shopper.getMemberClass().getName());
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

	private void toMain() {
		mainButton.addActionListener(e -> {
			int status = JOptionPane.showConfirmDialog(null, "do you want to keep shopping cart?", "Message", YES_NO_CANCEL_OPTION);
			// 2 = cancel, 1 = no, 0 = yes
			if (status != 2) {
				if (status == 1) {
					shopper.clearBasket();
				}
				MainPage page = new MainPage();
				page.run(getLocation());
				dispose();
			}
		});
	}

	public void directToProduct(int tableRow) {
		ProductExt product = getProductAt(tableRow);
		if (product.getType().equals(ProductType.EARRING)) {
			tabbedPane.setSelectedIndex(0);
		} else if (product.getType().equals(ProductType.PENDANT)) {
			tabbedPane.setSelectedIndex(1);
		} else if (product.getType().equals(ProductType.RING)) {
			tabbedPane.setSelectedIndex(2);
		}
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
		shopper.removeFromBasket(product.getOrder());
		shopper.addToBasket(product.getOrder());

		this.numProductLabel.setText(String.valueOf(shopper.getNumProduct()));
		this.totalProductLabel.setText(String.valueOf(shopper.getTotalProduct()));
		this.totalPriceLabel.setText(String.valueOf(shopper.getPrice()));

		updateTable();
	}

	private void createUIComponents() {
		table = fitSize(table);
	}
}
