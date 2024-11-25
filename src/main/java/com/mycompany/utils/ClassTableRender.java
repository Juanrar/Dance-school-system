package com.mycompany.utils;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import com.mycompany.models.Class;


public class ClassTableRender implements TableCellRenderer{
    
    private final TableCellRenderer oldCellRenderer;
    
    public ClassTableRender(JTable table){
        oldCellRenderer = table.getDefaultRenderer(Object.class);
    }

@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    if (value instanceof Class) {
        Class data = (Class) value;
        Component com = oldCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        TableCellClass cell = new TableCellClass(data, com.getFont());

        if (isSelected) {
            cell.setBackground(table.getSelectionBackground());
        } else {
            cell.setBackground(table.getBackground());
        }
        return cell;
    }
    return oldCellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
}

    
}
