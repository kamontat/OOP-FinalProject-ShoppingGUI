package code.constant;

/**
 * @author kamontat
 * @since 26/5/59 - 00:30
 */
public enum ProductSize {
	BIG("Big"),
	SMALL("Small");

	private String name;

	private ProductSize(String name) {
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
