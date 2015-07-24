package textEditor;

import java.awt.*;
import javax.swing.*;
import javax.crypto.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class DecryptButton extends JPanel implements ActionListener { 
	JButton button;
	 JTextArea textData;
	 
    public DecryptButton(JTextArea textArea) {    	
        super(new BorderLayout());
        textData = textArea;
        button = new JButton("Decrypt");
        //button.setPreferredSize(new Dimension(200, 80));
        add(button, BorderLayout.CENTER);
        button.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
    	try {
    		textData.setText(Reader.decrypt());
		} catch (IOException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
