package com.mycompany.models;

public class Pays {
    private int id;
    private Students student;
    private Packs pack;
    private int remaining;
    private String date;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
    
     public Packs getPack() {
        return pack;
    }

    public void setPack(Packs pack) {
        this.pack = pack;
    }
    
    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int cant) {
        this.remaining = cant;
    }
   
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
