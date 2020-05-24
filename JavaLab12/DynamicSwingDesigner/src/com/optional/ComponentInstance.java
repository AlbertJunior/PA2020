package com.optional;

import javax.swing.*;
import java.io.Serializable;

/**
 * This class represents a Component from designPanel
 */
public class ComponentInstance implements Serializable {
    private int x;
    private int y;
    private JComponent component;

    /**
     * We need to specify the JComponent and its position
     *
     * @param component
     * @param x
     * @param y
     */
    public ComponentInstance(JComponent component, int x, int y) {
        this.component = component;
        this.x = x;
        this.y = y;
    }

    public ComponentInstance() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }
}
