
package gui1;

import code.store.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AdderCustomerPage extends JDialog {
	private Store store = MainPage.store;

	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField nameField;
	private JTextField lastNameField;
	private JTextField idField;
	private JTextField ageField;
	private JComboBox memberComboBox;
	private JComboBox genderComboBox;

	public AdderCustomerPage() {
		setContentPane(contentPane);
		setModal(true);

		buttonOK.addActionListener(e -> onOK());
		buttonCancel.addActionListener(e -> onCancel());

		// call onCancel() when ESCAPE been press
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		// call onOK() when ENTER been press
		contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
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

			if (checkNumber(id) && checkNumber(age) && id.length() == 4 && age.length() <= 3) {
				System.out.println("ID valid");
				System.out.println("AGE valid");
				System.out.println("Name: " + name + " " + lastName + " Gender: " + gender);
				System.out.println("Member: " + member);
			}
		}
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}

	private boolean checkNumber(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (Character.isLetter(text.charAt(i))) return false;
		}
		return true;
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

	public static void main(String[] args) {
		AdderCustomerPage dialog = new AdderCustomerPage();
		dialog.run(new Point(0, 0));
	}
}
