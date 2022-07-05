package com.dev.mrvazguen.indexproductorum.data.model;

import androidx.annotation.AttrRes;
 

public class Usuari {
    String nombre;
    String email;
    String pasword;

    public  Usuari(String nombre,String email,String pasword){
        this.nombre = nombre;
        this.pasword= pasword;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

}
