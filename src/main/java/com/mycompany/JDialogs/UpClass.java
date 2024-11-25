package com.mycompany.JDialogs;

import com.mycompany.DAOs.DAOClassImp;
import com.mycompany.DAOs.DAOTeachersImp;
import com.formdev.flatlaf.FlatClientProperties;
import com.mycompany.models.Teachers;
import com.mycompany.models.Class;

import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class UpClass extends javax.swing.JDialog {
    private int idTeacher;
    private Class clas;
    private JPopupMenu suggestionPopup;

    public UpClass(java.awt.Frame parent, boolean modal, Class clas) {
        super(parent, modal);
        UIManager.put( "Button.arc", 25 );
        initComponents();
        init();
        
        if(clas != null){
            this.clas = clas;
            this.idTeacher = clas.getTeacher().getId();
            fillClassData();
            Title.setText("Modificar Clase");
            button.setText("Modificar");
        }
        else{
            Title.setText("Nueva Clase");
            button.setText("Agregar");
        }
    }
    
    private void init(){
        setResizable(false);
        jPanel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;");
        
        Title.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold +5;");
        
        StyleLebel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
        DayLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
        StartLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
        TeacherLabel.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:bold;");
        
         // DayCombBox
        DayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
        "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"
        }));
        
        //SearchBar
        teacherText.getDocument().addDocumentListener(new DocumentListener() {
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        StyleLebel = new javax.swing.JLabel();
        DayLabel = new javax.swing.JLabel();
        StartLabel = new javax.swing.JLabel();
        TeacherLabel = new javax.swing.JLabel();
        styleText = new javax.swing.JTextField();
        startText = new javax.swing.JTextField();
        teacherText = new javax.swing.JTextField();
        button = new com.mycompany.utils.ButtonAction();
        FinishLabel = new javax.swing.JLabel();
        finishText = new javax.swing.JTextField();
        DayComboBox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 315));

        bg.setBackground(new java.awt.Color(51, 51, 51));
        bg.setPreferredSize(new java.awt.Dimension(300, 315));

        Title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Title.setText("Nueva Clase");

        StyleLebel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StyleLebel.setText("Estilo:");

        DayLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        DayLabel.setText("Dia de semana:");

        StartLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        StartLabel.setText("Inicio:");

        TeacherLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TeacherLabel.setText("Profesor:");

        teacherText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                teacherTextActionPerformed(evt);
            }
        });

        button.setText("Agregar");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });

        FinishLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        FinishLabel.setText("Fin:");

        DayComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(styleText, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DayLabel)
                            .addComponent(StyleLebel)
                            .addComponent(Title)
                            .addComponent(teacherText, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(StartLabel)
                                    .addComponent(TeacherLabel)
                                    .addComponent(startText, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(finishText, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(FinishLabel)))))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Title)
                .addGap(10, 10, 10)
                .addComponent(StyleLebel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(styleText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DayLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartLabel)
                    .addComponent(FinishLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finishText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TeacherLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(teacherText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGap(0, 315, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void teacherTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_teacherTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_teacherTextActionPerformed

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        String style = styleText.getText().trim();
        String day = (String) DayComboBox.getSelectedItem();
        String startTime = startText.getText().trim();
        String finishTime = finishText.getText().trim();


        try{
            DAOClassImp daoClass = new DAOClassImp();
            DAOTeachersImp daoTeacher = new DAOTeachersImp();

            if(clas == null){
                Class newClass = new Class();
                newClass.setStyle(style);
                newClass.setDay(day);
                newClass.setStart_time(startTime);
                newClass.setFinish_time(finishTime);

                Teachers teacher = daoTeacher.selectTeacher(idTeacher);
                newClass.setTeacher(teacher);

                daoClass.register(newClass);

                javax.swing.JOptionPane.showMessageDialog(this,"Clase nueva exitosa","AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE); 
            }
            else{
                clas.setStyle(style);
                clas.setDay(day);
                clas.setStart_time(startTime);
                clas.setFinish_time(finishTime);

                Teachers teacher = daoTeacher.selectTeacher(idTeacher);
                clas.setTeacher(teacher);

                daoClass.modify(clas);
                javax.swing.JOptionPane.showMessageDialog(this,"Modificación Exitosa","AVISO", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
            
            
            
        } catch(Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this,"Ocurrio un error al crear clase nueva","AVISO", javax.swing.JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
        dispose();
        
        
    }//GEN-LAST:event_buttonActionPerformed
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpClass dialog = new UpClass(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JComboBox<String> DayComboBox;
    private javax.swing.JLabel DayLabel;
    private javax.swing.JLabel FinishLabel;
    private javax.swing.JLabel StartLabel;
    private javax.swing.JLabel StyleLebel;
    private javax.swing.JLabel TeacherLabel;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel bg;
    private com.mycompany.utils.ButtonAction button;
    private javax.swing.JTextField finishText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField startText;
    private javax.swing.JTextField styleText;
    private javax.swing.JTextField teacherText;
    // End of variables declaration//GEN-END:variables

    private void showSuggestions() {
        String input = teacherText.getText().trim();
        suggestionPopup.setVisible(false);
        if (input.isEmpty()) {
            return;
        }

        try {
            DAOTeachersImp dao = new DAOTeachersImp();
            List<Teachers> suggestions = dao.list("")
                .stream()
                .filter(teacher -> teacher.getName().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());

            if (suggestions.isEmpty()) {
                return;
            }

            suggestionPopup.removeAll();
            for (Teachers teacher : suggestions) {
                String displayText = teacher.getName() + " - DNI: " + teacher.getDNI();
                JMenuItem menuItem = new JMenuItem(displayText);

                menuItem.addActionListener(e -> {
                    
                    idTeacher = teacher.getId();
                    
                    teacherText.setText(teacher.getName());
                    suggestionPopup.setVisible(false);
                });

                suggestionPopup.add(menuItem);
            }

            suggestionPopup.show(teacherText, 0, teacherText.getHeight());
            suggestionPopup.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error al cargar sugerencias: " + e.getMessage());
        }
    }
    
    private void fillClassData() {
        styleText.setText(clas.getStyle());
        DayComboBox.setSelectedItem(clas.getDay());
        startText.setText(clas.getStart_time());
        finishText.setText(clas.getFinish_time());
        teacherText.setText(clas.getTeacher().getName());
    }
}
