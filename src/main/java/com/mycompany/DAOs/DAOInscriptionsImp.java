package com.mycompany.DAOs;

import com.mycompany.DAOs.DAOClassImp;
import com.mycompany.db.Cursor;
import com.mycompany.interfaces.DAOInscriptions;
import com.mycompany.models.Class;
import com.mycompany.models.Inscriptions;
import com.mycompany.models.Pays;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOInscriptionsImp extends Cursor implements DAOInscriptions{

    @Override
    public void register(Inscriptions ins) throws Exception {
        try {
            this.Connect();
            
            String query = "INSERT INTO inscripciones (clase_id, pago_id) VALUES (?, ?)";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, ins.getClas().getId());
            st.setInt(2, ins.getPay().getId());
            
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw new Exception("Error al registrar inscripción -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
    }

    @Override
    public void delete(int idInscription) throws Exception {
        try {
            this.Connect();

            String query = "DELETE FROM inscripciones WHERE id = ?";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, idInscription);

            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw new Exception("Error al eliminar inscripción -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
    }
    
    @Override
    public List<Inscriptions> list(int classId) throws Exception {
        List<Inscriptions> inscriptions = new ArrayList<>();
        
        try {
            this.Connect();
            String query = "SELECT id, pago_id, clase_id, TO_CHAR(fecha_inscripcion, 'DD-MM-YYYY') AS fecha_inscripcion FROM inscripciones WHERE clase_id = ? AND DATE(fecha_inscripcion) = CURRENT_DATE";
            
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, classId);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Inscriptions inscription = new Inscriptions();
                inscription.setId(rs.getInt("id"));
                
                DAOPaysImp daoPay = new DAOPaysImp();
                Pays pay = daoPay.selectPay(rs.getInt("pago_id"));
                inscription.setPay(pay);
                
                DAOClassImp daoClass = new DAOClassImp();
                Class clas = daoClass.selectClass(classId);
                inscription.setClas(clas);
                
                inscription.setDate(rs.getString("fecha_inscripcion"));
                inscriptions.add(inscription);
            }
            
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en la funcion de list() de DAOInscriptionsImp -> " + e.getMessage());
        } finally {
            this.Close();
        }
        return inscriptions;
    }
    
    public Inscriptions selectInscription(int id) throws Exception {
        Inscriptions inscription = null;

        try {
            this.Connect();

            String query = "SELECT id, pago_id, clase_id, TO_CHAR(fecha_inscripcion, 'DD-MM-YYYY') AS fecha_inscripcion FROM inscripciones WHERE id = ?";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                inscription = new Inscriptions();
                inscription.setId(rs.getInt("id"));

                DAOPaysImp daoPay = new DAOPaysImp();
                Pays pay = daoPay.selectPay(rs.getInt("pago_id"));
                inscription.setPay(pay);

                DAOClassImp daoClass = new DAOClassImp();
                Class clas = daoClass.selectClass(rs.getInt("clase_id"));
                inscription.setClas(clas);

                inscription.setDate(rs.getString("fecha_inscripcion"));
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en la función selectInscription() de DAOInscriptionsImp -> " + e.getMessage());
        } finally {
            this.Close();
        }

        return inscription;
    }
    
    public List<Inscriptions> listTeacherHistory(int professorId) throws Exception {
        //uso una instruccion fake para ver el historial de clases dadas con el total de inscriptos en esas clases
        List<Inscriptions> inscriptions = new ArrayList<>();

        try {
            this.Connect();
            String query = "SELECT " +
                           "i.clase_id AS clase_id, " +
                           "DATE(i.fecha_inscripcion) AS fecha_inscripcion, " +
                           "COUNT(*) AS total_inscripciones " +
                           "FROM inscripciones i " +
                           "JOIN clase c ON i.clase_id = c.id " +
                           "WHERE c.profesor_id = ? " +
                           "GROUP BY i.clase_id, DATE(i.fecha_inscripcion) " +
                           "ORDER BY DATE(i.fecha_inscripcion) DESC";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, professorId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Inscriptions fakeInscription = new Inscriptions();
                fakeInscription.setId(rs.getInt("total_inscripciones"));
                
                DAOClassImp daoClass = new DAOClassImp();
                Class clas = daoClass.selectClass(rs.getInt("clase_id"));
                fakeInscription.setClas(clas);
                fakeInscription.setDate(rs.getString("fecha_inscripcion"));

                inscriptions.add(fakeInscription);
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en listByProfessor -> " + e.getMessage());
        } finally {
            this.Close();
        }
        return inscriptions;
    }


    public int countInscriptions(int classId) throws Exception {
        int count = 0;

        try {
            this.Connect();

            String query = "SELECT COUNT(*) AS total FROM inscripciones WHERE clase_id = ? AND DATE(fecha_inscripcion) = CURRENT_DATE";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, classId);

            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt("total");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en la función countInscriptions() de DAOInscriptionsImp -> " + e.getMessage());
        } finally {
            this.Close();
        }

        return count;
    }
    
    public int calculateSalary(int teacherId, int month) throws SQLException{
        int count = 0;
        String query = """
        SELECT 
            p.precio AS pack_precio,
            pg.pack_id
        FROM 
            inscripciones i
        INNER JOIN 
            clase c ON i.clase_id = c.id
        INNER JOIN 
            pagos pg ON i.pago_id = pg.id
        INNER JOIN 
            pack p ON pg.pack_id = p.id
        WHERE 
            c.profesor_id = ?
            AND DATE_PART('month', i.fecha_inscripcion) = ?
            AND DATE_PART('year', i.fecha_inscripcion) = DATE_PART('year', CURRENT_DATE);
        """;
            
        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, teacherId);
            st.setInt(2, month);

            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int packPrice = rs.getInt("pack_precio");
                int packId = rs.getInt("pack_id");
                int amount;
                switch(packId){
                    case 1 -> amount = packPrice;
                    case 2 -> amount = packPrice/4;
                    case 3 -> amount = packPrice/8;
                    case 4 -> amount = packPrice/16;
                    default -> amount = 0;
                }
                
                count += amount;
            }   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOInscriptionsImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }   
}
