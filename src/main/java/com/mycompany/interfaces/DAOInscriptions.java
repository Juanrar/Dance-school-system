package com.mycompany.interfaces;

import com.mycompany.models.Inscriptions;
import java.util.List;

public interface DAOInscriptions {
    public void register(Inscriptions ins) throws Exception;
    //public void modify(Inscriptions ins) throws Exception;
    public void delete(int idInscription) throws Exception;
    public List<Inscriptions> list(int classId) throws Exception;
}