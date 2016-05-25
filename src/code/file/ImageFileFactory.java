package code.file;

import code.constant.ImageSize;

import java.io.File;

/**
 * @author kamontat
 * @since 25/5/59 - 00:56
 */
public class ImageFileFactory extends FileFactory {

	/**
	 * assign file by use path to group file and search only file with the "name"
	 *
	 * @param path
	 * 		the group of picture file
	 * @param name
	 * 		specific file name
	 */
	public ImageFileFactory(String path, String name, ImageSize size) {
		super(path);

		setFileBy(name);
		setFileBy(size.getName());
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
