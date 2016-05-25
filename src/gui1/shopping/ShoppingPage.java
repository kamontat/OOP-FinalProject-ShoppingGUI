package gui1.shopping;

import code.Interface.ButtonFactory;
import code.customer.Customer;
import gui1.main.MainPage;

import javax.swing.*;
import java.awt.*;

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
	private JLabel memberLabel;
	private JLabel customerLabel;

	private JTabbedPane tabbedPane;
	private JPanel earringPanel;
	private JPanel pendantPanel;
	private JPanel ringPanel;
	private JLabel totalNumLabel;

	private Customer shopper;

	public ShoppingPage() {
		super("Shopping Page");
		setContentPane(panel);

		shopper = MainPage.shopper;
		setCustomer();

		toMain(this, mainButton);

	}

	private void setCustomer() {
		customerLabel.setText(shopper.toString());
		memberLabel.setText(shopper.getMemberClass());
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(650, 470));
		setPreferredSize(new Dimension(800, 850));

		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JPanel getEarringPanel() {
		return earringPanel;
	}

	public JPanel getPendantPanel() {
		return pendantPanel;
	}

	public JPanel getRingPanel() {
		return ringPanel;
	}
}
