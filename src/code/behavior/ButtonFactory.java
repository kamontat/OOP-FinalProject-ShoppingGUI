package code.behavior;


import code.human.Customer;
import gui.customer.CustomerPage;
import gui.main.MainPage;
import gui.shopping.PaymentPage;
import gui.shopping.ShoppingPage;
import gui.store.LoginPage;
import gui.store.StorePage;

import javax.swing.*;

import static javax.swing.JOptionPane.YES_NO_CANCEL_OPTION;

/**
 * @author kamontat
 * @since 21/5/59 - 23:51
 */
public interface ButtonFactory {

	/**
	 * use in shopping page & payment only <br>
	 * to check before goto main page that want to save basket or not.
	 *
	 * @param page
	 * 		last page
	 * @param main
	 * 		button
	 * @param shopper
	 * 		basket of shopper
	 */
	default void toMain(JFrame page, JButton main, Customer shopper) {
		main.addActionListener(e -> {
			int status = JOptionPane.showConfirmDialog(null, "do you want to keep shopping cart?", "Message", YES_NO_CANCEL_OPTION);
			// 2 = cancel, 1 = no, 0 = yes
			if (status != 2) {
				if (status == 1) {
					shopper.clearBasket();
				}
				toMain(page, null);
			}
		});
	}

	default void toMain(JFrame frame, JButton main) {
		if (main == null) {
			MainPage page = new MainPage();
			page.run(frame.getLocation());
			frame.dispose();
		} else {
			main.addActionListener(e -> {
				MainPage page = new MainPage();
				page.run(frame.getLocation());
				frame.dispose();
			});
		}
	}

	default void toShopping(JFrame frame, JButton shopping) {
		if (shopping == null) {
			ShoppingPage page = new ShoppingPage();
			page.run(frame.getLocation());
			frame.dispose();
		} else {
			shopping.addActionListener(e -> {
				ShoppingPage page = new ShoppingPage();
				page.run(frame.getLocation());
				frame.dispose();
			});
		}
	}

	default void toPayment(JFrame frame, JButton payment) {
		if (payment == null) {
			PaymentPage page = new PaymentPage();
			page.run(frame.getLocation());
			frame.dispose();
		} else {
			payment.addActionListener(e -> {
				PaymentPage page = new PaymentPage();
				page.run(frame.getLocation());
				frame.dispose();
			});
		}
	}

	default void toCustomer(JFrame frame, JButton customer) {
		if (customer == null) {
			CustomerPage page = new CustomerPage();
			page.run(frame.getLocation());
			frame.dispose();
		} else {
			customer.addActionListener(e -> {
				CustomerPage page = new CustomerPage();
				page.run(frame.getLocation());
				frame.dispose();
			});
		}
	}

	default void toStore(JFrame frame, JButton store) {
		if (store == null) {
			StorePage page = new StorePage();
			page.run(frame.getLocation());
			frame.dispose();
		} else {
			store.addActionListener(e -> {
				StorePage page = new StorePage();
				page.run(frame.getLocation());
				frame.dispose();
			});
		}
	}

	default void toLogin(JFrame frame, JButton login) {
		if (login == null) {
			LoginPage page = new LoginPage(frame);
			page.run(frame.getLocation());
		} else {
			login.addActionListener(e -> {
				LoginPage page = new LoginPage(frame);
				page.run(frame.getLocation());
			});
		}
	}
	
	default void toExit(JButton exit) {
		exit.addActionListener(e -> {
			System.exit(0);
		});
	}
}
