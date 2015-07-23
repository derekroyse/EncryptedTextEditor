package textEditor;

import java.awt.*;

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
