package code.file;

import code.constant.ImageFolder;
import code.constant.ProductSize;
import code.constant.ProductType;
import code.constant.TextFile;

import javax.swing.*;
import java.io.*;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author kamontat
 * @since 20/5/59 - 23:52
 */
public class FileFactory {
	/**
	 * get current dir
	 */
	public static File dir = Paths.get("").toAbsolutePath().toFile();
	
	private Map<TextFile, File> textFile;
	private Map<ImageFolder, Map<ProductType, Map<ProductSize, File>>> imageFolder;
	private int size;
	private static FileFactory factory = new FileFactory();
	
	private final String separate = ":";
	
	private FileFactory() {
		textFile = new HashMap<>();
		imageFolder = new HashMap<>();
		
		String textPath = dir.getPath() + "/src/textfile/";
		
		TextFile[] allText = TextFile.values();
		
		for (TextFile cons : allText) {
			String newPath = textPath + cons.getFileName();
			textFile.putIfAbsent(cons, new File(newPath));
		}
		
		String imagePath = dir.getPath() + "/src/images/";
		ImageFolder product = ImageFolder.PRODUCT;
		ProductType[] allType = ProductType.values();
		ProductSize[] allSize = ProductSize.values();
		
		String newPath = imagePath + product;
		for (ProductType type : allType) {
			for (ProductSize size : allSize) {
				String newestPath = newPath + type + "/" + size + "/";
				Map<ProductSize, File> innerInner = new HashMap<>();
				Map<ProductType, Map<ProductSize, File>> inner = new HashMap<>();
				
				innerInner.put(size, new File(newestPath));
				
				inner.putIfAbsent(type, innerInner);
				
				imageFolder.putIfAbsent(product, inner);
			}
		}
		
		checkFile();
	}
	
	public static FileFactory getInstance() {
		return factory;
	}
	
	private void checkFile() {
		ArrayList<String> pathNotFound = new ArrayList<>();
		textFile.forEach((textFile1, file) -> {
			while (!file.exists()) {
				pathNotFound.add(file.getAbsolutePath());
			}
		});
		
		imageFolder.forEach((imageFolder1, productTypeMapMap) -> productTypeMapMap.forEach((productType, productSizeFileMap) -> productSizeFileMap.forEach((productSize, file) -> {
			while (!file.exists()) {
				pathNotFound.add(file.getAbsolutePath());
			}
		})));
		
		StringBuilder print = new StringBuilder();
		pathNotFound.forEach(s -> {
			print.append(s).append("\n");
		});
		
		JOptionPane.showMessageDialog(null, print.toString(), "Total NotFound: " + pathNotFound.size() + " file(s)", JOptionPane.ERROR_MESSAGE);
	}
	
	public File getTextFile(TextFile cons) {
		return textFile.get(cons);
	}
	
	/**
	 * return file if this textFile already created (NOT overwrite, if want to overwrite please use method <b>updateTextFile</b>)
	 *
	 * @param cons
	 * 		which text-file
	 * @param file
	 * 		which file to update
	 * @return file that exist, or null
	 */
	public File setTextFile(TextFile cons, File file) {
		if (!textFile.containsKey(cons)) {
			return textFile.putIfAbsent(cons, file);
		}
		return null;
	}
	
	/**
	 * update file in some <b>TextFile</b>
	 *
	 * @param cons
	 * 		which text-file
	 * @param file
	 * 		which file to update
	 * @return file that be replace, or null if key not exist
	 */
	public File updateTextFile(TextFile cons, File file) {
		if (textFile.containsKey(cons)) {
			return textFile.replace(cons, file);
		}
		return null;
	}
	
	public void update(TextFile which, String text) {
		try {
			FileWriter write = new FileWriter(textFile.get(which), true);
			write.write(text + "\n");
			size++;
			write.close();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}
	
	public void overwrite(TextFile which, Object[][] list) {
		String output = "";
		for (Object[] texts : list) {
			for (int j = 0; j < texts.length; j++) {
				// last index
				if (j == texts.length - 1) {
					output += texts[j] + " \n";
				} else {
					output += texts[j] + ":";
				}
			}
		}
		// read total number
		size = list.length;
		
		try {
			FileWriter write = new FileWriter(textFile.get(which));
			write.write(output);
			size++;
			write.close();
		} catch (IOException e) {
			System.err.println(e.toString());
		}
		
	}
	
	public String[][] read(code.file.File which) {
		try {
			ArrayList<String[]> text = new ArrayList<>();
			String temp;
			
			BufferedReader read = new BufferedReader(new FileReader(textfile));
			
			while ((temp = read.readLine()) != null) {
				String[] info = temp.split(separate);
				for (int i = 0; i < info.length; i++) {
					info[i] = info[i].trim();
				}
				text.add(info);
			}
			
			// read total number
			size = text.size();
			
			return text.toArray(new String[text.size()][]);
		} catch (IOException e) {
			System.err.println(e.toString());
		}
		return null;
	}
}
