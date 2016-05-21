package code.file;

import java.io.*;
import java.util.*;

/**
 * @author kamontat
 * @since 20/5/59 - 23:52
 */
public class FileFactory {
	private File file;
	private int count = 0;

	/**
	 * If use this constructor <br>
	 * Please, call method <b>setPath</b> to set file
	 */
	public FileFactory() {
		file = null;
	}

	public FileFactory(String path) {
		file = new File(path);
		if (!file.exists()) System.err.println("file is not exist.");
	}

	public void setPath(String path) {
		file = new File(path);
		if (!file.exists()) System.err.println("file is not exist.");
	}

	public File getFile() {
		return file;
	}

	public void write(String text) {
		try {
			FileWriter write = new FileWriter(file, true);
			write.write(++count + ":" + text + "\n");
			write.close();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	public String[][] read(String separate) {
		try {
			ArrayList<String[]> text = new ArrayList<>();
			String temp;
			BufferedReader read = new BufferedReader(new FileReader(file));

			while ((temp = read.readLine()) != null) {
				String[] info = temp.split(separate);
				for (int i = 0; i < info.length; i++) {
					info[i] = info[i].trim();
				}
				text.add(info);
			}

			// read total number
			count = text.size();

			return text.toArray(new String[text.size()][]);
		} catch (IOException e) {
			System.err.println(e.toString());
		}
		return null;
	}
}
