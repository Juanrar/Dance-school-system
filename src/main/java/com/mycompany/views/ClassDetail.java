package com.mycompany.views;

import com.mycompany.JDialogs.AddInscription;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import javax.swing.UIManager;
import com.mycompany.interfaces.DAOTeachers;
import com.mycompany.models.Inscriptions;
import com.mycompany.DAOs.DAOClassImp;
import com.mycompany.DAOs.DAOInscriptionsImp;
import com.mycompany.DAOs.DAOPaysImp;
import com.mycompany.DAOs.DAOTeachersImp;
import com.mycompany.utils.TableHeaderAlignment;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClassDetail extends javax.swing.JPanel {
    private int classId;

    public ClassDetail(int id) {
        FlatDarkFlatIJTheme.setup();
        classId = id;
        UIManager.put( "Button.arc", 25 );
        initComponents();
        Init();
        LoadAssists();
        CountAssists();
    }

    private void Init() {
        SetTitle();
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
    
    private void LoadAssists(){
        try{
           DAOInscriptionsImp dao = new DAOInscriptionsImp();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
           dao.list(classId).forEach((u)-> model.addRow(new Object[]
               {u.getId(),
                u.getPay().getStudent().getName(),
                u.getPay().getPack().getName()}));
        }catch(Exception e){
            System.out.println("Error en la funcion LoadAssist() en ClassDetail -> "+ e.getMessage());
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
        lbAssist = new javax.swing.JLabel();
        addButton = new com.mycompany.utils.ButtonAction();
        DeleteButton = new com.mycompany.utils.ButtonAction();

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
                "#", "Nombre", "Pack"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
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
        lbTitle.setText("NOMBRE DE LA CLASE");

        lbAssist.setText("Asistencias:");

        addButton.setBackground(new java.awt.Color(51, 51, 51));
        addButton.setText("Agregar");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        DeleteButton.setBackground(new java.awt.Color(51, 51, 51));
        DeleteButton.setText("Borrar");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
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
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(lbTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbAssist))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTitle)
                    .addComponent(lbAssist))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        AddInscription create = new AddInscription(parentFrame, true, classId);
        create.setLocationRelativeTo(parentFrame);
        create.setVisible(true);
        LoadAssists();
        CountAssists();
    }//GEN-LAST:event_addButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "No has seleccionado ningún pago para modificar.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        int inscriptionId = (int) table.getValueAt(selectedRow, 0);

        try {
            DAOInscriptionsImp daoInscription = new DAOInscriptionsImp();
            DAOPaysImp daoPay = new DAOPaysImp();

            Inscriptions inscription = daoInscription.selectInscription(inscriptionId);
            if (inscription != null) {
                daoInscription.delete(inscriptionId);
                daoPay.incrementRemainingClasses(inscription.getPay().getId());

                if (inscription.getPay().getPack().getName().equals("Clase individual")) {
                    daoPay.delete(inscription.getPay().getId());
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ClassDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        LoadAssists();
        CountAssists();
    }//GEN-LAST:event_DeleteButtonActionPerformed
    
    private void SetTitle(){
        try{
           DAOClassImp dao = new DAOClassImp();
           String title = dao.getClassTitle(classId);
           lbTitle.setText(title);
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }
    
    private void CountAssists(){
        DAOInscriptionsImp dao = new DAOInscriptionsImp();
        try {
            int assists = dao.countInscriptions(classId);
            lbAssist.setText("Asistencias: "+ assists);
        } catch (Exception ex) {
            Logger.getLogger(ClassDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.utils.ButtonAction DeleteButton;
    private com.mycompany.utils.ButtonAction addButton;
    private javax.swing.JPanel bg;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbAssist;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
