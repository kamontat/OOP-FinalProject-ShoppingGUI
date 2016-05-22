package gui1;

import javax.swing.*;
import java.awt.*;

/**
 * @author kamontat
 * @since 22/5/59 - 17:34
 */
public class ShoppingPage extends JFrame {
	private JPanel panel;
	private JTable table;
	private JButton mainButton;
	private JButton basketButton;
	private JButton paymentButton;
	private JPanel productPanel;
	private JLabel numProductLabel;
	private JLabel priceLabel;
	private JLabel memberLabel;
	private JLabel customerLabel;

	public ShoppingPage() {
		super("Shopping Page");
		setContentPane(panel);
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(0, 0));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		gui1.ShoppingPage page = new gui1.ShoppingPage();
		page.run(new Point(0, 0));
	}
}
