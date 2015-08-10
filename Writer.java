package textEditor;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.Base64;

/**
 * The Writer class contains methods to write
 * unencrypted files and to save encrypted files.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 */
public class Writer{ 		
		/**
		 * Save unencrypted data to a file. If file cannot be saved, 
		 * the user is returned to the main text editor menu.
		 * 
		 * @param	content		byte code representation of the data in the main
		 * 						program's JTextArea
		 * @throws 	IOException	on input error
		 */
		public static void save(byte[] content) throws IOException{
			JFileChooser openFile = new JFileChooser();
			openFile.showSaveDialog(null);
            File file = openFile.getSelectedFile();
			
            // If no file is selected
            if (file == null)
            {
            	new SimpleConfirm("No file selected.");
            	return;
            }
            
            // Attempt to write data to file 
			FileOutputStream fop = new FileOutputStream(file); 
			// if file doesn't exist, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
			// Save data to file, then close
			fop.write(content);
			fop.flush();
			fop.close();
		}
		
		/**
		 * This method asks the user to select a file and to provide a valid
		 * passkey. If both are successful, this method encodes and encrypts the JTextArea
		 * data and saves it to a file. If either fails, user is returned to the 
		 * main text editor menu. 
		 * 
		 * @param	content		byte code representation of the data in the main
		 * 						program's JTextArea
		 * @throws IOException	on input error
		 */
		public static void encrypt(String content) throws IOException{
			String input = null;
			
			// ask for keyword
			input = (String)JOptionPane.showInputDialog("Enter a 16 character keyword for encryption: ");
			// If the user cancelled
			if (input == null){
				new SimpleConfirm("Can't encrypt without a keyword!");
				return;
			}
			
			// verify the keyword is valid (16 characters)
			while (input.length() != 16){
				input = (String)JOptionPane.showInputDialog("Invalid keyword. Please enter a 16 character keyword: ");
				
				// If the user cancelled
 				if (input == null){
 					new SimpleConfirm("Can't encrypt without a keyword!");
 					return;
 				}
			}

			try{						
				// Create a key with keyword
				byte[] keyword = input.getBytes("utf-8");
				Key key = new SecretKeySpec(keyword, "AES");
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, key);
				
				// Encrypt with key
				byte[] contentBytes = content.getBytes("utf-8");
				byte[] encryptedData = cipher.doFinal(contentBytes);
				encryptedData = Base64.getEncoder().withoutPadding().encode(encryptedData);
				
				// pass encrypted content to save method
				// which will write it to a file
				save(encryptedData);
			}			
			// Exception handling
			catch (IllegalBlockSizeException|BadPaddingException|NoSuchAlgorithmException|NoSuchPaddingException  e){
				new SimpleConfirm("Invalid Encryption Info.");
			}
			catch (InvalidKeyException e){
				new SimpleConfirm("Invalid Key Exception.");
			}
		}
}
