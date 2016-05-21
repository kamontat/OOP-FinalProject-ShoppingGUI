package code.main;

import gui1.MainPage;

import java.awt.*;

/**
 * @author kamontat
 * @since 21/5/59 - 00:15
 */
public class Application {
	public static void main(String[] args) {
		MainPage page = new MainPage();
		page.run(new Point(0, 0));
	}
}
