package textEditor;

import java.io.*;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * The Reader class contains methods to load 
 * unecrypted files and to decrypt encrypted files.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 */
public class Reader{		
		/**
		 * This method loads byte data from a text file and converts it into
		 * a String to be displayed in the main program. If the file is
		 * invalid, the user is returned to the main text editor menu.
		 * 
		 * @return String		String version of the data in the file
		 * 						selected by the user
		 * @throws IOException	on input error
		 */
		public static String load() throws IOException{
			String fileData = "";
            String next = null;
			
			// Allow user to choose a file
            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);
            openFile.getSelectedFile();
            File file = openFile.getSelectedFile();
            
            // if no file selected
            if (file == null)
            {
            	new SimpleConfirm("No file selected.");
            	return null;
            }
            
            // Pull data from file by line
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));            
            while ((next = bufferedReader.readLine()) != null)
            {
            	fileData += next + "\n";
            }
            
            // Close bufferedReader and return fileData
            fileData = fileData.trim();
            bufferedReader.close();            
            return fileData;            
		}
		
		
		/**
		 * This method asks the user to select a file and to provide the 
		 * correct password. If both are successful, this method loads 
		 * byte data from a text file, decodes and decrypts it, and returns 
		 * the result as a String. If either fails, user is returned to the 
		 * main text editor menu.
		 * 
		 * @return String		Decrypted file data from selected file.
		 * @throws IOException	on input error
		 */
		public static String decrypt() throws IOException{			
		    String input = null;
		    String returnString = "";
			
			// Open file
			JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);	
            openFile.getSelectedFile();            
            File file = openFile.getSelectedFile();
            
            // if no file selected
            if (file == null)
            {
            	new SimpleConfirm("No file selected.");
            	return null;
            }
            
            FileInputStream inputStream = new FileInputStream(file); 
            
			// ask for keyword
 			input = (String)JOptionPane.showInputDialog("Enter your encryption password: ");
			if (input == null){
					new SimpleConfirm("A keyword is required to decrypt a file. Please choose another file.");
					inputStream.close();
					return null;
 				}
			
			// verify the keyword is valid (16 characters)
 			while (input.length() != 16){
 				input = (String)JOptionPane.showInputDialog("Invalid keyword. Please enter a 16 character keyword: ");
 				
 				if (input == null){
					new SimpleConfirm("A keyword is required to decrypt a file. Please choose another file.");
					inputStream.close();
					return null;
 				}
 			}

			// Attempt to decrypt the file 			
			try {
	            // read the file
				byte[] contentBytes =  new byte[(int)file.length()];
			    inputStream.read(contentBytes);
			    inputStream.close();
	        	
			    // Decryption
					// Create a key with keyword
			    	byte[] keyword = input.getBytes("utf-8");
				    Key key = new SecretKeySpec(keyword, "AES");
				    // Create cipher with key
				    Cipher c = Cipher.getInstance("AES");
				    c.init(Cipher.DECRYPT_MODE, key);		    
			    	// Decode, decrypt and convert to String		
		   		   	byte[] decoded = Base64.getDecoder().decode(contentBytes);
		   		    byte[] decValue = c.doFinal(decoded);
		   		    String decryptedValue = new String(decValue);
		   		    returnString = decryptedValue;
			}
	   		catch (IllegalBlockSizeException|NoSuchAlgorithmException|NoSuchPaddingException e){
				new SimpleConfirm("Invalid Encryption Info.");
	   		}
			catch (IllegalArgumentException e){
				new SimpleConfirm("Error. This file may not be encrypted.");
			}
			catch (BadPaddingException e){
				new SimpleConfirm("Invalid Keyword");
			}
			catch (InvalidKeyException e){
				new SimpleConfirm("Invalid Key Exception.");
			}
	   		    
	   		// Return the decrypted file data
			return returnString;
		}
}
