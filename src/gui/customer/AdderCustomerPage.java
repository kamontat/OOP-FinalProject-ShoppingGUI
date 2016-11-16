package gui.customer;

import code.human.Customer;
import code.store.Store;
import gui.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AdderCustomerPage extends JDialog {
	private Store store = Store.getInstance();

	private int CORRECTID = 0;
	private int DUPLICATEID = 1;
	private int INVALIDID = 2;

	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField idField;
	private JTextField ageField;
	private JComboBox memberComboBox;
	private JComboBox genderComboBox;
	private JLabel checkIDLabel;

	private Customer newCustomer;

	public AdderCustomerPage() {
		setContentPane(contentPane);
		setModal(true);

		// check id every time
		checkIDInstant();

		buttonOK.addActionListener(e -> onOK());
		buttonCancel.addActionListener(e -> onCancel());

		// call onCancel() when ESCAPE been press
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		// call onOK() when ENTER been press
		contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}

	public Customer getNewCustomer() {
		return newCustomer;
	}

	private void onOK() {
		if (idField.getText().equals("") || nameField.getText().equals("") || lastNameField.getText().equals("") || ageField.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please, input all information", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			String id = idField.getText();
			String name = convertFirstToCapital(nameField.getText());
			String lastName = convertFirstToCapital(lastNameField.getText());
			String gender = (String) genderComboBox.getSelectedItem();
			String age = ageField.getText();
			String member = (String) memberComboBox.getSelectedItem();
			if (checkValidID(id) == CORRECTID && checkNumber(age) && age.length() <= 3) {
				// add new human
				newCustomer = new Customer(id, name, lastName, gender, age, member);
				store.addCustomer(newCustomer);
				MainPage.reWriteCustomer();
				dispose();
			} else
				JOptionPane.showMessageDialog(null, "Enter correct all information", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}

	private void checkIDInstant() {
		idField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				updateCheckIDLabel();
			}
		});
	}

	private boolean checkNumber(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isLetter(text.charAt(i))) return false;
		}
		return true;
	}

	/**
	 * if id input is valid or not
	 *
	 * @param id
	 * 		id String
	 * @return number Error code
	 */
	private int checkValidID(String id) {
		// all is number, and length should be 4
		if (!checkNumber(id) || id.length() != 4) {
			return INVALIDID;
		}

		for (Customer customer : store.getCustomerList()) {
			if (customer.getID().equals(id)) return DUPLICATEID;
		}
		return CORRECTID;
	}

	private void updateCheckIDLabel() {
		if (checkValidID(idField.getText()) == CORRECTID) {
			checkIDLabel.setText("Valid");
			checkIDLabel.setForeground(new Color(0, 255, 0));
		} else if (checkValidID(idField.getText()) == DUPLICATEID) {
			checkIDLabel.setText("Already Member");
			checkIDLabel.setForeground(new Color(255, 251, 0));
		} else {
			checkIDLabel.setText("Invalid");
			checkIDLabel.setForeground(new Color(255, 0, 0));
		}
	}

	private String convertFirstToCapital(String text) {
		String first = text.substring(0, 1).toUpperCase();
		String second = text.substring(1, text.length());
		return first + second;
	}

	public void run(Point point) {
		setMinimumSize(new Dimension(500, 220));
		pack();
		setLocation(point);
		setVisible(true);
	}
}
