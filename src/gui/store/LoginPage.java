package gui.store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class LoginPage extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JPasswordField passwordField;
	private JTextField userField;

	public LoginPage(JFrame page) {
		setContentPane(contentPane);
		setModal(true);

		// do action when user press button
		buttonOK.addActionListener(e -> onOK(page));
		buttonCancel.addActionListener(e -> onCancel());

		// call onCancel() when ESCAPE been press
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		// call onOK() when ENTER been press
		contentPane.registerKeyboardAction(e -> onOK(page), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	private void onOK(JFrame page) {
		String user = userField.getText();
		String password = String.valueOf(passwordField.getPassword());

		if (checkAdmin(user, password)) {
			StorePage store = new StorePage();
			store.run(getLocation());

			page.dispose();
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Wrong Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void onCancel() {
		dispose();
	}

	private boolean checkAdmin(String user, String pass) {
		return user.equals("admin") && pass.equals("password");
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(340, 240));
		pack();
		setLocation(point);
		setVisible(true);
	}
}
