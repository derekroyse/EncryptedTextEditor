package textEditor;

import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class TextEditor extends JPanel{

	private static void createAndShowGUI() {
        // Window setup
        JFrame frame = new JFrame("Simple Text Editor");
        JPanel panel = new JPanel(new GridLayout(1, 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Menu Setup
        JMenuBar menuBar;
        JMenu menu;
        

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("File");
        menuBar.add(menu);
        
        // Text Area
        JTextArea textArea = new JTextArea(20, 50);
        textArea.setLineWrap(true); 
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);
        
        // Save Menu Option
        SaveButton save = new SaveButton(textArea);
        menu.add(save);
        
        // Load Menu Option
        LoadButton load = new LoadButton(textArea);
        menu.add(load);
        
        // Encrypt Menu Option
        EncryptButton encrypt = new EncryptButton(textArea);
        menu.add(encrypt);
        
        // Decrypt Menu Option
        DecryptButton decrypt = new DecryptButton(textArea);
        menu.add(decrypt);
        
 
        //Display the window.
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true); 
    }	

	public static void main(String[] args) {		
        //Schedule a job for the event-dispatching thread
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
}
