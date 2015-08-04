package textEditor;

import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import java.util.Base64;

public class Writer{ 		
		public static void save(byte[] content){
			JFileChooser openFile = new JFileChooser();
			openFile.showSaveDialog(null);
            openFile.getSelectedFile();
            
            File file = openFile.getSelectedFile();
			
			try (FileOutputStream fop = new FileOutputStream(file)) { 
				// if file doesn't exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
	 	 
				fop.write(content);
				fop.flush();
				fop.close();
	 
				System.out.println("Done");
	 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void encrypt(String content) throws IOException{
			String input = null;
			
			// ask for keyword		
			input = (String)JOptionPane.showInputDialog("Enter a 16 character keyword for encryption: ");
			if (input == null){
					input = "test";
				}
			
			while (input.length() != 16){
				input = (String)JOptionPane.showInputDialog("Invalid keyword. Please enter a 16 character keyword: ");
 				if (input == null){
 					break;
 				}
			}
			
 			// if the user cancelled
			if (input == null){
					JOptionPane.showConfirmDialog(null, "No keyword? Please try again without encrpyting.");
					return;
				}						

			try{
				//String input = "thisIsASecretKey";
				byte[] keyword = input.getBytes("utf-8");
				
				// Create a key with keyword
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
				System.out.write(encryptedData);
			}
			catch (IllegalBlockSizeException|BadPaddingException|NoSuchAlgorithmException|NoSuchPaddingException  e){
				System.out.println("Invalid Encryption Info. Exiting program.");
				System.exit(1);
			}
			catch (InvalidKeyException e){
				System.out.println("Invalid Key Exception. Exiting program.");
				System.exit(1);
			}
		}
}
