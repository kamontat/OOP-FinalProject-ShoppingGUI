package gui1.shopping;

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
	private JLabel numProductLabel;
	private JLabel totalPriceLabel;
	private JLabel memberLabel;
	private JLabel customerLabel;

	private JTabbedPane tabbedPane;
	private JPanel earringPanel;
	private JPanel pendantPanel;
	private JPanel ringPanel;
	private JLabel totalNumLabel;

	public ShoppingPage() {
		super("Shopping Page");
		setContentPane(panel);
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

	public static void main(String[] args) {
		ShoppingPage page = new ShoppingPage();
		page.run(new Point(0, 0));
	}
}
