package code.constant;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 9:08 PM
 */
public enum TextFile {
	CUSTOMER("Customer.txt"),
	PRODUCT("Product.txt"),
	STORE_INFORMATION("StoreInfo.txt"),
	FORM("Form.txt"),
	ADMIN("isAdminSet.txt");
	
	private String name;
	
	TextFile(String name) {
		this.name = name;
	}
	
	public String getFileName() {
		return name;
	}
}