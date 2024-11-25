package com.mycompany.interfaces;

import com.mycompany.models.Packs;
import java.util.List;

public interface DAOPacks {
    public void register(Packs pack) throws Exception;
    public void modify(Packs pack) throws Exception;
    public void delete(int packId) throws Exception;
    public List<Packs> list() throws Exception;
    public Packs selectPack(int packId)throws Exception;

}
