package com.compulsory;

import com.bonus.DetailsComponent;
import com.optional.ComponentInstance;
import com.optional.MyTableModel;
import com.optional.TableListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the canvas with all JComponents
 */
@DetailsComponent(Width = 1000, Height = 500)
public class DesignPanel extends JPanel implements Serializable {

    private List<ComponentInstance> componentsList;
    private ControlPanel controlPanel;
    private int x;
    private int y;
    private String componentType;
    private JTable table;

    /**
     * On order to create the designPanel we need to initialize the control panel
     * and JTable with all details for JComponents
     *
     * @param controlPanel
     */
    public DesignPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
        this.table = new JTable(new MyTableModel());
        setTheTable();
        init();
    }

    public DesignPanel() {
    }

    /**
     * This method initialize the JTable with properties
     */
    private void setTheTable() {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane = new JScrollPane(table);
        add(pane, BorderLayout.CENTER);
        table.getModel().addTableModelListener(new TableListener(componentsList));
    }


    /**
     * This method sets a mouse Listener for the entire canvas
     * In order to draw at the mouse coordinates
     */
    private void init() {
        componentsList = new ArrayList<>();
        this.add(table, BorderLayout.WEST);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                String componentType = controlPanel.getSwingComponentName();
                String componentText = controlPanel.getSwingComponentText();
                try {
                    Class clazz = Class.forName(componentType);
                    JComponent draw = (JComponent) clazz.getConstructor(String.class).newInstance(componentText);
                    addListener(draw);
                    componentsList.add(new ComponentInstance(draw, x, y));
                    repaint();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException E) {
                    System.err.println(E);
                }
                repaint();
            }
        });
    }

    /**
     * This method adds a listener to JTable to automatically change the properties for JComponents
     *
     * @param draw
     */
    private void addListener(JComponent draw) {
        addListener(draw);
        draw.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                try {
                    DesignPanel.this.updateJTable(draw);
                } catch (IllegalAccessException | IntrospectionException | NoSuchFieldException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    /**
     * This method is called after every repaint.
     * User can see every change in the GUI application
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font(null, 0, 100));
        drawComponents(g);
        repaint();
    }


    /**
     * We need to draw every time all the JComponents again
     * If we want to add an Undo button, in this way it's very easy
     * We just need to erase the last component from the listComponents
     *
     * @param g
     */
    private void drawComponents(Graphics g) {
        for (ComponentInstance component : componentsList) {
            component.getComponent().setBounds(component.getX(), component.getY(), 400, 100);
            component.getComponent().setFont(new Font("Arial", Font.PLAIN, 40));
            component.getComponent().setVisible(true);
            this.add(component.getComponent());
        }
    }

    /**
     * When we click on a JComponent from the canvas the JTable automatically update its fields
     *
     * @param draw
     * @throws IllegalAccessException
     * @throws IntrospectionException
     * @throws NoSuchFieldException
     */
    public void updateJTable(JComponent draw) throws IllegalAccessException, IntrospectionException, NoSuchFieldException {
        int i = 0;
        BeanInfo info = Introspector.getBeanInfo(draw.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getValue(pd.getName()));
            table.getModel().setValueAt(String.valueOf(pd.getPropertyType()), i, 0);
            table.getModel().setValueAt(String.valueOf(pd.getName()), i, 1);
            i++;
        }
        JButton button = (JButton) draw;
        button.setText(controlPanel.getSwingComponentText());
    }


    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public List<ComponentInstance> getComponentsList() {
        return componentsList;
    }

    public void setComponentsList(List<ComponentInstance> componentsList) {
        this.componentsList = componentsList;
    }
}
