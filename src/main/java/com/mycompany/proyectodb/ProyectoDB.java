package com.mycompany.proyectodb;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import java.awt.Font;
import javax.swing.UIManager;

public class ProyectoDB {

    public static void main(String[] args){
        FlatRobotoFont.install();
        FlatDarkFlatIJTheme.setup();
        UIManager.put("defaultFont",new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        
            // Configuración adicional para ajustar componentes
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Button.arc", 25);
        
        com.mycompany.proyectodb.Dashboard objectDashboard = new Dashboard();
        objectDashboard.setVisible(true);
        
    }
}
