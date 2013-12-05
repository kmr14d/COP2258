//**********************************************************
// Assignment: A4: CryptApplication
// Username: HDP12
// Author: HELEN PARKER
// Creation date: 11/13/13
// Completion time: 2
//
// Honor Code: I pledge that this program represents my own program code.
// I worked with (enter the names of others that you worked with on this assignment) 
// in designing and debugging my program.
//*********************************************************

import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class CryptApplication {
	
	public static void main(String[] args) {
		System.out.println("Crypt Application");
		Scanner scan = new Scanner(System.in);
		String choice = "";
		
		System.out.print("Input File Name: ");
		String input = scan.nextLine();
		// Make a file object called fileIn using the input String
		File fileIn = new File(input);
		
		
		System.out.print("Output File Name: ");
		String output = scan.nextLine();
		// Make a file object called fileOut using the output String
		File fileOut = new File(output);
		
		System.out.print("Password: ");
		String password = scan.nextLine();
	
		// This will loop until a valid encrypt or decrypt option is made
		while (choice == "") {
			System.out.print("Encrypt or Decrypt? ");
			choice = scan.nextLine();
			if (choice.toLowerCase().charAt(0) == 'e') {
				// call encryptFile(fileIn, fileOut, password);
				encryptFile(fileIn, fileOut, password);
			}
			else if(choice.toLowerCase().charAt(0) =='d') {
				// call decryptFile(fileIn, fileOut, password);
				decryptFile(fileIn, fileOut, password);
			}
			else {
				System.out.println("Please try again.");
				choice = "";
			}
		}
		System.out.println("Operation Complete.");
	}
	
	public static void encryptFile(File input, File output, String password) {
		try{
			// Read the data file
			// Encrypt the data and write it out
			byte[] data = FileIO.read(input);
			Crypt a = new Crypt(password);
			byte[] ecryp = a.encrypt(data);
			FileIO.write(output, ecryp);	
			
		}catch(IOException e) {
			System.out.println("Could not read/write file.");
		}catch (Exception e) {
			System.out.println("Encryption error.");
		}
	}
	
	public static void decryptFile(File input, File output, String password) {
		try{
			// Read the encrypted data 
			// and write out the decrypted data
			byte[] data = FileIO.read(input);
			Crypt a = new Crypt(password);
			byte[] dcryp = a.decrypt(data);
			FileIO.write(output, dcryp);
			
		}catch(IOException e) {
			System.out.println("Could not read/write file.");
		} catch (Exception e) {
			System.out.println("Encryption error.");
		}

	}
}


