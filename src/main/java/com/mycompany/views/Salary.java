package com.mycompany.views;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import com.mycompany.DAOs.DAOClassImp;
import com.mycompany.DAOs.DAOInscriptionsImp;
import javax.swing.UIManager;
import com.mycompany.DAOs.DAOTeachersImp;
import com.mycompany.models.Teachers;
import com.mycompany.utils.ExcelExporter;
import com.mycompany.utils.TableHeaderAlignment;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Salary extends javax.swing.JPanel {
    private int currentMonth;

    public Salary() {
        FlatDarkFlatIJTheme.setup();
        UIManager.put( "Button.arc", 25 );
        initComponents();
        Init();
        LoadSalary();
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
        
        // Configurar los meses en el ComboBox
        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = LocalDate.of(2023, i + 1, 1)
                    .getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.getDefault());
        }
        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(months));

        // Seleccionar el mes actual
        currentMonth = LocalDate.now().getMonthValue();
        monthComboBox.setSelectedIndex(currentMonth - 1);// `getMonthValue` devuelve 1-12
        
        monthComboBox.addActionListener(e -> {
            currentMonth = monthComboBox.getSelectedIndex() + 1;
            LoadSalary();
        });
        
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
    
    private void LoadSalary(){
        try{
           DAOTeachersImp daoTeacher = new DAOTeachersImp();
           DAOClassImp daoClass = new DAOClassImp();
           DAOInscriptionsImp daoInscription = new DAOInscriptionsImp();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
           daoTeacher.list("").forEach((u)-> {
               try {
                   model.addRow(new Object[]
                   {u.getId(),
                       u.getName(),
                       u.getPhone_number(),
                       daoClass.countClassesByTeacher(u.getId()),
                       daoInscription.calculateSalary(u.getId(), currentMonth)
                   });
               } catch (Exception ex) {
                   Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
               }
           });
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
        reportButton = new com.mycompany.utils.ButtonAction();
        monthComboBox = new javax.swing.JComboBox<>();

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
                "#", "Nombre", "Telefono", "Clases x semana", "Salario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        lbTitle.setText("EGRESOS");

        reportButton.setBackground(new java.awt.Color(51, 51, 51));
        reportButton.setText("Generar Reporte");
        reportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportButtonActionPerformed(evt);
            }
        });

        monthComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(reportButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
           String teacherSearching = txtSearch.getText();
           DAOTeachersImp daoTeacher = new DAOTeachersImp();
           DAOClassImp daoClass = new DAOClassImp();
           DAOInscriptionsImp daoInscription = new DAOInscriptionsImp();
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setRowCount(0);
           daoTeacher.list(teacherSearching).forEach((u)-> {
               try {
                   model.addRow(new Object[]
                   {u.getId(),
                       u.getName(),
                       u.getPhone_number(),
                       daoClass.countClassesByTeacher(u.getId()),
                       daoInscription.calculateSalary(u.getId(), currentMonth)
                   });
               } catch (Exception ex) {
                   Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
               }
           });
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
    }  
    
    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        //reemplazado
    }//GEN-LAST:event_txtSearchActionPerformed

    private void reportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportButtonActionPerformed
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int teacherId = (int) table.getValueAt(selectedRow, 0);
            DAOTeachersImp dao = new DAOTeachersImp();
            Teachers teacher = null;
            try {
                teacher = dao.selectTeacher(teacherId);
            } catch (Exception ex) {
                Logger.getLogger(Salary.class.getName()).log(Level.SEVERE, null, ex);
            }
            ExcelExporter exporter = new ExcelExporter();
            exporter.exportToExcel(teacher, currentMonth);
            javax.swing.JOptionPane.showMessageDialog(this,"Creacion de reporte exitosa","AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);

        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "No has seleccionado ningún profesor.", "Advertencia", javax.swing.JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_reportButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JComboBox<String> monthComboBox;
    private com.mycompany.utils.ButtonAction reportButton;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
