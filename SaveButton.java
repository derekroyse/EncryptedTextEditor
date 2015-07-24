package textEditor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
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
