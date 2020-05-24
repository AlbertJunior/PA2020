package com.bonus;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * This class represents the custom button that we need to use in the design, dynamically loading its class from the external jar.
 */
public class AmazingButton extends JButton {

    /**
     * This class initialize an Amazing Button, but the properties will be changed in the other application
     *
     * @param text
     */
    public AmazingButton(String text) {
        AbstractBorder brdrLeft = new TextBubbleBorder(Color.BLACK, 2, 16, 16);
        this.setText(text);
        this.setBorder(brdrLeft);
    }
}
