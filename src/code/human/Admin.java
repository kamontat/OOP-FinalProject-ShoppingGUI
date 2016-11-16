package code.human;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 8:27 PM
 */
public class Admin {
	private static Admin ourInstance;
	
	private String user;
	private String password;
	
	public static Admin getInstance(String user, String password) {
		if (ourInstance == null) {
			ourInstance = new Admin(user, password);
		}
		return ourInstance;
	}
	
	private Admin(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
	public boolean isAdmin(String user, String password) {
		return this.user.equals(user) && this.password.equals(password);
	}
	
	public static String encode(String text) {
		return Base64.getEncoder().encodeToString(text.getBytes());
	}
	
	public static String decode(String code) {
		return new String(Base64.getDecoder().decode(code));
	}
}
