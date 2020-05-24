package com.compulsory;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the main class of the application.
 * The frame will contain a ControlPanel in the north and a DesignPanel in the center.
 */
public class MainFrame extends JFrame {

    private ControlPanel controlPanel;
    private DesignPanel designPanel;

    public MainFrame(String title) throws HeadlessException {
        super(title);
        setSize(1800, 1000);
        this.setLayout(new BorderLayout());
        init();
    }

    /**
     * This method initializes controlPanel and designPanel and paints them
     */
    private void init() {
        controlPanel = new ControlPanel();
        designPanel = new DesignPanel(controlPanel);
        designPanel.setControlPanel(controlPanel);
        controlPanel.setDesignPanel(designPanel);

        designPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.add(designPanel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);

        this.repaint();

    }

}
