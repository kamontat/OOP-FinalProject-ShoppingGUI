package code.Interface;

import gui.ShoppingPage;
import gui1.customer.CustomerPage;
import gui1.store.LoginPage;
import gui1.main.MainPage;
import gui1.store.StorePage;

import javax.swing.*;

/**
 * @author kamontat
 * @since 21/5/59 - 23:51
 */
public interface ButtonAction {

	default void toMain(JFrame frame, JButton main) {
		main.addActionListener(e -> {
			MainPage page = new MainPage();
			page.run(frame.getLocation());
			frame.dispose();
		});
	}

	default void toShopping(JFrame frame, JButton shopping) {
		shopping.addActionListener(e -> {
			ShoppingPage page = new ShoppingPage();
			page.run();
			frame.dispose();
		});
	}

	default void toCustomer(JFrame frame, JButton customer) {
		customer.addActionListener(e -> {
			CustomerPage page = new CustomerPage();
			page.run(frame.getLocation());
			frame.dispose();
		});
	}

	default void toStore(JFrame frame, JButton store) {
		store.addActionListener(e -> {
			StorePage page = new StorePage();
			page.run(frame.getLocation());
			frame.dispose();
		});
	}

	default void toLogin(JFrame frame, JButton login) {
		login.addActionListener(e -> {
			LoginPage page = new LoginPage(frame);
			page.run(frame.getLocation());
		});
	}
	
	default void toExit(JButton exit) {
		exit.addActionListener(e -> {
			System.exit(0);
		});
	}
}
