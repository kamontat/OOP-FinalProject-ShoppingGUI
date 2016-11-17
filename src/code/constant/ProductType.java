package code.constant;

import code.behavior.Product;

/**
 * @author kamontat
 * @since 26/5/59 - 00:23
 */
public enum ProductType implements Product {
	EARRING("Earring"), PENDANT("Pendant"), RING("Ring");
	
	private String name;
	
	private ProductType(String name) {
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
