package textEditor;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * This class implements a JMenuItem that allows
 * users to save unencrypted text files.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 * @see		JMenuItem
 */
@SuppressWarnings("serial")
public class SaveButton extends JMenuItem implements ActionListener { 
	JTextArea textData;
	
   /**
   * Simple JMenuItem with an action listener that allows users to save a file.
   * 
   * @param textArea 	the JTextArea from the GUI
   * @see				JMenuItem
   */ 
    public SaveButton(JTextArea textArea) {    	
        textData = textArea;
        this.setText("Save");
        this.addActionListener(this);
    }
 
	/**
	 * Action listener that calls the Writer.save method and
	 * passes it the bytecode for the text in our program's 
	 * JTextArea when clicked.
	 * 
	 * @throws	IOException 	on input error
	 * @see 	IOException
	 */ 
    public void actionPerformed(ActionEvent e) {
    	try{
    		Writer.save(textData.getText().getBytes());
    	}
    	catch (IOException e1){
    		new SimpleConfirm("No file selected.");
    	}
    }
}
