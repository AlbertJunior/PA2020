package com.optional;

import javax.swing.table.AbstractTableModel;

/**
 * This class represents the table model
 * It helps us to Change a editable field, and set the editable fields
 */
public class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Field", "Value"};
    private Object[][] data;

    public MyTableModel() {
        this.data = new Object[1000][2];
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }


    public boolean isCellEditable(int row, int col) {
        if (col > 2) {
            return false;
        }
        return true;
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}