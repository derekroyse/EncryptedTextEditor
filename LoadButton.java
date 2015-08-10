package textEditor;

import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

/**
 * This class implements a JMenuItem that allows
 * users to load unencrypted text files.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 * @see		JMenuItem
 */
@SuppressWarnings("serial")
public class LoadButton extends JMenuItem implements ActionListener { 
	JTextArea textData;
	 
   /**
   * Simple JMenuItem with an action listener that allows the user to load a file.
   * 
   * @param textArea 	the JTextArea from the GUI
   * @see				JMenuItem
   */ 
    public LoadButton(JTextArea textArea) {    	
        textData = textArea;
        this.setText("Load");
        this.addActionListener(this);
    }
 
	/**
	 * Action listener that calls the Reader.load method and
	 * sets the text in the program's JTextArea to the String
	 * that it returns.
	 * 
	 * @throws	IOException 	on input error
	 * @see 	IOException
	 */ 
    public void actionPerformed(ActionEvent e) {
        try {        	
        	textData.setText(Reader.load());
		} catch (IOException e1) {
			new SimpleConfirm("No file selected.");
		}
    }
}
