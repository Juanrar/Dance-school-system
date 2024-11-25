package com.mycompany.utils;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Font;
import com.mycompany.models.Class;

public class TableCellClass extends javax.swing.JPanel {
    private Class data;

    public TableCellClass(Class data, Font font) {
        this.data = data;
        initComponents();
        scheduleLebel.setFont(font);
        scheduleLebel.setText(data.getStart_time()+ " - " +data.getFinish_time());
        styleTeacherLebel.putClientProperty(FlatClientProperties.STYLE, ""
                + "foreground:$Label.disabledForeground");
        styleTeacherLebel.setFont(font);
        styleTeacherLebel.setText(data.getStyle()+ " · " +data.getTeacher().getName());

        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scheduleLebel = new javax.swing.JLabel();
        styleTeacherLebel = new javax.swing.JLabel();

        scheduleLebel.setForeground(new java.awt.Color(255, 255, 255));
        scheduleLebel.setText("INICIO - FIN");

        styleTeacherLebel.setText("Estilo · Profesor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(styleTeacherLebel, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(scheduleLebel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scheduleLebel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(styleTeacherLebel)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public Class getData() {
        return data;
    }
    
    public String getTitle(){
        return styleTeacherLebel.getText();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel scheduleLebel;
    private javax.swing.JLabel styleTeacherLebel;
    // End of variables declaration//GEN-END:variables
}
