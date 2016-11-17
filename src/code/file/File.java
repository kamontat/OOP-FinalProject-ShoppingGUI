package code.file;

import code.constant.ImageFolder;
import code.constant.TextFile;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 11:07 PM
 */
public interface File<T> {
	public boolean equals(String name);
	
	public String getFileName();
	
	public T which();
	
	default String getFolderName() {
		if (this instanceof TextFile) {
			return "textfile/";
		} else if (this instanceof ImageFolder) {
			return "images/";
		}
		return "";
	}
}
