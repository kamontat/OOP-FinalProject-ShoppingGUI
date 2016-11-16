package code.constant;

import code.file.File;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 9:08 PM
 */
public enum TextFile implements File<TextFile> {
	CUSTOMER("Customer"),
	PRODUCT("Product"),
	STORE_INFORMATION("StoreInfo"),
	FORM("Form"),
	ADMIN("Admin");
	
	private String name;
	
	TextFile(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(String name) {
		return toString().equals(name);
	}
	
	@Override
	public String getFileName() {
		return name + ".txt";
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public TextFile which() {
		return this;
	}
}