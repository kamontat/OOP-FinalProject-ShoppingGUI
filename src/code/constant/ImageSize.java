package code.constant;

/**
 * @author kamontat
 * @since 26/5/59 - 00:30
 */
public enum ImageSize {
	BIG("big"),
	SMALL("small");

	private String name;

	private ImageSize(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
