package gui.shopping;

import code.TableModel.DefaultModel;
import code.behavior.ButtonFactory;
import code.behavior.Table;
import code.constant.ImageFolder;
import code.constant.ProductSize;
import code.constant.ProductType;
import code.file.FileFactory;
import code.human.Customer;
import code.product.ProductExt;
import code.store.Store;
import gui.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;

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
		
		// update this to observer
		Arrays.stream(products).forEach(productPanel -> productPanel.addObserver(this));
		
		toMain(this, mainButton, shopper);
		toPayment(this, paymentButton);
		
		updateTable();
	}
	
	private void updateTable() {
		DefaultModel model = new DefaultModel(shopper.getBasketToArray(), new String[]{"ID", "Name", "Type", "Material", "Size", "Weight", "Price", "Num"}, false);
		table.setModel(model);
		
		settingTable(table, null);
		
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
	
	private void setProduct(JPanel panel, ProductType type, int startIndex) {
		FileFactory factory = FileFactory.getInstance();
		
		// set url from big picture
		File[] bigPics = factory.getAllImageURL(ImageFolder.PRODUCT, type, ProductSize.BIG);
		
		// set url from small picture
		File[] smallPics = factory.getAllImageURL(ImageFolder.PRODUCT, type, ProductSize.SMALL);
		
		if (bigPics.length == smallPics.length) {
			for (int i = 0; i < bigPics.length; i++) {
				products[i + startIndex] = new ProductPanel(this, panel, store.getProductList().get(i + startIndex));
				products[i + startIndex].setInformation(smallPics[i]);
				products[i + startIndex].setPopupPic(bigPics[i]);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Not enough Picture for product", "Error Images", JOptionPane.ERROR_MESSAGE);
		}
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
		pack();
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
