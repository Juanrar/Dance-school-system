package com.mycompany.models;

public class Inscriptions {
    private int id;
    private Class clas;
    private String date;
    private Pays pay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Class getClas() {
        return clas;
    }

    public void setClas(Class clas) {
        this.clas = clas;
    }
    
    public Pays getPay() {
        return pay;
    }

    public void setPay(Pays pay) {
        this.pay = pay;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
