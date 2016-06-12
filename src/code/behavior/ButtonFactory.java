package code.behavior;


import gui.customer.CustomerPage;
import gui.main.MainPage;
import gui.shopping.PaymentPage;
import gui.shopping.ShoppingPage;
import gui.store.LoginPage;
import gui.store.StorePage;

import javax.swing.*;

/**
 * @author kamontat
 * @since 21/5/59 - 23:51
 */
public interface ButtonFactory {

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
