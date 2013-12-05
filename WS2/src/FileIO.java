import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/** 
 * 
 * FileIO is a utility class with read and write methods for byte arrays.
 * 
 * <h6>This code is released for educational purposes only and does not come with any 
 * warranty for the merchantability or fitness for any particular purpose.</h6>
 * @author Geoffery Miller
 * @version 0.1
 */
public class FileIO {
	/**
	 * This method writes a byte array to a file.
	 * @param file  The file to write, this will be created/overwritten.
	 * @param data	The data to write to the file as a byte array.
	 * @throws IOException	If there is a problem writing the file.
	 */
	public static void write(File file, byte[] data) throws IOException  {
		FileOutputStream fout = new FileOutputStream(file);
		fout.write(data);
		fout.close();
	}
	/**
	 * This method reads a file and returns the data from the file as a byte array.
	 * @param file The file to read.
	 * @return The data from the file as a byte array.
	 * @throws IOException	If there is a problem reading the file.
	 */
	public static byte[] read(File file) throws IOException {
		byte[] data = null;
		FileInputStream fin = new FileInputStream(file);
		int length = (int)file.length();
		data = new byte[length];
		fin.read(data);
		fin.close();
		return data;
	}
	
	private FileIO() {}
}
