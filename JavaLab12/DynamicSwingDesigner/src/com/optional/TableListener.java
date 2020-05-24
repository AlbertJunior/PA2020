package com.optional;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

/**
 * This class represents the listener from table model
 * It helps us to change properties from JComponents from the designPanel from the table
 */
public class TableListener implements TableModelListener {

    private List<ComponentInstance> componentsList;

    public TableListener(List<ComponentInstance> componentsList) {
        this.componentsList = componentsList;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel) e.getSource();
        Object data = model.getValueAt(row, column);
        checkProperties(row, column, model, data);
    }

    /**
     * This method allows us to change 4 properties for a JComponent from the canvas
     * @param row
     * @param column
     * @param model
     * @param data
     */
    private void checkProperties(int row, int column, TableModel model, Object data) {
        if (model.getValueAt(row, column - 1) == "font") {
            componentsList.get(componentsList.size() - 1).getComponent().setFont(new Font(null, 10, (Integer) data));
        } else if (model.getValueAt(row, column - 1) == "x") {
            componentsList.get(componentsList.size() - 1).getComponent().setAlignmentX((Integer) data);
        } else if (model.getValueAt(row, column - 1) == "y") {
            componentsList.get(componentsList.size() - 1).getComponent().setAlignmentY((Integer) data);
        } else if (model.getValueAt(row, column - 1) == "size") {
            componentsList.get(componentsList.size() - 1).getComponent().setSize((Integer) data, (Integer) data);
        }
    }

}
