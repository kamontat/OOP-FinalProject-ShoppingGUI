package code.file;

import java.io.File;

/**
 * @author kamontat
 * @since 25/5/59 - 00:56
 */
public class ImageFileFactory extends FileFactory {
	public static String BIGSIZE = "bigSize";
	public static String SMALLSIZE = "smallSize";

	private String name;

	/**
	 * assign file by use path to group file and search only file with the "name"
	 *
	 * @param path
	 * 		the group of picture file
	 * @param name
	 * 		specific file name
	 */
	public ImageFileFactory(String path, String name, String size) {
		super(path);
		this.name = name;

		setFileBy(name);
		setFileBy(size);
	}

	public void setFileBy(String filter) {
		File[] files = getFile().listFiles();
		if (files != null) {
			for (File folder : files) {
				if (folder.getName().equalsIgnoreCase(filter)) {
					setFile(folder);
				}
			}
		}
	}

	public String[] getAllImagePath() {
		File[] images = getFile().listFiles();
		if (images != null) {
			String[] path = new String[images.length];
			for (int i = 0; i < path.length; i++) {
				path[i] = images[i].toPath().toString();
			}
			return path;
		}
		return new String[0];
	}
}
