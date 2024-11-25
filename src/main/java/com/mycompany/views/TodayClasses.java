package com.mycompany.views;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.mycompany.interfaces.DAOClass;
import javax.swing.UIManager;
import com.mycompany.DAOs.DAOClassImp;
import com.mycompany.proyectodb.Dashboard;
import com.mycompany.utils.TableHeaderAlignment;
import javax.swing.table.DefaultTableModel;
import com.mycompany.utils.ClassTableRender;
import java.time.LocalDate;
import javax.swing.SwingUtilities;

public class TodayClasses extends javax.swing.JPanel {

    public TodayClasses() {
        FlatDarkFlatIJTheme.setup();
        UIManager.put( "Button.arc", 25 );
        initComponents();
        Init();
        loadTodayClasses();
    }
    
    private void Init() {
        TitleLebel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        
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
        table.getColumnModel().getColumn(1).setCellRenderer(new ClassTableRender(table));
        table.setRowHeight(50);

    }
    
    private void loadTodayClasses() {
        LocalDate now = LocalDate.now();
        String[] diasSemana = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        String diaSemana = diasSemana[now.getDayOfWeek().getValue() - 1];
        try{
           DAOClass dao = new DAOClassImp();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
           dao.list(diaSemana).forEach((u)-> model.addRow(new Object[]
               {u.getId(),
               u}));
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
        addButton = new com.mycompany.utils.ButtonAction();
        TitleLebel = new javax.swing.JLabel();

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
                "#", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
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

        addButton.setBackground(new java.awt.Color(51, 51, 51));
        addButton.setText("Detalle");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        TitleLebel.setForeground(new java.awt.Color(255, 255, 255));
        TitleLebel.setText("Clases de hoy");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(TitleLebel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TitleLebel)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        int selectedRow = table.getSelectedRow();
        Dashboard parentFrame = (Dashboard) SwingUtilities.getWindowAncestor(this);
        if (selectedRow >= 0) {
            int classId = (int) table.getValueAt(selectedRow, 0); 
            parentFrame.ShowJPanel(new ClassDetail(classId));
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No has seleccionado ninguna clase.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_addButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TitleLebel;
    private com.mycompany.utils.ButtonAction addButton;
    private javax.swing.JPanel bg;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
    
}
