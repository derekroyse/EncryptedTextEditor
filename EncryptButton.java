package textEditor;

import java.awt.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
 
import javax.swing.JTextArea;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptButton extends JPanel implements ActionListener { 
	JButton button;
	 JTextArea textData;
	 
    public EncryptButton(JTextArea textArea) {    	
        super(new BorderLayout());
        textData = textArea;
        button = new JButton("Encrypt");
        add(button, BorderLayout.CENTER);
        button.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
    		try {
				Writer.encrypt(textData.getText());
			} catch (IllegalBlockSizeException e1) {
				e1.printStackTrace();
			} catch (BadPaddingException e1) {
				e1.printStackTrace();
			} catch (InvalidKeyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchPaddingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    }
}
