package com.mycompany.interfaces;

import com.mycompany.models.Packs;
import com.mycompany.models.Pays;
import java.util.List;

public interface DAOPays {
    public void register(Pays pay) throws Exception;
    public void modify(int payId,Packs newPack) throws Exception;
    public void delete(int idPay) throws Exception;
    public List<Pays> list(int studentId) throws Exception;
}