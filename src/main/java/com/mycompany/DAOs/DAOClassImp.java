package com.mycompany.DAOs;

import com.mycompany.db.Cursor;
import com.mycompany.interfaces.DAOClass;
import com.mycompany.interfaces.DAOTeachers;
import com.mycompany.models.Class;
import com.mycompany.models.Teachers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DAOClassImp extends Cursor implements DAOClass{

    @Override
    public void register(Class cl) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO Clase (estilo, dia_semana, horario_inicio, horario_fin, profesor_id) VALUES(?,?,?,?,?);");
            st.setString(1, cl.getStyle());
            st.setString(2, cl.getDay());
            LocalDate defaultDate = LocalDate.now();
        
            // Convierte las horas a LocalTime y luego a LocalDateTime
            LocalDateTime startTime = LocalDateTime.of(defaultDate, LocalTime.parse(cl.getStart_time()));
            LocalDateTime finishTime = LocalDateTime.of(defaultDate, LocalTime.parse(cl.getFinish_time()));

            st.setTimestamp(3, Timestamp.valueOf(startTime));
            st.setTimestamp(4, Timestamp.valueOf(finishTime));
            st.setInt(5, cl.getTeacher().getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }

    @Override
    public void modify(Class cl) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("UPDATE Clase SET estilo = ?, dia_semana = ?, horario_inicio = ?, horario_fin = ?, profesor_id = ? WHERE id = ?;");
            st.setString(1, cl.getStyle());
            st.setString(2, cl.getDay());
            LocalDate defaultDate = LocalDate.now();
        
            // Convierte las horas a LocalTime y luego a LocalDateTime
            LocalDateTime startTime = LocalDateTime.of(defaultDate, LocalTime.parse(cl.getStart_time()));
            LocalDateTime finishTime = LocalDateTime.of(defaultDate, LocalTime.parse(cl.getFinish_time()));

            st.setTimestamp(3, Timestamp.valueOf(startTime));
            st.setTimestamp(4, Timestamp.valueOf(finishTime));
            st.setInt(5, cl.getTeacher().getId());
            st.setInt(6, cl.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }

    @Override
    public void delete(int idClass) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM Clase WHERE id = ?;");
            st.setInt(1, idClass);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Close();
        }  
    }

    @Override
    public List<Class> list(String diaSemana) throws Exception {
        List<Class> list = null;
        try{
            this.Connect();
            String query = null;
                    
            if (diaSemana == null) {
                query = "select id, estilo, dia_semana, TO_CHAR(horario_inicio, 'HH24:MI') as horario_inicio, "
                      + "TO_CHAR(horario_fin, 'HH24:MI') as horario_fin, profesor_id from clase";
            } else {
                query = "SELECT id, estilo, dia_semana, TO_CHAR(horario_inicio, 'HH24:MI') AS horario_inicio, "
                      + "TO_CHAR(horario_fin, 'HH24:MI') AS horario_fin, profesor_id "
                      + "FROM clase "
                      + "WHERE dia_semana = ? "
                      + "ORDER BY horario_inicio ASC";

            }

            PreparedStatement st = this.conn.prepareStatement(query);
            
            if (diaSemana != null) {
                st.setString(1, diaSemana);
            }
            
            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            DAOTeachers dao = new DAOTeachersImp();
            while(rs.next()){
                Class clas = new Class();
                clas.setId(rs.getInt("id"));
                clas.setStyle(rs.getString("estilo"));
                clas.setDay(rs.getString("dia_semana"));
                clas.setStart_time(rs.getString("horario_inicio"));
                clas.setFinish_time(rs.getString("horario_fin"));
                Teachers teacher = dao.selectTeacher(rs.getInt("profesor_id"));
                clas.setTeacher(teacher);
                list.add(clas);
            }
            rs.close();
            st.close();            
        } catch(Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return list;
    }
    
    public String getClassTitle(int idClass) throws Exception {
        String title = null;
        try {
            this.Connect();
            String query = "SELECT clase.estilo, profesores.nombre " +
                           "FROM clase " +
                           "JOIN profesores ON clase.profesor_id = profesores.id " +
                           "WHERE clase.id = ?";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, idClass);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String estilo = rs.getString("estilo");
                String nombreProfesor = rs.getString("nombre");
                title = estilo + " · " + nombreProfesor;
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return title;
    }
    
    public Class selectClass(int classId) throws Exception{
        String query = "SELECT id, estilo, dia_semana, TO_CHAR(horario_inicio, 'HH24:MI') as horario_inicio, TO_CHAR(horario_fin, 'HH24:MI') as horario_fin,profesor_id FROM Clase WHERE id = ?";
        Class result = null;

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, classId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = new Class();
                result.setId(rs.getInt("id"));
                result.setStyle(rs.getString("estilo"));
                result.setDay(rs.getString("dia_semana"));
                result.setStart_time(rs.getString("horario_inicio"));
                result.setFinish_time(rs.getString("horario_fin"));
                
                DAOTeachersImp daoTeacher = new DAOTeachersImp();
                Teachers teacher = daoTeacher.selectTeacher(rs.getInt("profesor_id"));
                result.setTeacher(teacher);
            }
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw new Exception("Error al seleccionar el pack: " + e.getMessage());
        } finally {
            this.Close();
        }

        return result;
    }
    
    public List<Class> listClasses(String teacherName) throws Exception {
        List<Class> classList = new ArrayList<>();
        String query;

        if (teacherName == null || teacherName.trim().isEmpty()) {
            // Query para obtener todas las clases
            query = "SELECT id, estilo, dia_semana, TO_CHAR(horario_inicio, 'HH24:MI') AS horario_inicio, "
                  + "TO_CHAR(horario_fin, 'HH24:MI') AS horario_fin, profesor_id FROM clase ORDER BY id ASC";
        } else {
            // Query para obtener clases de un profesor específico
            query = "SELECT c.id, c.estilo, c.dia_semana, TO_CHAR(c.horario_inicio, 'HH24:MI') AS horario_inicio, "
                  + "TO_CHAR(c.horario_fin, 'HH24:MI') AS horario_fin, c.profesor_id "
                  + "FROM clase c "
                  + "JOIN profesores p ON c.profesor_id = p.id "
                  + "LOWER(nombre) LIKE LOWER(?) ORDER BY c.id ASC";
        }

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);

            // Si hay un nombre de profesor, asignarlo al parámetro de la consulta
            if (teacherName != null && !teacherName.trim().isEmpty()) {
                st.setString(1, teacherName);
            }

            ResultSet rs = st.executeQuery();
            DAOTeachers daoTeacher = new DAOTeachersImp();

            while (rs.next()) {
                Class cl = new Class();
                cl.setId(rs.getInt("id"));
                cl.setStyle(rs.getString("estilo"));
                cl.setDay(rs.getString("dia_semana"));
                cl.setStart_time(rs.getString("horario_inicio"));
                cl.setFinish_time(rs.getString("horario_fin"));

                // Asignar el profesor correspondiente
                Teachers teacher = daoTeacher.selectTeacher(rs.getInt("profesor_id"));
                cl.setTeacher(teacher);

                classList.add(cl);
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error al listar clases: " + e.getMessage());
        } finally {
            this.Close();
        }

        return classList;
    }
    
        public int countClassesByTeacher(int teacherId) throws Exception {
        int classCount = 0;
        String query = "SELECT COUNT(*) AS total FROM clase WHERE profesor_id = ?";

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, teacherId); // Asigna el id del profesor al parámetro de la consulta

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                classCount = rs.getInt("total"); // Obtiene el resultado de la consulta
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error al contar clases del profesor: " + e.getMessage());
        } finally {
            this.Close();
        }

        return classCount;
    }

}
