package code.main;

import gui.store.SetAdminPage;

import java.awt.*;

/**
 * @author kamontat
 * @since 21/5/59 - 00:15
 */
public class Application {
	public static void main(String[] args) {
		SetAdminPage page = new SetAdminPage();
		page.run(new Point(0, 0));
	}
}
