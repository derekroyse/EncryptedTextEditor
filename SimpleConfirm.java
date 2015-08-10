package textEditor;

import javax.swing.*;
/**
 * The SimpleConfirm class is a simple class that shows
 * a confirm dialog box with only an "OK" button.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 */
public class SimpleConfirm{ 	
    /**
     * Shows a confirm dialog box with only an "OK" button.
     * 
     * @param	input	String to be displayed in the message box
     */
    public SimpleConfirm(String input) {    	
    	String[] options = {"OK"};
    	JPanel panel = new JPanel();
    	JLabel lbl = new JLabel(input);
    	panel.add(lbl);
    	JOptionPane.showOptionDialog(null, panel, "Alert", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options , options[0]);
    }
}
