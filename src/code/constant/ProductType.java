package code.constant;

/**
 * @author kamontat
 * @since 26/5/59 - 00:23
 */
public enum ProductType {
	EARRING("Earring"), PENDANT("Pendant"), RING("Ring");
	
	private String name;
	
	private ProductType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name.toLowerCase();
	}
}
