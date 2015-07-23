package textEditor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.*;
import javax.swing.JFileChooser;

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
		
		public static void encrypt(String content) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException{
			// ask for keyword			
			String input = "thisIsASecretKey";
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
			
			// Decryption test
				// Create a key with keyword			
			    Key key2 = new SecretKeySpec(keyword, "AES");
			    Cipher c = Cipher.getInstance("AES");
			    c.init(Cipher.DECRYPT_MODE, key2);			    
			    // Decrypt with key			    
			    byte[] decoded = Base64.getDecoder().decode(encryptedData);
			    byte[] decValue = c.doFinal(decoded);
			    String decryptedValue = new String(decValue);
		}
}
