package gui;

import code.customer.Customer;
import code.product.ProductExt;
import code.store.OrderElement;
import code.store.Store;
import gui1.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ShoppingPage extends JFrame {
	// every J must have in this program
	private JButton buttonMainMenu, buttonbasket, buttonpayment;
	private JLabel textMemberClass, textCustomer;
	private static JLabel textProductCustomer, textPrice, textSelectProduct;
	// To import file image
	private ClassLoader loader = this.getClass().getClassLoader();
	// get store in MainMenu
	private Store store = MainPage.store;
	// get product and customer from store
	private ArrayList<ProductExt> productList = store.getProductList();
	private Customer shopper;
	// variable else
	private static int numProductCustomer = 0, priceProductCustomer = 0;

	private BasketPage basket = new BasketPage();
	static private boolean check = true;

	public ShoppingPage() {
		super("Shopping Page");
		getContentPane().setBackground(SystemColor.controlDkShadow);
		this.setBackground(new Color(216, 191, 216));

		shopper = MainPage.shopper;

		initComponent();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(50, 50, 950, 950);
		setVisible(true);
		setResizable(false);
	}

	public static JLabel getTextProductCustomer() {
		return textProductCustomer;
	}

	public static JLabel getTextPrice() {
		return textPrice;
	}

	public static JLabel getTextSelectProduct() {
		return textSelectProduct;
	}

	public static int getNumProductCustmer() {
		return numProductCustomer;
	}

	public static int getPriceProductCustmer() {
		return priceProductCustomer;
	}

	public static boolean isCheck() {
		return check;
	}

	public static void setCheck(boolean check) {
		ShoppingPage.check = check;
	}

	public static void setNumProductCustomer(int numProductCustomer) {
		ShoppingPage.numProductCustomer = numProductCustomer;
	}

	public static void setPriceProductCustomer(int priceProductCustomer) {
		ShoppingPage.priceProductCustomer = priceProductCustomer;
	}

	public static void setTextPrice(JLabel textPrice) {
		ShoppingPage.textPrice = textPrice;
	}

	public static void setTextSelectProduct(JLabel textSelectProduct) {
		ShoppingPage.textSelectProduct = textSelectProduct;
	}

	public static void setTextProductCustomer(JLabel textProductCustomer) {
		ShoppingPage.textProductCustomer = textProductCustomer;

	}

	public void initComponent() {
		getContentPane().setLayout(null);

		// container
		Container firstContainer = new Container();
		firstContainer.setBounds(0, 0, 950, 185);
		// null is mean Absolute Layout
		firstContainer.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 0, 585, 184);
		firstContainer.add(scrollPane);

		Container secondContainer = new Container();
		secondContainer.setBounds(0, 183, 950, 745);
		secondContainer.setLayout(new BorderLayout());

		Container customerInformationContainer = new Container();
		customerInformationContainer.setLayout(new FlowLayout());

		Container productInformationContainer = new Container();
		productInformationContainer.setLayout(new GridLayout(3, 5));

		Container buttonZoneContainer = new Container();
		buttonZoneContainer.setLayout(new GridLayout(1, 5));

		// assign MemberClass
		textMemberClass = new JLabel(shopper.getMemberClass());
		textMemberClass.setHorizontalAlignment(SwingConstants.CENTER);
		textMemberClass.setForeground(new Color(255, 250, 240));
		textMemberClass.setFont(new Font("Iowan Old Style", Font.PLAIN, 28));
		textMemberClass.setBounds(16, 121, 152, 64);
		firstContainer.add(textMemberClass);

		textSelectProduct = new JLabel("0");
		textSelectProduct.setHorizontalAlignment(SwingConstants.CENTER);
		textSelectProduct.setForeground(new Color(240, 248, 255));
		textSelectProduct.setFont(new Font("Heiti TC", Font.BOLD, 15));
		textSelectProduct.setBounds(781, 48, 165, 20);
		firstContainer.add(textSelectProduct);

		textPrice = new JLabel("0");
		textPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textPrice.setForeground(new Color(240, 248, 255));
		textPrice.setFont(new Font("Heiti TC", Font.BOLD, 15));
		textPrice.setBounds(781, 125, 165, 20);
		firstContainer.add(textPrice);

		// costomerInformation
		textCustomer = new JLabel(shopper.toString());
		textCustomer.setBackground(Color.WHITE);
		textCustomer.setForeground(Color.WHITE);
		textCustomer.setFont(new Font("Iowan Old Style", Font.PLAIN, 18));
		customerInformationContainer.add(textCustomer);
		// finish costomerInformation

		// create New Button
		// Ring
		JButton buttonBlueTopazesRing = new JButton(new ImageIcon(loader.getResource("images/Ring/BlueTopazesRing.jpg")));
		buttonBlueTopazesRing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>designed to be stacked or worn alone<br><br>Size: 6<br><br>Material 18k gold with a blue topaz, Carat weight 1.00<br><br>Price: 22,200 ฿</html>", "Blue Topazes Ring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 0, 22200);

			}
		});

		JButton buttonMidnightTitaniumRing = new JButton(new ImageIcon(loader.getResource("images/Ring/MidnightTitaniumRing.jpg")));
		buttonMidnightTitaniumRing.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>the classic cars and racing is highlighted<br><br>Size: 9<br><br>stainless steel and midnight titanium<br><br>Price: 13,800 ฿</html>", "Midnight Titanium Ring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 1, 13800);
			}
		});

		JButton buttonPinkDiamondsRing = new JButton(new ImageIcon(loader.getResource("images/Ring/PinkDiamondsRing.jpg")));
		buttonPinkDiamondsRing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>Pink diamonds lends radiance to a sparkling white diamond center stone<br><br>Size: 6<br><br>diamonds in platinum and 18k rose gold.<br>Pink diamonds, carat total weight 0.02<br>white diamonds, carat total weight 0.34 <br><br>Price: 202,000 ฿</html>", "Pink Diamonds Ring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 2, 202000);
			}
		});

		JButton buttonSilverBowRing = new JButton(new ImageIcon(loader.getResource("images/Ring/SilverBowRing.jpg")));
		buttonSilverBowRing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>Bow is a charming symbol of our most important ties<br><br>Size: 6<br><br>Sterling silver<br><br>Price: 6,600 ฿</html>", "Silver Bow Ring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 3, 6600);
			}
		});

		JButton buttonXDiamondsRing = new JButton(new ImageIcon(loader.getResource("images/Ring/XDiamondsRing.jpg")));
		buttonXDiamondsRing.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>rendition of an “X,” the popular symbol of love<br><br>Size: 6<br><br>18k gold with diamonds and pink sapphires in platinum<br>Round brilliant diamonds, carat total weight 0.59<br>round pink sapphires, carat total weight 0.75<br><br>Price: 253,000 ฿</html>", "X Diamonds Ring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 4, 253000);
			}
		});

		// Pendant
		JButton buttonMixedClusterPendant = new JButton(new ImageIcon(loader.getResource("images/Pendant/MixedClusterPendant.jpg")));
		buttonMixedClusterPendant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>The elegant simplicity of this pendant makes it as appropriate for day as it is for evening<br><br>Size: Medium<br><br>Platinum with mixed-cut diamonds<br>Marquise diamonds, carat total weight 0.27<br>Pear-shaped diamonds, carat total weight 0.20<br><br>Price: 155,000 ฿</html>", "Mixed Cluster Pendant", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 5, 155000);
			}
		});

		JButton buttonOliveLeafPendant = new JButton(new ImageIcon(loader.getResource("images/Pendant/OliveLeafPendant.jpg")));
		buttonOliveLeafPendant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>A beautiful tribute to the olive branch<br><br>sterling silver<br><br>Size: Small<br><br>Price: 13,000 ฿</html>", "Olive Leaf Pendant", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 6, 13000);
			}
		});

		JButton buttonPiercedPendant = new JButton(new ImageIcon(loader.getResource("images/Pendant/PiercedPendant.jpg")));
		buttonPiercedPendant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>this pendant accentuate the strong lines of timeless Roman numerals<br><br>Size: Small<br><br>18k gold with round brilliant diamonds<br><br>Price: 25,200 ฿</html>", "Pierced Pendant", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 7, 25200);
			}
		});

		JButton buttonSilverBowPendant = new JButton(new ImageIcon(loader.getResource("images/Pendant/SilverBowPendant.jpg")));
		buttonSilverBowPendant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>Fluid sterling silver accentuates the flowing design of this pendant<br><br>Size: Small<br><br>sterling silver<br><br>Price: 5,600 ฿</html>", "Silver Bow Pendant", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 8, 5600);
			}
		});

		JButton buttonWhiteGoldBowPendant = new JButton(new ImageIcon(loader.getResource("images/Pendant/WhiteGoldBowPendant.jpg")));
		buttonWhiteGoldBowPendant.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>This asymmetrical pendant is a fluid reminder of special moments<br><br>Size: Small<br><br>18k white gold with round brilliant diamonds Carat total weight 0.20<br><br>Price: 111,000 ฿</html>", "White Gold Bow Pendant", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 9, 111000);
			}
		});

		// Earring
		JButton buttonBlueTopazesOliveLeafEarring = new JButton(new ImageIcon(loader.getResource("images/Earring/BlueTopazesOliveLeafEarring.jpg")));
		buttonBlueTopazesOliveLeafEarring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>symbol of peace or victory<br><br>Size: Mini<br><br>18k gold with blue topazes. Carat total weight 3.00<br><br>Price: 24,600 ฿</html>", "Blue Topazes Olive Leaf Earring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 10, 24600);
			}
		});

		JButton buttonBowEarring = new JButton(new ImageIcon(loader.getResource("images/Earring/BowEarring.jpg")));
		buttonBowEarring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>this design captures the sweeping movement of a flowing ribbon<br><br>Size: Mini<br><br>18k rose gold<br><br>Price: 14,200 ฿</html>", "Bow Earring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 11, 14200);
			}
		});

		JButton buttonColorByTheYardEarring = new JButton(new ImageIcon(loader.getResource("images/Earring/ColorByTheYardEarring.jpg")));
		buttonColorByTheYardEarring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>the color in the yard<br><br>sterling silver with two round pink sapphires<br><br>Size: Mini<br><br>Price: 30,600 ฿</html>", "Color By The Yard Earring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 12, 30600);
			}
		});
		JButton buttonRivalsNightSkyEarring = new JButton(new ImageIcon(loader.getResource("images/Earring/RivalsNightSkyEarring.jpg")));
		buttonRivalsNightSkyEarring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>celebrates the blazing brilliance of diamonds<br><br>Size: Medium<br><br>Platinum with marquise diamonds, Carat total weight 1.62<br><br>Price: 504,000 ฿</html>", "Rivals Night Sky Earring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 13, 504000);
			}
		});

		JButton buttonSilverOliveLeafEarring = new JButton(new ImageIcon(loader.getResource("images/Earring/SilverOliveLeafEarring.jpg")));
		buttonSilverOliveLeafEarring.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = JOptionPane.showInputDialog(null, "<html>a symbol of peace and abundance<br><br>Size: Medium<br><br>sterling silver<br><br>Price: 5,600 ฿</html>", "Silver Olive Leaf Earring", JOptionPane.PLAIN_MESSAGE);
				updateData(text, 14, 5600);
			}
		});
		// finish Images

		// productInformation
		productInformationContainer.add(buttonBlueTopazesRing);
		productInformationContainer.add(buttonMidnightTitaniumRing);
		productInformationContainer.add(buttonPinkDiamondsRing);
		productInformationContainer.add(buttonSilverBowRing);
		productInformationContainer.add(buttonXDiamondsRing);

		productInformationContainer.add(buttonMixedClusterPendant);
		productInformationContainer.add(buttonOliveLeafPendant);
		productInformationContainer.add(buttonPiercedPendant);
		productInformationContainer.add(buttonSilverBowPendant);
		productInformationContainer.add(buttonWhiteGoldBowPendant);

		productInformationContainer.add(buttonBlueTopazesOliveLeafEarring);
		productInformationContainer.add(buttonBowEarring);
		productInformationContainer.add(buttonColorByTheYardEarring);
		productInformationContainer.add(buttonRivalsNightSkyEarring);
		productInformationContainer.add(buttonSilverOliveLeafEarring);
		// finish productInformation

		// buttonZone
		buttonMainMenu = new JButton("MAIN MENU");
		buttonMainMenu.setFont(new Font("Century Gothic", Font.PLAIN, 16));
		buttonMainMenu.setForeground(Color.DARK_GRAY);

		buttonbasket = new JButton("BASKET");
		buttonbasket.setForeground(Color.DARK_GRAY);
		buttonbasket.setFont(new Font("Century Gothic", Font.PLAIN, 16));

		buttonpayment = new JButton("PAYMENT");
		buttonpayment.setForeground(Color.DARK_GRAY);
		buttonpayment.setFont(new Font("Century Gothic", Font.PLAIN, 16));

		// make button can use
		backToMain(buttonMainMenu);
		ToBasket(buttonbasket);
		ToPayment(buttonpayment);

		buttonZoneContainer.add(buttonMainMenu);
		buttonZoneContainer.add(new JLabel(""));
		buttonZoneContainer.add(buttonbasket);
		buttonZoneContainer.add(new JLabel(""));
		buttonZoneContainer.add(buttonpayment);
		// finish buttonZone

		// add container to Big container second
		secondContainer.add(customerInformationContainer, BorderLayout.NORTH);
		secondContainer.add(productInformationContainer, BorderLayout.CENTER);
		secondContainer.add(buttonZoneContainer, BorderLayout.SOUTH);
		// finish add

		textProductCustomer = new JLabel(String.format("<html><pre>%-10s %-31s %-25s %-6s %-6s %-8s   %6s</pre></html>", "ProductID", "Name", "Material", "Size", "Weight", "Price", "Number"));
		textProductCustomer.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		textProductCustomer.setVerticalAlignment(SwingConstants.TOP);
		textProductCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		scrollPane.setViewportView(textProductCustomer);

		JLabel textNumProduct = new JLabel("NUMBER OF PRODUCT :");
		textNumProduct.setForeground(SystemColor.controlLtHighlight);
		textNumProduct.setFont(new Font("Heiti TC", Font.BOLD, 14));
		textNumProduct.setHorizontalAlignment(SwingConstants.CENTER);
		textNumProduct.setBounds(781, 23, 165, 20);
		firstContainer.add(textNumProduct);

		JLabel textPrice = new JLabel("TOTAL PRICE :");
		textPrice.setForeground(SystemColor.text);
		textPrice.setFont(new Font("Heiti TC", Font.BOLD, 14));
		textPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textPrice.setBounds(781, 100, 165, 20);
		firstContainer.add(textPrice);

		JLabel lblClass = new JLabel("- CLASS MEMBER -");
		lblClass.setHorizontalAlignment(SwingConstants.CENTER);
		lblClass.setFont(new Font("Heiti TC", Font.PLAIN, 19));
		lblClass.setForeground(Color.WHITE);
		lblClass.setBounds(6, 99, 179, 16);
		firstContainer.add(lblClass);

		// add container to this Frame
		getContentPane().add(firstContainer);
		getContentPane().add(secondContainer);
	}

	/**
	 * Open Main by use ActionListener (Only MainButton)
	 *
	 * @param button
	 */
	public void backToMain(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// return currNumStock
				for (int i = 0; i < shopper.getBasketList().size(); i++) {
					store.refundStock(shopper.getBasketList().get(i));
				}
				shopper.clearBasket();

				MainPage menu = new MainPage();
				menu.run(getLocation());
				dispose();

			}
		});
	}

	/**
	 * check that text is or not Integer
	 *
	 * @param text
	 * @return if text equals Integer will return int, otherwise return -1
	 */
	public int checkInteger(String text) {
		try {
			return Integer.parseInt(text);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * text in number that customer want to buy Check text is or not valid Check
	 * CurrNumStock in Stock Update Display with basket that customer buy Update
	 * Price and Shipping And Update every thing must update in Shopping
	 *
	 * @param text
	 * @param index
	 * @param price
	 */
	public void updateData(String text, int index, int price) {
		if (text != null) {
			// check if text isn't integer
			int num = checkInteger(text);
			// check is availaility
			int availStatus = store.checkAvailability(productList.get(index), num);
			String printBasket = String.format("<pre>%-10s %-31s %-25s %-6s %-6s %-8s   %6s</pre>", "ProductID", "Name", "Material", "Size", "Weight", "Price", "Number");
			if (num > 0) {
				if (availStatus == 1) {
					numProductCustomer += num;
					priceProductCustomer += num * price;

					OrderElement element = new OrderElement(store.getProductList().get(index), num);
					shopper.addToBasket(element);
					// the store updates stock
					store.updateStock(element);
					// update comboBox in basket
					basket.addItemInComboBox(element.getName());
				} else {
					// if the stock isn't enough, notify shopper
					JOptionPane.showMessageDialog(null, String.format("Sorry. We only have %d left. Please reorder.\n", store.getProductList().get(index).getCurrNumStock()), "Warning", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Sorry. Please, Enter Only Positive Number", "Error", JOptionPane.ERROR_MESSAGE);
			}
			// update display in basket
			basket.setTextproductCustomerInBasket();
			basket.updateElseInShopping();
			basket.updatePriceShipping();
			basket.updateFinalPrice();

			String tempPrice = String.format("%,d", priceProductCustomer);
			printBasket += shopper.getBasketString();
			updatePage(printBasket, Integer.toString(numProductCustomer), tempPrice);
		}
	}

	/**
	 * Update Shopping page by paramater
	 *
	 * @param updateProduct
	 * @param updateNumProduct
	 * @param updatePrice
	 */
	public void updatePage(String updateProduct, String updateNumProduct, String updatePrice) {
		textProductCustomer.setText("<html>" + updateProduct + "</html>");
		textSelectProduct.setText("<html>" + updateNumProduct + "</html>");
		textPrice.setText("<html>" + updatePrice + "</html>");
	}

	/**
	 * Open CustomerPage by use ActionListener (Only BasketButton)
	 *
	 * @param button
	 */
	public void ToBasket(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				basket = new BasketPage();
				basket.run();
			}
		});
	}

	/**
	 * Open PaymentPage by use ActionListener (Only PaymentButton)
	 *
	 * @param button
	 */
	public void ToPayment(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PaymentPage payment = new PaymentPage();
				payment.run();
				setVisible(false);
				basket.setVisible(false);
			}
		});
	}
}
