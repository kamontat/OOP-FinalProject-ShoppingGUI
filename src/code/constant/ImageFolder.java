package code.constant;

import code.file.File;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 9:16 PM
 */
public enum ImageFolder implements File<ImageFolder> {
	PRODUCT("product");
	
	private String name;
	
	ImageFolder(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(String name) {
		return getFileName().equals(name);
	}
	
	@Override
	public String getFileName() {
		return name;
	}
	
	@Override
	public ImageFolder which() {
		return this;
	}
}
