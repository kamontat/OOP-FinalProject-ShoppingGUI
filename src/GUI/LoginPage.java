package GUI;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import java.awt.Color;

public class LoginPage extends JFrame {
	private JTextField textField;
	final private String[][] userList = {{"Emmy", "emmy"}, {"Net", "net"}, {"May", "may"}, {"N2M", ""}};
	private JPasswordField passwordField;

	public LoginPage() {
		super("Login");
		this.initComponent();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void run() {
		setBounds(100, 100, 600, 350);
		this.setVisible(true);
	}

	public void initComponent() {
		setResizable(false);

		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserName = new JLabel("Username :");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Trajan Pro", Font.PLAIN, 35));
		lblUserName.setBounds(37, 100, 230, 47);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Trajan Pro", Font.PLAIN, 35));
		lblPassword.setBounds(34, 153, 230, 47);
		contentPane.add(lblPassword);

		textField = new JTextField();
		textField.setBounds(263, 103, 230, 35);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("H E A R T   T O   H E A R T   By  N 2 M ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("LiSong Pro", Font.BOLD, 13));
		lblNewLabel.setBounds(167, 293, 250, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("- Login -");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("paaymaay", Font.BOLD | Font.ITALIC, 50));
		lblNewLabel_1.setBounds(177, 15, 207, 45);
		contentPane.add(lblNewLabel_1);

		JLabel lblPleaseLoginBefore = new JLabel("PLEASE LOGIN BEFORE ENTER HEART TO HEART'S  STORE PAGE. ");
		lblPleaseLoginBefore.setForeground(Color.WHITE);
		lblPleaseLoginBefore.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseLoginBefore.setFont(new Font("LiSong Pro", Font.PLAIN, 14));
		lblPleaseLoginBefore.setBounds(23, 64, 537, 28);
		contentPane.add(lblPleaseLoginBefore);

		JButton buttonSubmit = new JButton("- SUBMIT -");
		buttonSubmit.setFont(new Font("Heiti TC", Font.PLAIN, 21));
		buttonSubmit.setBounds(223, 220, 131, 44);
		contentPane.add(buttonSubmit);

		JButton buttonCancel = new JButton("- CANCEL -");
		buttonCancel.setFont(new Font("Heiti TC", Font.PLAIN, 21));
		buttonCancel.setBounds(366, 220, 131, 44);
		contentPane.add(buttonCancel);

		passwordField = new JPasswordField();
		passwordField.setBounds(265, 155, 228, 34);
		contentPane.add(passwordField);

		buttonSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pass = "";
				for (int i = 0; i < passwordField.getPassword().length; i++) {
					pass += passwordField.getPassword()[i];
				}
				boolean check = true;
				for (String[] user : userList) {
					if (user[0].equals(textField.getText()) && user[1].equals(pass)) {
						StorePage store = new StorePage();
						store.run();
						setVisible(false);
						check = false;
					}
				}
				if (check) {
					JOptionPane.showMessageDialog(null, "Wrong Usename Or Password", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu main = new MainMenu();
					main.run();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}
		});
	}
}
