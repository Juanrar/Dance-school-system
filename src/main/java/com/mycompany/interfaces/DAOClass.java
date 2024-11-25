package com.mycompany.interfaces;

import com.mycompany.models.Class;
import java.util.List;

public interface DAOClass {
    public void register(Class cl) throws Exception;
    public void modify(Class cl) throws Exception;
    public void delete(int idClass) throws Exception;
    public List<Class> list(String diaSemana) throws Exception;
}