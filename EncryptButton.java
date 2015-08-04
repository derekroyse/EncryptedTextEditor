package textEditor;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class EncryptButton extends JPanel implements ActionListener { 
	JButton button;
	JTextArea textData;
	 
    public EncryptButton(JTextArea textArea) {    	
        super(new BorderLayout());
        textData = textArea;
        button = new JButton("Encrypt");
        add(button, BorderLayout.CENTER);
        button.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e) {
				try {
					Writer.encrypt(textData.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

    }
}
