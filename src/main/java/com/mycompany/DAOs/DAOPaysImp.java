package com.mycompany.DAOs;

import com.mycompany.DAOs.DAOPacksImp;
import com.mycompany.db.Cursor;
import com.mycompany.interfaces.DAOPays;
import com.mycompany.models.Packs;
import com.mycompany.models.Pays;
import com.mycompany.models.Students;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOPaysImp extends Cursor implements DAOPays{

    @Override
    public void register(Pays pay) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO Pagos (estudiante_id, pack_id, cantidad_restante) VALUES(?,?,?);");
            st.setInt(1, pay.getStudent().getId());
            st.setInt(2, pay.getPack().getId());
            st.setInt(3, pay.getPack().getCant_class());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }

    @Override
    public void modify(int payId, Packs pack) throws Exception {
        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("UPDATE pagos SET pack_id = ?, cantidad_restante = ? WHERE id = ?;");
            st.setInt(1, pack.getId());
            st.setInt(2, pack.getCant_class());
            st.setInt(3, payId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    @Override
    public void delete(int idPay) throws Exception {
        String query = "DELETE FROM pagos WHERE id = ?";

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, idPay);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new Exception("No se encontró el pago con id " + idPay + " para eliminar.");
            }

            st.close();
        } catch (Exception e) {
            throw new Exception("Error en delete en DAOPaysImp -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
    }

    @Override
    public List<Pays> list(int studentId) throws Exception {
        List<Pays> list = null;
        try{
            this.Connect();
            
            String query = "SELECT id, estudiante_id, pack_id, cantidad_restante, TO_CHAR(fecha_adquisicion, 'DD-MM-YYYY') AS fecha_adquisicion " 
               + "FROM pagos "
               + "WHERE estudiante_id = ? "
               + "ORDER BY id DESC;";
            
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, studentId);
            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Pays pay = new Pays();
                pay.setId(rs.getInt("id"));
                
                DAOStudentsImp daoStudent = new DAOStudentsImp();
                Students student = daoStudent.selectStudent(rs.getInt("estudiante_id"));
                pay.setStudent(student);
                
                DAOPacksImp daoPack = new DAOPacksImp();
                Packs pack = daoPack.selectPack(rs.getInt("pack_id"));
                pay.setPack(pack);
                
                pay.setRemaining(rs.getInt("cantidad_restante"));
                pay.setDate(rs.getString("fecha_adquisicion"));
                list.add(pay);
            }
            rs.close();
            st.close();            
        } catch(Exception e) {
            throw new Exception("Error en list en DAOPaysImp -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
        return list;
    }
    
    public List<Pays> activePayList(int studentId) throws Exception {
        List<Pays> list = new ArrayList<>();
        try {
            this.Connect();

            String query = "SELECT id, estudiante_id, pack_id, cantidad_restante, TO_CHAR(fecha_adquisicion, 'DD-MM-YYYY') AS fecha_adquisicion " 
                         + "FROM pagos "
                         + "WHERE estudiante_id = ? "
                         + "AND cantidad_restante > 0 "
                         + "AND fecha_adquisicion >= NOW() - INTERVAL '30 days' " // Últimos 30 días
                         + "ORDER BY id DESC;";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, studentId);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pays pay = new Pays();
                pay.setId(rs.getInt("id"));

                DAOStudentsImp daoStudent = new DAOStudentsImp();
                Students student = daoStudent.selectStudent(rs.getInt("estudiante_id"));
                pay.setStudent(student);

                DAOPacksImp daoPack = new DAOPacksImp();
                Packs pack = daoPack.selectPack(rs.getInt("pack_id"));
                pay.setPack(pack);

                pay.setRemaining(rs.getInt("cantidad_restante"));
                pay.setDate(rs.getString("fecha_adquisicion"));

                list.add(pay);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en list en DAOPaysImp -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
        return list;
    }

    
    public void decrementRemainingClasses(int payId) throws Exception {
    String query = "UPDATE pagos SET cantidad_restante = cantidad_restante - 1 WHERE id = ? AND cantidad_restante > 0;";
    
    try {
        this.Connect();
        PreparedStatement st = this.conn.prepareStatement(query);
        st.setInt(1, payId);
        
        int rowsAffected = st.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new Exception("No se pudo decrementar la cantidad restante de clases. Verifica que haya clases restantes para este pago.");
        }
        
        st.close();
    } catch (Exception e) {
        throw new Exception("Error en decrementRemainingClasses en DAOPaysImp -> " + e.getMessage(), e);
    } finally {
        this.Close();
        }
    }
    
    public void incrementRemainingClasses(int payId) throws Exception {
    String query = "UPDATE pagos SET cantidad_restante = cantidad_restante + 1 WHERE id = ?;";
    
    try {
        this.Connect();
        PreparedStatement st = this.conn.prepareStatement(query);
        st.setInt(1, payId);
        
        int rowsAffected = st.executeUpdate();
        
        if (rowsAffected == 0) {
            throw new Exception("No se pudo incrementar la cantidad restante de clases.");
        }
        
        st.close();
    } catch (Exception e) {
        throw new Exception("Error en incrementRemainingClasses en DAOPaysImp -> " + e.getMessage(), e);
    } finally {
        this.Close();
        }
    }   

    
    public Pays selectPay(int payId) throws Exception {
        String query = "SELECT id, estudiante_id, pack_id, cantidad_restante,TO_CHAR(fecha_adquisicion, 'DD-MM-YYYY') AS fecha_adquisicion FROM pagos WHERE id = ?";
        Pays result = null;

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, payId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = new Pays();
                result.setId(payId);
                
                DAOStudentsImp daoStudent = new DAOStudentsImp();
                Students student = daoStudent.selectStudent(rs.getInt("estudiante_id"));
                
                result.setStudent(student);
                
                DAOPacksImp daoPack = new DAOPacksImp();
                Packs pack = daoPack.selectPack(rs.getInt("pack_id"));

                result.setPack(pack);
                
                result.setRemaining(rs.getInt("cantidad_restante"));
                result.setDate(rs.getString("fecha_adquisicion"));
            }
            
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en selectPay en DAOPaysImp -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
        return result;
    }
    
    public int getPayForStudent(int studentId, int packId) throws Exception {
        String query = "SELECT * FROM pagos WHERE estudiante_id = ? AND pack_id = ? ORDER BY id DESC LIMIT 1";
        int result = -1;
        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, studentId);
            st.setInt(2, packId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = rs.getInt("id");
            }
            
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error en getPayForStudent en DAOPaysImp -> " + e.getMessage(), e);
        } finally {
            this.Close();
        }
        return result;
    }    
}


