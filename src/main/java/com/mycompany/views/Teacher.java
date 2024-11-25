package com.mycompany.views;

import com.mycompany.JDialogs.UpTeacher;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import javax.swing.UIManager;
import com.mycompany.interfaces.DAOTeachers;
import com.mycompany.models.Teachers;
import com.mycompany.DAOs.DAOTeachersImp;
import com.mycompany.proyectodb.Dashboard;
import com.mycompany.utils.TableHeaderAlignment;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Teacher extends javax.swing.JPanel {

    public Teacher() {
        FlatDarkFlatIJTheme.setup();
        UIManager.put( "Button.arc", 25 );
        initComponents();
        Init();
        LoadTeachers();

    }
    
    private void Init() {
        bg.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;");
        
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;"
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        
        table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));
        
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Buscar...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("images/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                );
        
        //SearchBar
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            searchTeacher();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            searchTeacher();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            searchTeacher();
        }
    });
    }
    
    private void LoadTeachers(){
        try{
           DAOTeachers dao = new DAOTeachersImp();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
           dao.list("").forEach((u)-> model.addRow(new Object[]
               {u.getId(),
               u.getName(), 
               u.getEmail(), 
               u.getDNI(), 
               u.getPhone_number()}));
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        deleteButton = new com.mycompany.utils.ButtonAction();
        modifyButton = new com.mycompany.utils.ButtonAction();
        newButton = new com.mycompany.utils.ButtonAction();
        historyButton = new com.mycompany.utils.ButtonAction();

        setBackground(new java.awt.Color(51, 51, 51));
        setForeground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(820, 570));

        bg.setBackground(new java.awt.Color(76, 76, 76));
        bg.setPreferredSize(new java.awt.Dimension(820, 570));

        scroll.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Nombre", "Numero", "DNI", "Telefono"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFocusable(false);
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        txtSearch.setBackground(new java.awt.Color(51, 51, 51));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbTitle.setForeground(new java.awt.Color(255, 255, 255));
        lbTitle.setText("PROFESORES");

        deleteButton.setBackground(new java.awt.Color(51, 51, 51));
        deleteButton.setText("Borrar");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        modifyButton.setBackground(new java.awt.Color(51, 51, 51));
        modifyButton.setText("Modificar");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        newButton.setBackground(new java.awt.Color(51, 51, 51));
        newButton.setText("Nuevo");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        historyButton.setBackground(new java.awt.Color(51, 51, 51));
        historyButton.setText("Historial");
        historyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTitle)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(historyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchTeacher() {                                          
        try{
           String studentSeaching = txtSearch.getText();
           DAOTeachers dao = new DAOTeachersImp();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
           dao.list(studentSeaching).forEach((u)-> model.addRow(new Object[]
               {u.getId(),
               u.getName(), 
               u.getEmail(), 
               u.getDNI(), 
               u.getPhone_number()}));
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }  
    
    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        //reemplazado
    }//GEN-LAST:event_txtSearchActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
    int[] selectedRows = table.getSelectedRows();
    if (selectedRows.length > 0) {
        String message;
        if (selectedRows.length == 1) {
            message = "¿Estás seguro de que deseas eliminar al profesor seleccionado?";
        } else {
            message = "¿Estás seguro de que deseas eliminar a los " + selectedRows.length + " profesores seleccionados?";
        }

        int confirmation = JOptionPane.showConfirmDialog(
            this, 
            message, 
            "Confirmación de eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        // Si el usuario confirma, proceder con la eliminación
        if (confirmation == JOptionPane.YES_OPTION) {
            DAOTeachers dao = new DAOTeachersImp();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            
            // Eliminar las filas desde la última seleccionada hasta la primera
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                try {
                    int rowIndex = selectedRows[i];
                    Object value = table.getValueAt(rowIndex, 0);
                    int userId;
                    
                    // Verificar si el valor es un String y convertirlo a entero, si es necesario
                    if (value instanceof String) {
                        userId = Integer.parseInt((String) value);
                    } else if (value instanceof Integer) {
                        userId = (Integer) value; // Si ya es un Integer
                    } else {
                        throw new Exception("Valor no válido para userId en la fila " + rowIndex);
                    }
                    
                    dao.delete(userId); 
                    model.removeRow(rowIndex);

                } catch (Exception e) {
                    System.out.println("Error al eliminar profesor: " + e.getMessage());
                }
            }


        }
    } else {
        // Si no se ha seleccionado ninguna fila, mostrar un mensaje de advertencia
        JOptionPane.showMessageDialog(
            this, 
            "No has seleccionado ningún profesor para eliminar.", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE
        );
    }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
        int selectedRow = table.getSelectedRow();
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (selectedRow >= 0) {
            Teachers wanted = new Teachers();
            wanted.setId((int) table.getValueAt(selectedRow, 0));
            wanted.setName((String)table.getValueAt(selectedRow, 1));
            wanted.setEmail((String)table.getValueAt(selectedRow, 2));
            wanted.setDNI((String)table.getValueAt(selectedRow, 3));
            wanted.setPhone_Number((String)table.getValueAt(selectedRow, 4));           

            UpTeacher create = new UpTeacher(parentFrame, true, wanted);
            create.setLocationRelativeTo(parentFrame);
            create.setVisible(true);
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No has seleccionado ningún estudiante para modificar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        LoadTeachers();
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        UpTeacher create = new UpTeacher(parentFrame, true, null);
        create.setLocationRelativeTo(parentFrame);
        create.setVisible(true);
        LoadTeachers();
    }//GEN-LAST:event_newButtonActionPerformed

    private void historyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyButtonActionPerformed
        int selectedRow = table.getSelectedRow();
        Dashboard parentFrame = (Dashboard) SwingUtilities.getWindowAncestor(this);
        if (selectedRow >= 0) {
            Teachers wanted = new Teachers();
            wanted.setId((int) table.getValueAt(selectedRow, 0));
            wanted.setName((String)table.getValueAt(selectedRow, 1));
            wanted.setEmail((String)table.getValueAt(selectedRow, 2));
            wanted.setDNI((String)table.getValueAt(selectedRow, 3));
            wanted.setPhone_Number((String)table.getValueAt(selectedRow, 4));           

            parentFrame.ShowJPanel(new HistoryTeacher(wanted));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No has seleccionado ningún Profesor para ver el historial.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_historyButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private com.mycompany.utils.ButtonAction deleteButton;
    private com.mycompany.utils.ButtonAction historyButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private com.mycompany.utils.ButtonAction modifyButton;
    private com.mycompany.utils.ButtonAction newButton;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
