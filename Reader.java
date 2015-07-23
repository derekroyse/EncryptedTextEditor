package textEditor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;

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
		    // Open file
			JFileChooser openFile = new JFileChooser();
            openFile.showOpenDialog(null);	
            openFile.getSelectedFile();            
            File file = openFile.getSelectedFile();            
            FileInputStream inputStream = new FileInputStream(file);
            
			// get keyword
			String input = "thisIsASecretKey";
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
