package com.mycompany.DAOs;

import com.mycompany.db.Cursor;
import com.mycompany.interfaces.DAOTeachers;
import com.mycompany.models.Teachers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOTeachersImp extends Cursor implements DAOTeachers{

    @Override
    public void register(Teachers teacher) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO profesores (nombre,email,dni,telefono) VALUES(?,?,?,?);");
            st.setString(1, teacher.getName());
            st.setString(2, teacher.getEmail());
            st.setString(3, teacher.getDNI());
            st.setString(4, teacher.getPhone_number());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }

    @Override
    public void modify(Teachers teacher) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("UPDATE profesores SET nombre = ?, email = ?, dni = ?, telefono = ? WHERE id = ?;");
            st.setString(1, teacher.getName());
            st.setString(2, teacher.getEmail());
            st.setString(3, teacher.getDNI());
            st.setString(4, teacher.getPhone_number());
            st.setInt(5, teacher.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }    
    }

    @Override
    public void delete(int teacherId) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM profesores WHERE id = ?;");
            st.setInt(1, teacherId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        
    }

    @Override
    public List<Teachers> list(String name) throws Exception {
        List<Teachers> list = null;
        try{
            this.Connect();
            
            String query = name.isEmpty() ? "SELECT * from profesores ORDER BY id ASC;" : 
                    "SELECT * from profesores WHERE LOWER(nombre) LIKE LOWER('%"+name+"%') ORDER BY id ASC;";
            
            PreparedStatement st = this.conn.prepareStatement(query);
            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Teachers teacher = new Teachers();
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("nombre"));
                teacher.setEmail(rs.getString("email"));
                teacher.setDNI(rs.getString("DNI"));
                teacher.setPhone_Number(rs.getString("telefono"));
                list.add(teacher);
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

    @Override
    public Teachers selectTeacher(int teacherId) throws Exception {
        String query = "SELECT * FROM profesores WHERE id = ?";
        Teachers teacher = null;

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, teacherId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                teacher = new Teachers();
                teacher.setId(teacherId);
                teacher.setName(rs.getString("nombre"));
                teacher.setEmail(rs.getString("email"));
                teacher.setDNI(rs.getString("dni"));
                teacher.setPhone_Number(rs.getString("telefono"));
            }
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw new Exception("Error al seleccionar el pack: " + e.getMessage());
        } finally {
            this.Close();
        }

        return teacher;
    }
}
    
