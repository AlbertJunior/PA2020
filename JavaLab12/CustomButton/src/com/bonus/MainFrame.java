package com.bonus;

import javax.swing.*;
import java.awt.*;

/**
 * This is the main class for this application
 */
public class MainFrame extends JFrame {

    /**
     * This method set an AmazingButton to JFrame to see if it works
     *
     * @param title
     * @throws HeadlessException
     */
    public MainFrame(String title) throws HeadlessException {
        super(title);
        setSize(1800, 1000);
        this.setLayout(new BorderLayout());
        AmazingButton amazingButton = new AmazingButton("Ana");
        amazingButton.setBounds(100, 100, 100, 100);
        amazingButton.setText("Hello");
        this.add(amazingButton);

    }
}
