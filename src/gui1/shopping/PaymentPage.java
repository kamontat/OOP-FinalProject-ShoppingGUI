package gui1.shopping;

import code.TableModel.DefaultModel;
import code.behavior.ButtonFactory;
import code.behavior.Table;
import code.constant.Shipping;
import code.customer.Customer;
import code.store.Store;
import gui1.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author kamontat
 * @since 1/6/59 - 16:36
 */
public class PaymentPage extends JFrame implements ButtonFactory, Table {
	private Store store = Store.getInstance();
	private Customer shopper = MainPage.shopper;
	private Shipping shipping = Shipping.NONE;

	private JPanel panel;
	private JTable table;
	private JButton buyButton;
	private JButton mainButton;
	private JButton shoppingButton;
	private JComboBox comboBox;
	private JTextField priceLabel;
	private JTextField weightLabel;
	private JLabel discountLabel;
	private JLabel memberLabel;
	private JLabel customerLabel;
	private JLabel finalPriceLabel;
	private JTextField shippingLabel;

	public PaymentPage() {
		super("Payment Page");
		setContentPane(panel);

		toMain(this, mainButton);
		toShopping(this, shoppingButton);

		setCustomer();
		setComboBox();
		setLabel();

		updateTable();
	}

	private void updateTable() {
		DefaultModel model = new DefaultModel(shopper.getBasketToArray(), new String[]{"ID", "Name", "Type", "Material", "Size", "Weight", "Price", "Num"}, false);
		table.setModel(model);
		settingTable(table, new int[]{75, 75, 75, 75, 75, 75, 75, 75});

		table.addMouseListener(new MouseAdapter() {
			// when user double click in the table
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					ShoppingPage page = new ShoppingPage();
					page.directToProduct(table.getSelectedRow());
					page.run(getLocation());
					dispose();
				}
			}
		});
	}

	private void setCustomer() {
		customerLabel.setText(shopper.toString());
		memberLabel.setText(shopper.getMemberClass().getName());
	}

	private void setComboBox() {
		shippingLabel.setText(shipping.toString(shopper.getWeight()));
		comboBox.addItemListener(e -> {
			// change shipping
			shipping = Shipping.check(String.valueOf(e.getItem()));
			setLabel();
		});
	}

	public void setLabel() {
		weightLabel.setText(String.format("%.2f g", shopper.getWeight()));
		priceLabel.setText(String.valueOf(shopper.getPrice()));
		finalPriceLabel.setText(String.valueOf(shopper.getTotalPrice() + shipping.getPrice(shopper.getWeight())));
		discountLabel.setText(String.valueOf(shopper.getDiscount()));
		shippingLabel.setText(shipping.toString(shopper.getWeight()));
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(780, 630));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		PaymentPage page = new PaymentPage();
		page.run(new Point(0, 0));
	}

	private void createUIComponents() {
		// make table fit size by contact
		table = fitSize(table);
	}
}
