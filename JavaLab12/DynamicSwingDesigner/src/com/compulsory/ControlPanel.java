package com.compulsory;

import com.bonus.DetailsComponent;
import com.optional.XMLDecoderPanel;
import com.optional.XMLEncoderPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;

/**
 * This class represents a set of options that we can customize to build
 * a Swing graphical user interface.
 */
public class ControlPanel extends JPanel implements Serializable {

    private DesignPanel designPanel;
    @DetailsComponent(Width = 150, Height = 10, Text = "Input", FontSize = 15)
    private JTextField swingComponentName;
    @DetailsComponent(Width = 150, Height = 10, Text = "Input", FontSize = 15)
    private JTextField swingComponentText;
    @DetailsComponent(Height = 10, Text = "save", FontSize = 15)
    private JButton saveButton;
    @DetailsComponent(Height = 10, Text = "load", FontSize = 15)
    private JButton loadButton;
    private JTable table;


    /**
     * In order to create The controlPanel we need to initialize load and save buttons
     */
    public ControlPanel() {
        initLoad();
        initSave();
        init();
    }

    /**
     * This method creates a TextField for any class name of a Swing component we want to create
     * and a default text for that component
     */
    private void init() {
        Annotation singleAnnotation;
        DetailsComponent detailsComponent
        if (swingComponentText.isAnnotationPresent(DetailsComponent.class)) {
            singleAnnotation = swingComponentText.getAnnotation(DetailsComponent.class);
            detailsComponent = (DetailsComponent) singleAnnotation;
            swingComponentName = new JTextField(detailsComponent.Width());
        }
        if (swingComponentName.isAnnotationPresent(DetailsComponent.class)) {
            singleAnnotation = swingComponentName.getAnnotation(DetailsComponent.class);
            detailsComponent = (DetailsComponent) singleAnnotation;
            swingComponentName = new JTextField(detailsComponent.Width());
        }
        table = new JTable();

        this.add(swingComponentName);
        this.add(swingComponentText);
    }

    /**
     * This method initialize the save button
     * When the save button is pressed, this method serializes to XML the design panel
     */
    private void initSave() {
        saveButton = new JButton("Save");
        this.add(saveButton);
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    XMLEncoderPanel.serializeToXML(designPanel);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.err.println("Save error");
                }
            }
        });
    }

    /**
     * This method initialize the save button
     * When the load button is pressed, this method deserializes from XML the design panel
     */
    private void initLoad() {
        loadButton = new JButton("Load");
        this.add(loadButton);
        loadButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    designPanel = XMLDecoderPanel.deserializeFromXML();
                    designPanel.setControlPanel(ControlPanel.this);
                    designPanel.repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.err.println("Load error");
                }
            }
        });
    }


    public String getSwingComponentText() {
        return swingComponentText.getText();
    }

    public String getSwingComponentName() {
        return swingComponentName.getText();
    }

    public void setSwingComponentName(JTextField swingComponentName) {
        this.swingComponentName = swingComponentName;
    }

    public void setDesignPanel(DesignPanel designPanel) {
        this.designPanel = designPanel;

    }

}
