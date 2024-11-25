package com.mycompany.interfaces;

import com.mycompany.models.Teachers;
import java.util.List;

public interface DAOTeachers {
    public void register(Teachers teacher) throws Exception;
    public void modify(Teachers teacher) throws Exception;
    public void delete(int teacherId) throws Exception;
    public List<Teachers> list(String name) throws Exception;
    
    public Teachers selectTeacher(int teacherId) throws Exception;
}