package code.file;

/**
 * @author kamontat
 * @version 1.0
 * @since 11/16/2016 AD - 11:07 PM
 */
public interface File<T> {
	public boolean equals(String name);
	
	public String getFileName();
	
	public T which();
}
