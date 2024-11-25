package com.mycompany.models;

public class Teachers {
    private int id;
    private String name;
    private String email;
    private String DNI;
    private String phone_number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_Number(String phone_number) {
        this.phone_number = phone_number;
    }
}
