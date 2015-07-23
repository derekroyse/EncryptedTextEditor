package textEditor;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
