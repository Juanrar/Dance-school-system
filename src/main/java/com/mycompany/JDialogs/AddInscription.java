package com.mycompany.JDialogs;

import com.formdev.flatlaf.FlatClientProperties;

import com.mycompany.interfaces.DAOStudents;
import com.mycompany.models.Packs;
import com.mycompany.models.Pays;
import com.mycompany.models.Students;
import com.mycompany.models.Class;
import com.mycompany.models.Inscriptions;
import com.mycompany.DAOs.DAOClassImp;
import com.mycompany.DAOs.DAOInscriptionsImp;
import com.mycompany.DAOs.DAOPacksImp;
import com.mycompany.DAOs.DAOPaysImp;
import com.mycompany.DAOs.DAOStudentsImp;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddInscription extends javax.swing.JDialog {
    private int idClass;
    private int idStudent;
    private JPopupMenu suggestionPopup;

    public AddInscription(java.awt.Frame parent, boolean modal, int idClass) {
        super(parent, modal);
        UIManager.put( "Button.arc", 25 );
        initComponents();
        init();
        this.idClass = idClass;
        
    }
    
    private void init(){
        setResizable(false);
        jPanel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;");
        
        Title.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        
        NameLebel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
        PackLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
               
        //SearchBar
        nameText.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            showSuggestions();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            showSuggestions();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            showSuggestions();
        }
        });
        
        suggestionPopup = new JPopupMenu();
        suggestionPopup.setFocusable(false);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        NameLebel = new javax.swing.JLabel();
        PackLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        button = new com.mycompany.utils.ButtonAction();
        comboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 200));

        bg.setBackground(new java.awt.Color(51, 51, 51));
        bg.setPreferredSize(new java.awt.Dimension(300, 200));

        Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Title.setText("Añadir Estudiante");

        NameLebel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NameLebel.setText("Nombre:");

        PackLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        PackLabel.setText("Pack:");

        nameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextActionPerformed(evt);
            }
        });

        button.setText("Agregar");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PackLabel)
                            .addComponent(NameLebel)
                            .addComponent(Title)
                            .addComponent(nameText, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6))
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addGap(10, 10, 10)
                .addComponent(NameLebel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PackLabel)
                .addGap(4, 4, 4)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        int index = comboBox.getSelectedIndex();
        String packName = comboBox.getItemAt(index);

        DAOPacksImp daoPack = new DAOPacksImp();
        DAOPaysImp daoPay = new DAOPaysImp();
        DAOStudentsImp daoStudent = new DAOStudentsImp();
        DAOClassImp daoClass = new DAOClassImp();
        DAOInscriptionsImp daoInscription = new DAOInscriptionsImp();

        try {
            Class clas = daoClass.selectClass(this.idClass);
            Pays pay;
            Packs pack;
            int payId;

            if ("Clase individual".equals(packName)) {
                pack = daoPack.selectPack(1);
                Students student = daoStudent.selectStudent(idStudent);

                pay = new Pays();
                pay.setStudent(student);
                pay.setPack(pack);
                daoPay.register(pay);

                payId = daoPay.getPayForStudent(idStudent, pack.getId());
                pay.setId(payId);

            } else {
                int packId = daoPack.getPackIdByName(packName);

                payId = daoPay.getPayForStudent(idStudent, packId);
                pay = daoPay.selectPay(payId);
            }

            Inscriptions newInscription = new Inscriptions();
            newInscription.setClas(clas);
            newInscription.setPay(pay);

            daoInscription.register(newInscription);
            daoPay.decrementRemainingClasses(payId);
            javax.swing.JOptionPane.showMessageDialog(this,"Inscripción Exitosa","AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE); 
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Ocurrio un error al hacer una inscripción","AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        dispose();
    }//GEN-LAST:event_buttonActionPerformed

    private void nameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextActionPerformed

    }//GEN-LAST:event_nameTextActionPerformed

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged

    }//GEN-LAST:event_comboBoxItemStateChanged

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
     
    }//GEN-LAST:event_comboBoxActionPerformed
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddInscription dialog = new AddInscription(new javax.swing.JFrame(), true, 0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameLebel;
    private javax.swing.JLabel PackLabel;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel bg;
    private com.mycompany.utils.ButtonAction button;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameText;
    // End of variables declaration//GEN-END:variables

    private void showSuggestions() {
        String input = nameText.getText().trim();
        suggestionPopup.setVisible(false);
        if (input.isEmpty()) {
            return;
        }

        try {
            DAOStudents dao = new DAOStudentsImp();
            List<Students> suggestions = dao.list("")
                .stream()
                .filter(student -> student.getName().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());

            if (suggestions.isEmpty()) {
                return;
            }

            suggestionPopup.removeAll();
            for (Students student : suggestions) {
                String displayText = student.getName() + " - DNI: " + student.getDNI();
                JMenuItem menuItem = new JMenuItem(displayText);

                menuItem.addActionListener(e -> {

                    idStudent = student.getId();

                    nameText.setText(student.getName());
                    loadPacks();
                    suggestionPopup.setVisible(false);
                });

                suggestionPopup.add(menuItem);
            }

            suggestionPopup.show(nameText, 0, nameText.getHeight());
            suggestionPopup.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al cargar sugerencias: " + e.getMessage());
        }
    }

    private void loadPacks() {
        try {
            comboBox.removeAllItems();
            comboBox.addItem("Clase individual");
            
            DAOPaysImp dao = new DAOPaysImp();
            dao.activePayList(idStudent).forEach((u) -> comboBox.addItem(u.getPack().getName()));

        } catch (Exception e) {
            System.out.println("Error cargando packs: " + e.getMessage());
        }
    }
}
