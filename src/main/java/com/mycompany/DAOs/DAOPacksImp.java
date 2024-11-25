package com.mycompany.DAOs;

import com.mycompany.db.Cursor;
import com.mycompany.interfaces.DAOPacks;
import com.mycompany.models.Packs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPacksImp extends Cursor implements DAOPacks{

    @Override
    public void register(Packs pack) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("INSERT INTO pack (nombre,cantidad_clases,precio) VALUES(?,?,?);");
            st.setString(1, pack.getName());
            st.setInt(2, pack.getCant_class());
            st.setInt(3, pack.getPrice());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }

    @Override
    public void modify(Packs pack) throws Exception {
        
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("UPDATE pack SET nombre = ?, cantidad_clases = ?, precio = ? WHERE id = ?");
            st.setString(1, pack.getName());
            st.setInt(2, pack.getCant_class());
            st.setInt(3, pack.getPrice());
            st.setInt(4, pack.getId());
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;  
        } finally {
            this.Close();
        }
    }
    
    @Override
    public void delete(int packId) throws Exception {
        try{
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement("DELETE FROM pack WHERE id = ?;");
            st.setInt(1, packId);
            st.executeUpdate();
            st.close();
        } catch(Exception e) {
            throw e;
        } finally {
            this.Close();
        }
    }

    @Override
    public List<Packs> list() throws Exception {
        List<Packs> list = null;
        try{
            this.Connect();
            
            String query ="SELECT * FROM pack WHERE id > 1 ORDER BY id ASC;";
            
            PreparedStatement st = this.conn.prepareStatement(query);
            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Packs pack = new Packs();
                pack.setId(rs.getInt("id"));
                pack.setName(rs.getString("nombre"));
                pack.setCant_class(rs.getInt("cantidad_clases"));
                pack.setPrice(rs.getInt("precio"));
                list.add(pack);
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
    public Packs selectPack(int packId) throws Exception {
        String query = "SELECT nombre, cantidad_clases, precio FROM pack WHERE id = ?";
        Packs pack = null;

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, packId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                pack = new Packs();
                pack.setId(packId);
                pack.setName(rs.getString("nombre"));
                pack.setCant_class(rs.getInt("cantidad_clases"));
                pack.setPrice(rs.getInt("precio"));
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error al seleccionar el pack: " + e.getMessage());
        } finally {
            this.Close();
        }
        return pack;
    }
    
    public int getPackIdByName(String packName) throws Exception {
        int packId = -1;
        String query = "SELECT id FROM pack WHERE nombre = ?";

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, packName);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                packId = rs.getInt("id");
            }

            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error En getPackIdByName en DAOPacksImp -> " + e.getMessage());
        } finally {
            this.Close();
        }
        return packId;
    }
    
    public List<Packs> listByName(String packName) throws Exception {
        List<Packs> list = new ArrayList<>();
        String query = packName.isEmpty() ? "SELECT * FROM pack ORDER BY id ASC;"
                :"SELECT * FROM pack WHERE LOWER(nombre) LIKE LOWER(?) ORDER BY id ASC";

        try {
            this.Connect();
            PreparedStatement st = this.conn.prepareStatement(query);
            if(!packName.isEmpty()){
                st.setString(1, "%" + packName + "%"); // Filtro para coincidencias parciales
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Packs pack = new Packs();
                pack.setId(rs.getInt("id"));
                pack.setName(rs.getString("nombre"));
                pack.setCant_class(rs.getInt("cantidad_clases"));
                pack.setPrice(rs.getInt("precio"));
                list.add(pack);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw new Exception("Error al listar packs por nombre: " + e.getMessage());
        } finally {
            this.Close();
        }
        return list;
    }


}
