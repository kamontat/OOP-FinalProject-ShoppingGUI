package code.constant;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 9:16 PM
 */
public enum ImageFolder {
	PRODUCT("product");
	
	private String name;
	
	ImageFolder(String name) {
		this.name = name;
	}
	
	public String getFileName() {
		return name;
	}
}
