package textEditor;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
 
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SaveButton extends JPanel implements ActionListener { 
	JButton button;
	JTextArea textData;
	 
    public SaveButton(JTextArea textArea) {    	
        super(new BorderLayout());
        textData = textArea;
        button = new JButton("Save");
        add(button, BorderLayout.CENTER);
        button.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
        Writer.save(textData.getText().getBytes());
    }
}
