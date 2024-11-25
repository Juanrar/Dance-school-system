package com.mycompany.DAOs;

import com.mycompany.db.Cursor;
import com.mycompany.interfaces.DAOStudents;
import com.mycompany.models.Students;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOStudentsImp extends Cursor implements DAOStudents{

    @Override
    public void register(Students student) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO Estudiantes (nombre,email,dni,telefono) VALUES(?,?,?,?);");
            st.setString(1, student.getName());
            st.setString(2, student.getEmail());
            st.setString(3, student.getDNI());
            st.setString(4, student.getPhone_number());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }

    @Override
    public void modify(Students student) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("UPDATE estudiantes SET nombre = ?, email = ?, dni = ?, telefono = ? WHERE id = ?;");
            st.setString(1, student.getName());
            st.setString(2, student.getEmail());
            st.setString(3, student.getDNI());
            st.setString(4, student.getPhone_number());
            st.setInt(5, student.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }    
    }

    @Override
    public void delete(int studentId) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM Estudiantes WHERE id = ?;");
            st.setInt(1, studentId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Close();
        }  
    }

    @Override
    public List<Students> list(String name) throws Exception {
        List<Students> list = null;
        try{
            this.Connect();
            
            String query = name.isEmpty() ? "SELECT * from Estudiantes ORDER BY id ASC;" : 
                    "SELECT * from Estudiantes WHERE LOWER(nombre) LIKE LOWER('%"+name+"%') ORDER BY id ASC;";
            
            PreparedStatement st = this.conn.prepareStatement(query);
            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Students student = new Students();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("nombre"));
                student.setEmail(rs.getString("email"));
                student.setDNI(rs.getString("DNI"));
                student.setPhone_Number(rs.getString("telefono"));
                list.add(student);
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
    
    public int getStudentIdByName(String studentName) throws Exception {
        int studentId = -1;
        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("SELECT id FROM Estudiantes WHERE LOWER(nombre) = LOWER(?);");
            st.setString(1, studentName);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                studentId = rs.getInt("id");
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Close();
        }
        return studentId;
    }
    
    public Students selectStudent(int studentId) throws Exception{
        String query = "SELECT * FROM Estudiantes WHERE id = ?";
        Students result = null;

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, studentId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = new Students();
                result.setId(studentId);
                result.setName(rs.getString("nombre"));
                result.setEmail(rs.getString("email"));
                result.setDNI(rs.getString("dni"));
                result.setPhone_Number(rs.getString("telefono"));
            }
            rs.close();
            st.close();
            
        } catch (Exception e) {
            throw new Exception("Error en selectStudent en DAOStudentImp -> " + e.getMessage());
        } finally {
            this.Close();
        }

        return result;
    }
    
}
