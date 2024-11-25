package com.mycompany.interfaces;

import com.mycompany.models.Students;
import java.util.List;

public interface DAOStudents {
    public void register(Students student) throws Exception;
    public void modify(Students student) throws Exception;
    public void delete(int studentId) throws Exception;
    public List<Students> list(String name) throws Exception;
    public int getStudentIdByName(String studentName) throws Exception;
}