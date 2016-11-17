package code.constant;

import code.behavior.Product;

/**
 * @author kamontat
 * @since 26/5/59 - 00:30
 */
public enum ProductSize implements Product {
	BIG("Big"), SMALL("Small");
	
	private String name;
	
	private ProductSize(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getFolderName() {
		return name.toLowerCase() + "/";
	}
	
	@Override
	public String toString() {
		return name;
	}
}
