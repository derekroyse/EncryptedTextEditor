package textEditor;

import java.awt.GridLayout;
import javax.swing.*;

/**
 * This program allows users to save, load,
 * encrypt, and decrypt files with AES encryption.
 * 
 * @author	Derek Royse
 * @version	1.0
 * @since	2015-08-05
 */

public class TextEditor{
   /**
   * Starts the textEditor program by creating and showing the Swing 
   * GUI on the screen.
   */
	private static void createAndShowGUI() {
        // Window setup
        JFrame frame = new JFrame("Simple Text Editor");
        JPanel panel = new JPanel(new GridLayout(1, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Menu setup
        JMenu menu = new JMenu("File");        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);        
        
        // Text Area
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setLineWrap(true); 
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
        
        // Menu options
        SaveButton save = new SaveButton(textArea);
        menu.add(save);
        LoadButton load = new LoadButton(textArea);
        menu.add(load);
        EncryptButton encrypt = new EncryptButton(textArea);
        menu.add(encrypt);
        DecryptButton decrypt = new DecryptButton(textArea);
        menu.add(decrypt);        
 
        //Display the window.
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true); 
    }	
	
   /**
   * This is the main method which simply runs the createAndShowGUI() method.
   * 
   * @param args 	unused
   */
	public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
