package textEditor;

import java.io.*;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Reader{ 		
		public static String load() throws IOException{
            JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);	
            openFile.getSelectedFile();
            
            File file = openFile.getSelectedFile();
            String next = null;
            List<String> records = new ArrayList<String>();
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            
           while ((next = bufferedReader.readLine()) != null)
           {
        	   records.add(next);
           }
            
           String output = null;
           for (int i = 0; i < records.size(); i++)
           {
        	   if (i == records.size()-1)
        		   output += records.get(i);
        	   else
        		   output += records.get(i) + "\n";
           }
            
            // Close the BufferedReader
            bufferedReader.close();
            
            return output;
            
		}
		
		public static String decrypt() throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException{			
		    String input = "blank";
			
			// Open file
			JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);	
            openFile.getSelectedFile();            
            File file = openFile.getSelectedFile();            
            FileInputStream inputStream = new FileInputStream(file);
            
			// get keyword
            // ask for keyword
 			input = (String)JOptionPane.showInputDialog("Enter your encryption password: ");
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
					JOptionPane.showConfirmDialog(null, "No keyword? Please choose another file.");
					inputStream.close();
					return null;
				}

			//String input = "thisIsASecretKey";
			byte[] keyword = input.getBytes("utf-8");			

            // read the file
			byte[] contentBytes =  new byte[(int)file.length()];
		    inputStream.read(contentBytes);    
        	
		    // Decryption
				// Create a key with keyword
			    Key key = new SecretKeySpec(keyword, "AES");
			    Cipher c = Cipher.getInstance("AES");
			    c.init(Cipher.DECRYPT_MODE, key);		    
		    	// Decrypt with key		
	   		   	byte[] decoded = Base64.getDecoder().decode(contentBytes);
	   		    byte[] decValue = c.doFinal(decoded);
	   		    String decryptedValue = new String(decValue);
	   		    
	        // Close the BufferedReader	            
	   		inputStream.close();	            
	   		return decryptedValue;
		}
}
