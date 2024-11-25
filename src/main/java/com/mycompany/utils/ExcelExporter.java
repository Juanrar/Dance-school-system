package com.mycompany.utils;

import com.mycompany.DAOs.DAOInscriptionsImp;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.db.Cursor;
import com.mycompany.models.Teachers;
import java.sql.PreparedStatement;

public class ExcelExporter extends Cursor {

public void exportToExcel(Teachers teacher, int month) {
        String fileName = teacher.getName() + " - reporte salario mes " + month + ".xlsx";
        try {
            Connect();

            // Query para obtener los datos de las inscripciones
            String query = "SELECT " +
                           "c.estilo as estilo, " +
                           "DATE(i.fecha_inscripcion) AS fecha_inscripcion, " +
                           "COUNT(*) AS total_inscripciones " +
                           "FROM inscripciones i " +
                           "JOIN clase c ON i.clase_id = c.id " +
                           "WHERE c.profesor_id = ? " +
                           "AND DATE_PART('month', i.fecha_inscripcion) = ? " +
                           "AND DATE_PART('year', i.fecha_inscripcion) = DATE_PART('year', CURRENT_DATE) " +
                           "GROUP BY c.estilo, DATE(i.fecha_inscripcion) " +
                           "ORDER BY DATE(i.fecha_inscripcion) DESC";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, teacher.getId());
            st.setInt(2, month);

            ResultSet rs = st.executeQuery();

            // Crear un libro de trabajo y una hoja
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Reporte");

            // Crear los encabezados
            Row headerRow = sheet.createRow(0);
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(rs.getMetaData().getColumnName(i));
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                cell.setCellStyle(style);
            }

            // Rellenar las filas con los datos del ResultSet
            int rowCount = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowCount++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    Object value = rs.getObject(i);
                    if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            // Llamar a la función calculateSalary para obtener el sueldo final
            DAOInscriptionsImp dao = new DAOInscriptionsImp();
            int salary = dao.calculateSalary(teacher.getId(), month);

            // Agregar la fila del sueldo final
            Row salaryRow = sheet.createRow(rowCount++);
            salaryRow = sheet.createRow(rowCount++);
            Cell salaryCellLabel = salaryRow.createCell(0);
            salaryCellLabel.setCellValue("Remuneración Total");
            Cell salaryCellValue = salaryRow.createCell(1);
            salaryCellValue.setCellValue(salary);

            // Ajustar el ancho de las columnas
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ruta donde se guardará el archivo
            String desktopPath = "C:/Users/juanc/OneDrive/Escritorio/";
            String filePath = desktopPath + fileName;

            // Escribir el archivo Excel
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            System.out.println("Archivo Excel generado con éxito en: " + filePath);


            // Cerrar recursos
            rs.close();
            st.close();
            Close();
            
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
