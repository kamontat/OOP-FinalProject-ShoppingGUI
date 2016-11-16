package gui.store;

import gui.main.MainPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetAdminPage extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JPasswordField passwordField;
	private JTextField userField;
	
	public SetAdminPage() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);
		
		buttonOK.addActionListener(e -> onOK());
		
		buttonCancel.addActionListener(e -> onCancel());
		
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});
		
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	}
	
	private void onOK() {
		
		MainPage page = new MainPage();
		page.run(new Point(0, 0));
		
		dispose();
	}
	
	private void onCancel() {
		// add your code here if necessary
		System.exit(0);
	}
	
	public void run(Point point) {
		setMinimumSize(new Dimension(0, 0));
		pack();
		setLocation(point);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
}
