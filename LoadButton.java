package textEditor;

import java.awt.*;

import javax.swing.*;
import javax.swing.JTextArea;
import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class LoadButton extends JPanel implements ActionListener { 
	JButton button;
	JTextArea textData;
	 
    public LoadButton(JTextArea textArea) {    	
        super(new BorderLayout());
        textData = textArea;
        button = new JButton("Load");
        add(button, BorderLayout.CENTER);
        button.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
        try {
        	textData.setText(Reader.load());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
}
