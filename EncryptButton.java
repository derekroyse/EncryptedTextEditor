package textEditor;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.IOException;

/**
 * This class implements a JMenuItem that allows
 * users to save encrypted text files.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 * @see		JMenuItem
 */
@SuppressWarnings("serial")
public class EncryptButton extends JMenuItem implements ActionListener { 
	JTextArea textData;
	
   /**
   * Simple JMenuItem with an action listener that allows the user 
   * to save an encrypted file.
   * 
   * @param textArea 	the JTextArea from the GUI
   * @see				JMenuItem
   */ 
    public EncryptButton(JTextArea textArea) {    	
        textData = textArea;
        this.setText("Encrypt");
        this.addActionListener(this);
    }
 
	/**
	 * Action listener that calls the Writer.encrypt method and
	 * passes it the bytecode for the text in our program's 
	 * JTextArea when clicked.
	 * 
	 * @throws	IOException	on input error
	 * @see 	IOException
	 */ 
    public void actionPerformed(ActionEvent e) {
		try {
			Writer.encrypt(textData.getText());
		} catch (IOException e1) {
			new SimpleConfirm("No file selected.");
		}
    }
}
