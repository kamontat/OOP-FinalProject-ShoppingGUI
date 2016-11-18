package code.test;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * To Extract jar file into normal file (System file)
 *
 * @author kamontat
 * @version 3.3
 * @since 11/18/2016 AD - 10:22 PM
 */
public class JarExtract {
	// constant
	private final int FOLDER = 1;
	private final int FILE = 2;
	
	private JarFile jarFile;
	private File destFolder;
	private int folderNum = 0;
	private int fileNum = 0;
	
	private long runTime;
	
	private boolean complete;
	
	/**
	 * when you call this, it will create your destination folder <br>
	 * if you want to extract immediately, use <code>extract</code> parameter <br>
	 * otherwise make it as <code>false</code>, and you can manual extract by using method <code>extract()</code> <br>
	 *
	 * @param jar
	 * 		jar file that want to extract
	 * @param dest
	 * 		destination folder
	 * @param extract
	 * 		you want to immediately extract OR not
	 */
	public JarExtract(JarFile jar, File dest, boolean extract) {
		jarFile = jar;
		destFolder = dest;
		
		assignDestinationFolder();
		
		if (extract) extract();
	}
	
	/**
	 * get Truly Destination Folder <br>
	 * they have few condition that may input <code>dest</code> isn't folder output, Like isn't directory
	 * so that method will create new folder call "destinationFolder" instead
	 */
	private void assignDestinationFolder() {
		String text = "";
		if (!destFolder.isDirectory()) {
			File parent = destFolder.getParentFile();
			destFolder = new File(parent.getAbsolutePath() + File.separator + "destinationFolder");
		}
		if (destFolder.mkdir()) {
			text = "(new File )";
		}
		// popup show destination file path
		// JOptionPane.showMessageDialog(null, destFolder.getAbsolutePath(), text + "destination file is.. ", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * extract jar manually
	 */
	public void extract() {
		long millSecond = System.currentTimeMillis();
		
		Enumeration enumEntries = jarFile.entries();
		try {
			while (enumEntries.hasMoreElements()) {
				JarEntry jarEntry = (JarEntry) enumEntries.nextElement();
				File file = new File(destFolder.getPath() + File.separator + jarEntry.getName());
				
				switch (create(jarEntry, file)) {
					case FILE:
						changeContents(jarEntry, file);
						fileNum++;
						break;
					case FOLDER:
						folderNum++;
						break;
					default:
				}
			}
			complete = true;
		} catch (IOException e) {
			complete = false;
			e.printStackTrace();
		}
		runTime = System.currentTimeMillis() - millSecond;
	}
	
	/**
	 * read content inside jar file and place it into <b>FILE (only)</b>
	 *
	 * @param entry
	 * 		entry of each file
	 * @param file
	 * 		file to write
	 * @throws IOException
	 * 		exception if have problem in jar, entry or file
	 */
	private void changeContents(JarEntry entry, File file) throws IOException {
		InputStream input = jarFile.getInputStream(entry); // stream from jar
		FileOutputStream fileOutput = new FileOutputStream(file); // stream from dist file
		while (input.available() > 0) {  // write contents of 'jar' to 'file'
			fileOutput.write(input.read());
		}
		fileOutput.close();
		input.close();
	}
	
	/**
	 * create file or folder that live in jar file by using JarEntry
	 *
	 * @param entry
	 * 		jar entry, you can get from JarFile.entries() -> Enumeration
	 * 		and then loop look every file in jar by <code>while (Enumeration.hasMoreElements())</code> <br>
	 * 		and get each entry by <code>(JarEntry) Enumeration.nextElement()</code> <br>
	 * @param file
	 * 		position to create each file
	 * @return constant that 1 is FOLDER, 2 is FILE
	 * @throws IOException
	 * 		exception if have problem in entry or file
	 * @see JarFile
	 * @see Enumeration
	 * @see JarEntry
	 */
	private int create(JarEntry entry, File file) throws IOException {
		if (entry.isDirectory()) {
			if (file.mkdir()) {
				// System.out.println("create Folder: " + file.getName());
			}
			return FOLDER;
		} else {
			// this for META-INF
			if (file.getParentFile().mkdir()) {
				// System.out.println("create META-INF");
			}
			
			if (file.createNewFile()) {
				// System.out.println("create File: " + file.getName());
			}
		}
		return FILE;
	}
	
	/**
	 * check complete extract or not
	 *
	 * @return true if complete, otherwise false
	 */
	public boolean isComplete() {
		return complete;
	}
	
	/**
	 * get path that destination extract
	 *
	 * @return path of destination folder
	 */
	public String extractTo() {
		return destFolder.getAbsolutePath();
	}
	
	/**
	 * get run time that extract jar file IN iff extract complete (<b>Run Time Unit is MilliSecond</b>)
	 *
	 * @return Run Time if complete, otherwise return 0;
	 */
	public long getRunTime() {
		if (complete) return runTime;
		return 0;
	}
	
	/**
	 * set new jarFile in case that more that 1 jar that need to extract
	 *
	 * @param jarFile
	 * 		new Jar File
	 */
	public void setJarFile(JarFile jarFile) {
		complete = false;
		this.jarFile = jarFile;
	}
	
	/**
	 * set new destination folder in case that more that 1 jar that need to extract
	 *
	 * @param destFolder
	 * 		new destination folder
	 */
	public void setDestFolder(File destFolder) {
		this.destFolder = destFolder;
	}
	
	/**
	 * return how many folder that extract out
	 *
	 * @return count number folder in jar
	 */
	public int getFolderNum() {
		return folderNum;
	}
	
	/**
	 * return how many file that extract out
	 *
	 * @return count number file in jar
	 */
	public int getFileNum() {
		return fileNum;
	}
	
	/**
	 * use to check file that contain extension or NOT
	 *
	 * @param f
	 * 		some file
	 * @param e
	 * 		extension
	 * @return true if contain, otherwise return false
	 */
	private static boolean isExtension(File f, String e) {
		String ext = "";
		String s = f.getName();
		int i = s.lastIndexOf('.');
		
		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext.equals(e);
	}
	
	/**
	 * use to find some file that contain extension, example ".jar", ".java"
	 *
	 * @param folder
	 * 		some folder that you think it contain file with extension
	 * @param extension
	 * 		some extension <b>without</b> "." (dot)
	 * @return file that have extension same with input, otherwise will throw FileNotFoundException
	 * @throws FileNotFoundException
	 * 		throw with DON'T have that extension
	 */
	public static File[] find(File folder, String extension) throws FileNotFoundException {
		ArrayList<File> files = new ArrayList<>();
		if (folder.isDirectory()) {
			File[] all = folder.listFiles();
			if (all == null || all.length == 0) throw new FileNotFoundException();
			for (File file : all) {
				if (isExtension(file, extension)) {
					files.add(file);
				}
			}
		}
		if (files.size() == 0) throw new FileNotFoundException();
		return files.toArray(new File[files.size()]);
	}
	
	public static void main(String[] args) {
		File destFile = Paths.get("").toAbsolutePath().toFile();
		File projectLocation = Paths.get("Application").toAbsolutePath().toFile();
		
		try {
			File[] files = JarExtract.find(projectLocation, "jar");
			projectLocation = files[0];
			JarFile jarFile = new JarFile(projectLocation);
			
			JarExtract extract = new JarExtract(jarFile, projectLocation, true);
			
			if (extract.isComplete()) {
				System.out.println();
				System.out.println(extract.extractTo());
				System.out.println("Extract Folder:" + extract.getFolderNum());
				System.out.println("Extract File:" + extract.getFileNum());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}